from flask import Blueprint, request, Response, jsonify
from utils import (
    validate_user_input,
    generate_salt,
    generate_hash,
    db_write,
    validate_user,
)

dashboard = Blueprint("dashboard", __name__)


