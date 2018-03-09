import database
import eventbrite
import entertainment
import threading
import sys

# db = Database("test", "1234", "159.65.84.145", "app")
# with db:
#     for event in entertainment.get_info():
#         try:
#             db.check_add_event(event)
#         except UnicodeEncodeError as err:
#             print("Error : {}".format(err))
#             print("ERROR WITH", event)
#         print ("________________________________________")
db = database.Database("test", "1234", "159.65.84.145", "app")
if sys.argv[1].lower() == "entertainment":
    with db:
        db.run(entertainment.get_info())

elif sys.argv[1].lower() == "eventbrite":
    with db:
        db.run(eventbrite.get_info())


# threading.Thread(target=db.run, args=(eventbrite.get_info(),)).start()
# print("Lol")
# threading.Thread(target=db.run, args=(entertainment.get_info(),)).start()
