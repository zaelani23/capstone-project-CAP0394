from flask import Blueprint, request, Response, jsonify
from utils import (
    db_write,
    db_read,
    db_update,
)

dashboard = Blueprint("dashboard", __name__)

@dashboard.route("/profil", methods=["POST"])
def get_profile():
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
	user_nama = request.json["nama"].title().strip()
	user_email = request.json["email"].strip()
	user_desa = request.json["desa"].title().strip()
	user_kecamatan = request.json["kecamatan"].title().strip()
	user_kabupaten = request.json["kabupaten"].title().strip()
	user_provinsi = request.json["provinsi"].title().strip()

	update1 = db_update("""UPDATE profil SET email = %s, nama = %s, desa = %s, kecamatan = %s, kabupaten = %s, provinsi = %s WHERE profil.id = %s""",
                (user_email, user_nama, user_desa, user_kecamatan, user_kabupaten, user_provinsi, user_id),
            )
	update2 = db_update("""UPDATE users SET email = %s WHERE users.id = %s""",
				(user_email, user_id),
			)

	if update1 and update2:
		return jsonify({"status": "success"})
	else:
		return Response(status=400)