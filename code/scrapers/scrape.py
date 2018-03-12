import database
import eventbrite
import entertainment
import threading
import runner


def run_with(func):
    db = database.Database("test", "1234", "159.65.84.145", "app")
    with db:
        db.run(func)

threading.Thread(target=run_with, args=(entertainment.get_info_entertainment(),)).start()
threading.Thread(target=run_with, args=(runner.run(),)).start()
threading.Thread(target=run_with, args=(eventbrite.get_info_eventbrite(),)).start()

