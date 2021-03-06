from settings import JWT_SECRET_KEY
from flask_mysqldb import MySQLdb
from hashlib import pbkdf2_hmac

from app import db

import os
import jwt


def db_read(query, params=None):
    cursor = db.connection.cursor()
    if params:
        cursor.execute(query, params)
    else:
        cursor.execute(query)

    entries = cursor.fetchall()
    cursor.close()

    content = []

    for entry in entries:
        content.append(entry)

    return content


def db_write(query, params):
    cursor = db.connection.cursor()
    try:
        cursor.execute(query, params)
        insert_id = db.connection.insert_id()
        db.connection.commit()
        cursor.close()

        return {"status": True, "insert_id": insert_id}

    except MySQLdb._exceptions.IntegrityError:
        cursor.close()
        return {"status": False, "insert_id": 0}

def db_update(query, params):
    cursor = db.connection.cursor()
    try:
        cursor.execute(query, params)
        db.connection.commit()
        cursor.close()

        return True

    except MySQLdb._exceptions.IntegrityError:
        cursor.close()
        return False

def generate_salt():
    salt = os.urandom(16)
    return salt.hex()


def generate_hash(plain_password, password_salt):
    password_hash = pbkdf2_hmac(
        "sha256",
        b"%b" % bytes(plain_password, "utf-8"),
        b"%b" % bytes(password_salt, "utf-8"),
        10000,
    )
    return password_hash.hex()


def generate_jwt_token(content):
    encoded_content = jwt.encode(content, JWT_SECRET_KEY, algorithm="HS256")
    token = str(encoded_content).split("'")[1]
    return token

def decode_token(token):
    decode = jwt.decode(token, JWT_SECRET_KEY, algorithm="HS256")
    return decode


def validate_user_input(input_type, **kwargs):
    if input_type == "authentication":
        if len(kwargs["email"]) <= 255 and len(kwargs["password"]) <= 255 and len(kwargs["nama"]) <= 255:
            return True
        else:
            return False


def validate_user(email, password):
    current_user = db_read("""SELECT users.*, profil.nama FROM users, profil WHERE users.id = profil.id AND users.email = %s""", (email,))

    if len(current_user) == 1:
        saved_password_hash = current_user[0]["password_hash"]
        saved_password_salt = current_user[0]["password_salt"]
        password_hash = generate_hash(password, saved_password_salt)

        if password_hash == saved_password_hash:
            user_id = current_user[0]["id"]
            user_role = current_user[0]["role"]
            user_email = current_user[0]["email"]
            user_nama = current_user[0]["nama"]
            # jwt_token = generate_jwt_token({"id": user_id})
            return {"user_id": user_id,
                    "email": user_email,
                    "nama": user_nama,
                    "role": user_role
                }
        else:
            return False

    else:
        return False
