import datetime
import time
import collections.abc
import random
import sys
from flask import (
    Flask, 
    redirect,
    render_template, 
    request,
    session,
    url_for,
    flash
)

from flask_sqlalchemy import SQLAlchemy
from flask_wtf import FlaskForm
from wtforms import StringField, SubmitField
from wtforms.validators import DataRequired, Length, EqualTo


app = Flask(__name__)
app.secret_key = 'somesecretkeyonlyishouldknow'
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///inventory.sqlite3'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)

#database

class Inventory(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    Name = db.Column("Name", db.String(100), nullable=False)
    Manufacturer = db.Column("Manufacturer", db.String(100), nullable=False)
    Orderid = db.Column("Order ID", db.String(100), nullable=False)
    AmountinStock = db.Column("Amount in Stock", db.Integer, nullable=False)
    Par = db.Column("Par", db.Integer, nullable=False)
    LotNumber = db.Column("Lot Number", db.String(100), nullable=False)
    LotExpiration = db.Column("Lot Expiration", db.String(100), nullable=False)
    Notes = db.Column("Notes", db.Text, nullable=False)
    DatePosted = db.Column("Date Posted", db.DateTime, nullable=False)

    def __repr__(self):
        return f"Inventory('{self.Name}', '{self.Manufacturer}', '{self.Orderid}', '{self.AmountinStock}', '{self.Par}', '{self.LotNumber}', '{self.LotExpiration}', '{self.Notes}', '{self.DatePosted}')"
    


# inventory system project 

headings = ("ID", "Name", "Manufacturer", "Order ID", "Amount in Stock", "Par", "Lot Number", "Lot Expiration")

expLot_headings = ("Name", "Lot Number", "Expiration")

user_headings = ("User Name", "Action", "Item", "Date and Time")

dict_inventory = {
1: {"Name": "Gloves", "Manufacturer": "NA", "Order ID": "PHGLOVLG", "Amount in stock": 20, "Par": 2, "Lot Number": "123456", "Lot Expiration": datetime.date(2023, 3, 5)}, 
2: {"Name": "BacT Bottles", "Manufacturer": "Biomerieux", "Order ID": "NA", "Amount in stock": 2, "Par": 3, "Lot Number":"789456", "Lot Expiration": datetime.date(2021, 5, 26)}, 
3: {"Name": "pH Buffer 10", "Manufacturer" : "ThermoScientific", "Order ID": "NA", "Amount in stock": 10, "Par":10, "Lot Number": "35678", "Lot Expiration": datetime.date(2020, 3, 14)}
}

dict_tracking = {"User Name" : "" , "Action" : "", "Item": "", "Date and Time": ""}

dict_usernames = {"Michael" : "password", "Natalie" : "secret", "Shelby": "passcode"}

class User:
    def __init__(self, id, username, password):
        self.id = id
        self.username = username
        self.password = password

    def __repr__(self):
        return f'<User: {self.username}>' 


class AddItemForm(FlaskForm):
    for k in dict_inventory:
        add_name = dict_inventory[k]['Name']
    Add = StringField('Add',
                        validators=[DataRequired(), EqualTo(add_name)])
    Quantity = StringField('Quantity',
                        validators=[DataRequired()])
    Submit = SubmitField('Submit')
    

class TakeItemForm(FlaskForm):
    for k in dict_inventory:
        take_name = dict_inventory[k]['Name']
    Take = StringField('Take', 
                            validators=[DataRequired(), EqualTo(take_name)])
    Quantity = StringField('Quanity',
                            validators=[DataRequired()])
    Submit = SubmitField('Submit')

users = []
users.append(User(id=1, username= 'Michael', password='password'))
users.append(User(id=2, username= 'Natalie', password='secret'))

@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        session.pop('user_id', None)

        username = request.form['username']
        password = request.form['password']
        
        user = [x for x in users if x.username == username][0]
        if user and user.password == password:
            session['user_id'] = user.id
            return redirect(url_for('main_menu'))
        else:
            return redirect(url_for('login'))
        

    return render_template('login.html')

@app.route('/menu')
def main_menu(): 
    if 'user_id' in session:
        return render_template('main_menu.html')
        
@app.route('/stock')
def stock_items():
    return render_template('item_list.html',headings=headings, dict_inventory=dict_inventory)

@app.route('/AddItem', methods=['GET', 'POST'])
def add_item():
    form = AddItemForm()
    if form.validate_on_submit():
        flash(f'{form.Add.data} successfully added to stock!', 'success')
        return redirect(url_for('stock'))
    return render_template('add_item.html', title='Add', form=form)

@app.route('/TakeItem', methods=['GET', 'POST'])
def take_item():
    form = TakeItemForm()
    if form.validate_on_submit():
        flash(f'{form.Quantity.data} {form.Take.data} successfully taken from stock!', 'success')
        return redirect(url_for('stock'))
    return render_template('take_item.html', title='Take', form=form)

@app.route('/AddNewItem', methods=['GET', 'POST'])
def add_new_item():
    form = AddItemForm()
    if form.validate_on_submit():
        flash(f'{form.Add.data} successfully added to stock!', 'success')
        return redirect(url_for('stock'))
    return render_template('add_new_item.html', title='Add', form=form)

@app.route('/DeleteItem', methods=['GET', 'POST'])
def delete_item():
    form = TakeItemForm()
    if form.validate_on_submit():
        flash(f'{form.Quantity.data} {form.Take.data} successfully taken from stock!', 'success')
        return redirect(url_for('stock'))
    return render_template('remove_item.html', title='Take', form=form)

@app.route('/LotExp')
def lot_exp():
    return render_template('lot_exp.html',expLotheadings=expLot_headings, dict_inventory=dict_inventory)

@app.route('/UserHistory')
def user_history():
    return render_template('user_history.html', user_headings=user_headings, dict_tracking=dict_tracking)


if __name__ == "__main__":
    db.create_all()
    app.run(debug=True)




