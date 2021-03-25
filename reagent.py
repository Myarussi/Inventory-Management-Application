from datetime import datetime
from flask_sqlalchemy import SQLAlchemy
from flask_wtf import FlaskForm
from wtforms import StringField, SubmitField
from wtforms.validators import DataRequired, Length, EqualTo
from flask import (
    Flask, 
    redirect,
    render_template, 
    request,
    session,
    url_for,
    flash
)


app = Flask(__name__)
app.secret_key = 'somesecretkeyonlyishouldknow'
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///reagent.sqlite3'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)


class Reagent(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    Name = db.Column(db.String(100), nullable=False)
    ExpirationDate = db.Column(db.DateTime, nullable=False, default=datetime.utcnow)

def __repr__(self):
        return f"Reagents('{self.Name}', '{self.ExpirationDate}')"

@app.route("/")
def home():
    return render_template("reagent.html")

@app.route("/open-reagent", methods=["POST", "GET"])
def open():
    if request.method == "POST":
        session.permanent = True
        name = request.form("nm")
        return redirect(url_for("reagent", reag=reagent))
    else:
        return render_template("reagent.html")

@app.route("/<reag>")
def reagent(reag):
    return f"<h1>{reag}</h1>"

if __name__ == "__main__":
    app.run(debug=True)