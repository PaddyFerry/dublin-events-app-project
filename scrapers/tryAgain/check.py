from bs4 import BeautifulSoup as Soup
from unidecode import unidecode
import requests as re
import mysql.connector


def check_db(place, cur):
    name = place[0]
    if "'" in name:
        name = "".join(name.split("'"))
    # print (name)
    name = "".join(name.split(" "))
    address = place[4]
    sel_name = unidecode(u"SELECT * FROM place WHERE searchName = '{}'".format(name))
    print(sel_name)
    sel_address = unidecode(u"SELECT * FROM place WHERE address LIKE '%{}%'".format(address[3: -10]))
    # print sel_address
    # print sel_name
    cur.execute(sel_name)
    name_res = len([l for l in cur])
    cur.execute(sel_address)
    add_res = len([l for l in cur])
    return name_res and add_res


def google_it(place):
    headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36'}
    url = "https://www.google.ie/search?q="
    raw = re.get(url + place + "+dublin" + "&cr=IE", headers=headers)
    html = Soup(raw.content, "html5lib")
    result = html.find("div", {"class": "_OKe"})
    if result.find("span", {"data-dtype": "d3ifr"}):
        phone = result.find("span", {"data-dtype": "d3ifr"}).get_text()
    else:
        phone = ""
    return (result.find("span", {'class': None}).get_text(),   # name
            result.find("span", {"class": "rtng"}).get_text(),  # rating
            result.find("span", {"class": "_eMw"}).get_text(),   # category
            phone,                                              # phone
            result.find("span", {"class": "_Xbe"}).get_text())  # address


def add_to_db(place, cur, conn):
    add_place = ("INSERT INTO place "
                 "(name, rating, category, phone, address) "
                 "VALUES (%s, %s, %s, %s, %s)")
    cur.execute(add_place, place)
    conn.commit()


def find_check_add(place):

    cnx = mysql.connector.connect(user='test', password='1234',
                                  host='159.65.84.145', database="app")
    print("Connected to DB")
    cursor = cnx.cursor()
    pub_venue = google_it(place)
    if not check_db(pub_venue, cursor):
        add_to_db(pub_venue, cursor, cnx)
        print("Added to DB")
    else:
        print("Already in DB")
