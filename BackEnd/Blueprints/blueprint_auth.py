from flask import Blueprint, request, Response, jsonify, session
from utils import (
    validate_user_input,
    generate_salt,
    generate_hash,
    db_write,
    validate_user,
)

authentication = Blueprint("authentication", __name__)


@authentication.route("/register", methods=["POST"])
def register_user():
    user_nama = request.json["nama"]
    user_email = request.json["email"]
    user_password = request.json["password"]
    user_confirm_password = request.json["confirmPassword"]
    user_role = request.json["role"]

    if user_password == user_confirm_password and validate_user_input(
        "authentication", email=user_email, password=user_password, nama=user_nama
    ):
        password_salt = generate_salt()
        password_hash = generate_hash(user_password, password_salt)

        query1 = db_write(
            """INSERT INTO users (email, password_salt, password_hash, role) VALUES (%s, %s, %s, %s)""",
            (user_email, password_salt, password_hash, user_role),
        )
        if query1["status"]:
            insert_id = query1["insert_id"]
            query2 = db_write(
                """INSERT INTO profil (id, email, nama, desa, kecamatan, kabupaten, provinsi) VALUES (%s, %s, %s, '', '', '', '')""",
                (insert_id, user_email, user_nama.title()),
            )

        if query1["status"] and ["status"]:
            return jsonify({"status": "success"})
        else:
            return Response(status=409)
    else:
        return Response(status=400)


@authentication.route("/login", methods=["POST"])
def login_user():
    user_email = request.json["email"]
    user_password = request.json["password"]

    user = validate_user(user_email, user_password)

    if user:
        session['user_id'] = user["user_id"]
        return jsonify(user)
    else:
        Response(status=401)


@authentication.route("/logout")
def logout_user():
    session.pop('user_id', None)
    return Response(status=200)
