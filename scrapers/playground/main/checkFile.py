from collections import Counter
import json
import mysql.connector

f = open("test.json", "r")

data = json.loads(f.read())

countVenues = Counter(l["location"] for l in data)
countDates = Counter(l["time"] for l in data)


def check_name(place, cur):
    print ("Searching for {}".format(place))
    name = place
    if "'s" in name:
        name = "".join(name.split("'"))
    name = "".join(name.split(" "))
    print (name)
    sel_name = "SELECT * FROM place WHERE searchName = '{}'".format(name)
    #print sel_name
    cur.execute(sel_name)
    name_res = ([l for l in cur])
    return name_res


maxi = 0
i = ""
# for event in data:
#     try:
#         if len(event["description"]) > maxi:
#             maxi = len(event["description"])
#             i = event["description"]
#     except KeyError:
#         if len(event["desc"]) > maxi:
#             maxi = len(event["desc"])
#             i = event["desc"]


cnx = mysql.connector.connect(user='test', password='1234',
                              host='159.65.84.145', database="app")
cursor = cnx.cursor()
count = 0
for event in data:
    try:
        check = check_name(event["location"], cursor)
        if check:
            count += 1
            print check, "THIS RIGHT HERE"
    except:
        continue

print count
cursor.close()
cnx.close()