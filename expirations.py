from datetime import date
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

#This program will provide a menu of all the reagents. The
#program will allow you to select a certain item and will
#return the expiration of the item if it is open on the current date.
#The program will allow you to enter a date to see when the reagent
#will expire on a date of the users choice.

app = Flask(__name__)
app.secret_key = 'somesecretkeyonlyishouldknow'
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///inventory.sqlite3'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)

class reagents(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    Name = db.Column(db.String(100), nullable=False)
    ExpirationDate = db.Column(db.DateTime, nullable=False)

def __repr__(self):
        return f"Reagents('{self.Name}', '{self.ExpirationDate}')"



def menu():
    print("-----------------------------")
    print("1. Opening a reagent today")
    print("2. Reagent from another day")
    print("-----------------------------")

def instruments():
    print("------------------------------------")
    print("1. Adam")
    print("2. Alyx")
    print("3. Orion")
    print("------------------------------------")
    
def adam():
    print("-------------------")
    print("1. Adam Controls")
    print("2. Bead Solution")
    print("3. r-Solution")
    print("-------------------")

def alyx():
    print("------------------")
    print("1. Miniclean")
    print("2. Minilyse LMG")
    print("3. Minidil LMG")
    print("4. Minoclair")
    print("5. Minitrol 16")
    print("------------------")

def orion():
    print("------------------")
    print("1. 7 Buffer")
    print("2. 4 Buffer")
    print("3. 10 Buffer")
    print("4. Filling Solution")
    print("5. A pH cleaner")
    print("------------------")




while(True):
    menu()
    select = int(input("Select an option: "))
#If reagent was opened on current date
    if select == 1:
        instruments()
        choice = int(input("Which instrument has a new reagent?: "))
        if choice == 1:
            adam()
            int(input("Which reagent was replaced?: "))
            if choice == 1:
                print("Adam controls expire: ")
            if choice == 2:
                print("Bead Solution expires: ")
            if choice == 3:
                print("r-solution expires: ")
        if choice == 2:
            alyx()
            int(input("Which reagent was replaced?: "))
            if choice == 1:
                print("Miniclean expires: ")
            if choice == 2:
                print("Minilyse LMG expires: ")
            if choice == 3: 
                print("Minidil LMG expires: ")
            if choice == 4:
                print("Minoclair expires: ")
            if choice == 5:
                    print("Minitrol 16 expires: ")
        if choice == 3:
            orion()
            int(input(print("Which reagent was replaced?: ")))
            if choice == 1:
                print("7 Buffer expires: ")
            if choice == 2:
                print("4 Buffer expires: ")
            if choice == 3:
                print("10 Buffer expires: ")
            if choice == 4:
                print("Filling Solution expires: ")
            if choice == 5:
                print("A pH clearner expires: ")

#If regent was not opened on current date
    if select == 2:
        instruments()
        date_entry = input("Enter a date in YYYY-MM-DD format: ")
        year, month, day = map(int, date_entry.split('-'))
        ReagExp = datetime.date(year, month, day)
        choice = int(input("Which instrument has a new reagent?: "))
        if choice == 1:
            adam()
            int(input("Which reagent was replaced?: "))
            if choice == 1:
                print("Adam controls expire: ")
            if choice == 2:
                print("Bead Solution expires: ")
            if choice == 3:
                print("r-solution expires: ")
        if choice == 2:
            alyx()
            int(input("Which reagent was replaced?: "))
            if choice == 1:
                print("Miniclean expires: ")
            if choice == 2:
                print("Minilyse LMG expires: ")
            if choice == 3: 
                print("Minidil LMG expires: ")
            if choice == 4:
                print("Minoclair expires: ")
            if choice == 5:
                print("Minitrol 16 expires: ")
        if choice == 3:
            orion()
            int(input(print("Which reagent was replaced?: ")))
            if choice == 1:
                print("7 Buffer expires: ")
            if choice == 2:
                print("4 Buffer expires: ")
            if choice == 3:
                print("10 Buffer expires: ")
            if choice == 4:
                print("Filling Solution expires: ")
            if choice == 5:
                print("A pH clearner expires: ")

    
    