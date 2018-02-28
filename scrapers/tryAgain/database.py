from bs4 import BeautifulSoup as Soup
from unidecode import unidecode
import requests as re
import mysql.connector
import entertainment
from time import sleep


class Database(object):
    """Class for taking scraper returns and working with the database accordingly"""
    def __init__(self, user, password, host, database):
        self.user = user
        self.password = password
        self.host = host
        self.database = database

    def __enter__(self):
        self.cnx = mysql.connector.connect(user=self.user, password=self.password,
                                           host=self.host, database=self.database)
        print("Connected to database")

    def __exit__(self, exc_type, exc_val, exc_tb):
        self.cnx.close()
        print("Finished with database")

    @staticmethod
    def select(query, column, table):
        """Returns a select statement to be used in SQL"""
        return unidecode(u"SELECT * FROM {0} WHERE {1} = \"{2}\" OR"
                         u" REPLACE({1}, \"\'\", \"\") = \"{2}\"").format(table, column, query.replace("'", ""))

    def check_db_venue(self, place):
        """Checks the database for the composite key of venue ie.(name, address)"""
        print "Checking database for " + place
        cur = self.cnx.cursor()
        name = self.select(place, "name", "place")
        cur.execute(name)
        name_res = len([l for l in cur])
        cur.close()
        return name_res # and add_res

    @staticmethod
    def google_it(place):
        """Google's a venue and returns information about it in a tuple as (name, rating, category, phone, address)"""
        place = place
        headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36'}
        url = "https://www.google.ie/search?q="
        raw = re.get(url + place + "+dublin" + "&cr=IE", headers=headers)
        html = Soup(raw.content, "html5lib")
        result = html.find("div", {"class": "_OKe"})
        if result.find("span", {"data-dtype": "d3ifr"}):
            phone = result.find("span", {"data-dtype": "d3ifr"}).get_text()
        else:
            phone = ""
        return (result.find("span", {'class': None}).get_text(),  # name
                result.find("span", {"class": "rtng"}).get_text(),  # rating
                result.find("span", {"class": "_eMw"}).get_text(),  # category
                phone,  # phone
                result.find("span", {"class": "_Xbe"}).get_text())  # address

    def add_to_db_venue(self, place):
        """Adds a venue(tuple) to the database raises error if duplicate"""
        cur = self.cnx.cursor()
        try:
            add_place = ("INSERT INTO place "
                         "(name, rating, category, phone, address) "
                         "VALUES (%s, %s, %s, %s, %s)")
            cur.execute(add_place, place)
            print("ADDED ", place[0])
            self.cnx.commit()
        except mysql.connector.IntegrityError as err:
            print("Error: {}".format(err))
        cur.close()

    def add_to_db_event(self, event):
        """Adds an event (tuple) to the database raises error if duplicate"""
        cur = self.cnx.cursor()
        try:
            add_event = ("INSERT INTO events "
                         "(name, location, tickets, description, datetime) "
                         "VALUES (%s, %s, %s, %s, %s)")
            cur.execute(add_event, event)
            print("ADDED ", event[0])
            self.cnx.commit()
        except mysql.connector.IntegrityError as err:
            print("Error: {}".format(err))
        cur.close()

    def check_add_venue(self, place):
        """Takes a venue name and if it is not in the database it tries to add it"""
        if not self.check_db_venue(place):
            try:
                self.add_to_db_venue(self.google_it(place))
                print("{} added to DB".format(place))
            except AttributeError:
                print("Not enough info on {}".format(place))
                return
        else:
            print("{} already in DB".format(place))

    def check_add_event(self, event):
        """Adds an event to the events table and the venue to the venue table if it doesn't already exist there"""
        # print(event[1])
        self.check_add_venue(event[1])
        self.add_to_db_event(event)


db = Database("test", "1234", "159.65.84.145", "app")
with db:
    for event in entertainment.get_info():
        try:
            db.check_add_event(event)
        except UnicodeEncodeError as err:
            print("Error : {}".format(err))
            print("ERROR WITH", event)
        print ("________________________________________")
    # db.check_add_venue("The Grand Social")
