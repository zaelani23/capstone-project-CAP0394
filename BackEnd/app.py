from flask import Flask
from flask_cors import CORS
from flask_mysqldb import MySQL
from settings import MYSQL_USER, MYSQL_PASSWORD, MYSQL_DB


app = Flask(__name__)

app.secret_key = b'_5#y2L"F4ssz\n\xec]/'

app.config["MYSQL_HOST"] = MYSQL_HOST
app.config["MYSQL_USER"] = MYSQL_USER
app.config["MYSQL_PASSWORD"] = MYSQL_PASSWORD
app.config["MYSQL_DB"] = MYSQL_DB
app.config["MYSQL_CURSORCLASS"] = "DictCursor"

db = MySQL(app)

from Blueprints.blueprint_auth import authentication
app.register_blueprint(authentication, url_prefix="/api/auth")

from Blueprints.blueprint_dashboard import dashboard
app.register_blueprint(dashboard, url_prefix="/api/")
