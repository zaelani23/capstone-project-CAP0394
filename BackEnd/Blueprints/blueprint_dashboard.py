from flask import Blueprint, request, Response, jsonify
from utils import (
    db_write,
    db_read,
    db_update,
)

dashboard = Blueprint("dashboard", __name__)

@dashboard.route("/profil", methods=["POST"])
def get_profile():
	print(request.json)
	user_id = request.json["user_id"]

	user_profile = db_read("""SELECT * FROM profil WHERE id = %s""", (user_id,))
	profile = {
		"nama": user_profile[0]["nama"],
		"email": user_profile[0]["email"],
		"desa": user_profile[0]["desa"],
		"kecamatan": user_profile[0]["kecamatan"],
		"kabupaten": user_profile[0]["kabupaten"],
		"provinsi": user_profile[0]["provinsi"]
	}

	return jsonify(profile)

@dashboard.route("/edit-profil", methods=["POST"])
def edit_profil():
	user_id = request.json["user_id"]
	user_nama = request.json["nama"]
	user_email = request.json["email"]
	user_desa = request.json["desa"]
	user_kecamatan = request.json["kecamatan"]
	user_kabupaten = request.json["kabupaten"]
	user_provinsi = request.json["provinsi"]

	update = db_update("""UPDATE profil SET email = %s, nama = %s, desa = %s, kecamatan = %s, kabupaten = %s, provinsi = %s WHERE profil.id = %s""",
                (user_email, user_nama, user_desa, user_kecamatan, user_kabupaten, user_provinsi, user_id),
            )

	if update:
		return Response(status=200)
	else:
		return Response(status=400)