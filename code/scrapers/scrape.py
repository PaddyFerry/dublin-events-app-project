import database
import eventbrite
import entertainment
import threading
import sys
import runner


db = database.Database("test", "1234", "159.65.84.145", "app")
if sys.argv[1].lower() == "entertainment":
    with db:
        db.run(entertainment.get_info())

elif sys.argv[1].lower() == "eventbrite":
    with db:
        db.run(eventbrite.get_info())

elif sys.argv[1].lower() == "facebook":
    with db:
        db.run(runner.run())

