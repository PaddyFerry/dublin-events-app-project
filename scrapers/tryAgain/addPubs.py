import database

db = database.Database("test", "1234", "159.65.84.145", "app")

with db:
    # with open("pubs.txt", "r") as pubs:
    #     i = 0
    #     for pub in pubs.readlines()[50:]:
    #         db.check_add_venue(pub)
    #         i += 1
    #         if i == 75:
    #             break
    db.check_add_venue("Fibber Maggees")
