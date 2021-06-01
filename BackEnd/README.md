# BackEnd REST API

## Built With

- Flask
- MySQL

## Usage

1. Clone the repository and change directory

```bash
git https://github.com/zaelani23/capstone-project-CAP0394.git
cd capstone-project-CAP0394/BackEnd
```

2. Setup Python VirtualEnv and activate.

```bash
virtualenv -p <path_to_python_installation> venv
source venv/bin/activate
```

3. Install the required packages.

```bash
pip install -r requirements.txt
```

4. Setup environment variables in `.env` file.

```
MYSQL_HOST = "<HOST>"
MYSQL_USER="<username>"
MYSQL_PASSWORD="<password>"
MYSQL_DB="<database>"
JWT_SECRET_KEY="SomeRandomSecretPhrase"
```

5. Start the server.

  - For Linux environment

```bash
export FLASK_APP=app.py
export FLASK_ENV=development
flask run --host=0.0.0.0
```
  - For Windows environment

```bash
set FLASK_APP=app.py
set FLASK_ENV=development
flask run --host=0.0.0.0
```
