from dotenv import load_dotenv

import os

load_dotenv()

# MYSQL Config
MYSQL_HOST = os.getenv("MYSQL_HOST")
MYSQL_USER = os.getenv("MYSQL_USER")
MYSQL_PASSWORD = os.getenv("MYSQL_PASSWORD")
MYSQL_UNIX_SOCKET = os.getenv("MYSQL_UNIX_SOCKET")
MYSQL_DB = os.getenv("MYSQL_DB")

# JWT Config
JWT_SECRET_KEY = os.getenv("JWT_SECRET_KEY")
