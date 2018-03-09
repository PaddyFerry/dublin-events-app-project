from selenium import webdriver
from time import sleep
from selenium.webdriver.firefox.options import Options
import mysql.connector


def check_db(place, cur):
    name = place[0]
    if "'" in name:
        name = "".join(name.split("'"))
    print (name)
    name = "".join(name.split(" "))
    address = place[4]
    sel_name = "SELECT * FROM place WHERE searchName = '{}'".format(name)
    print(address.split(" ")[1])
    sel_address = "SELECT * FROM place WHERE address LIKE '%{}%'".format(address[3: -3])
    print sel_address
    print sel_name
    cur.execute(sel_name)
    name_res = len([l for l in cur])
    cur.execute(sel_address)
    add_res = len([l for l in cur])
    return name_res and add_res


def get_details(lines):
    (name, rating, category, phone, address) = lines[0], lines[2], lines[4], "null", "null"
    for line in lines:
        if "Address: " in line:
            address = line[8:].strip()
        elif "Phone: " in line:
            phone = line[6:].strip()
            break
    print name
    return name, rating, category, phone, address


def get_places(term):
    url = "https://www.google.ie/search?dcr=0&q=" + term + "+in+dublin&npsic=0" \
          "&rflfq=1&rlha=0&rllag=53375801,-6375625,1731&tbm=lcl&ved=0a" \
          "hUKEwiU5u_T1rTZAhWJDsAKHcstDzoQjGoIYg&tbs=lrf:!2m1!1e2!2m1!" \
          "1e3!3sIAE,lf:1,lf_ui:9&rldoc=1&biw=1268&bih=607"
    options = Options()
    options.add_argument("--headless")
    driver = webdriver.Firefox(firefox_options=options)
    driver.get(url)
    print("Connected")
    next_page = driver.find_element_by_id("pnnext")
    while next_page:
        try:
            next_page = driver.find_element_by_id("pnnext")
        except:
            print("last page")
            next_page = None
        panels = driver.find_elements_by_class_name("_gt")
        for panel in panels:
            panel.click()
            sleep(1)
            try:
                yield get_details(driver.find_element_by_class_name("_OKe").text.split("\n"))
            except IndexError:
                print("failed")
                f = "fail"
                yield (f, f, f, f, f)
        if next_page:
            next_page.click()
        else:
            pass
        print("next page")
        sleep(3)
    driver.close()


cnx = mysql.connector.connect(user='test', password='1234',
                              host='159.65.84.145', database="app")
print("Connected to DB")

cursor = cnx.cursor()
if check_db(('Lanigan\'s Pub', '4.4', 'Bar', '(01) 874 3535', '10 Eden Quay, North City, Dublin'), cursor):
    print(True)
else:
    print(False)


add_place = ("INSERT INTO place "
             "(name, rating, category, phone, address) "
             "VALUES (%s, %s, %s, %s, %s)")


# for i in get_places("music venues"):
#     cursor.execute(add_place, i)
#     cnx.commit()
#     print "committed"

cursor.close()
cnx.close()
