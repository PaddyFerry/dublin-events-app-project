import database
import eventbrite
import entertainment
import threading
import sys
import runner
import time


def run_with(func):
    db = database.Database("test", "1234", "159.65.84.145", "app")
    with db:
        db.run(func)

#
# threading.Thread(target=run_with, args=(entertainment.get_info(),)).start()


# db = database.Database("test", "1234", "159.65.84.145", "app")
# with db:
#     threading.Thread(target=db.run, args=(entertainment.get_info(),))

threading.Thread(target=run_with, args=(entertainment.get_info(),)).start()
threading.Thread(target=run_with, args=(runner.run(),)).start()
threading.Thread(target=run_with, args=(eventbrite.get_info(),)).start()
# db = database.Database("test", "1234", "159.65.84.145", "app")

# if sys.argv[1].lower() == "entertainment":
#     with db:
#         db.run(entertainment.get_info())
#
# elif sys.argv[1].lower() == "eventbrite":
#     with db:
#         db.run(eventbrite.get_info())
#
# elif sys.argv[1].lower() == "facebook":
#     with db:
#         db.run(runner.run())

