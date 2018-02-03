import facebook,sys,eventClass,sqlite3
from datetime import date
from datetime import datetime
from os import path
sys.path.append(path.dirname(path.dirname(path.abspath(__file__))))
from facebookScrape.faceScrape import FacebookScraper
from sqliteDB.sqliteDB import SqliteDB


def main():
	conn = sqlite3.connect('event.db')
	conn.text_factory = str
	cur = conn.cursor()
	database = SqliteDB(cur)
	database.create_db()

	face = FacebookScraper(facebook.GraphAPI(access_token="EAANzGl7D69MBAEzqZB5n2oAFSxzf6YrY3bDgJoXSdmNNn73v4mtwCZCw6qQmuRY78n3QZAmBuyMRePFo4LJCdOE3mCHVcDOZB0otFKjFOZCO3cja9Km4ZAF2eciXlFIKr536h6gLwZBJJZBZCTdBlNKCBrAGEiVzxwonBJgA9bIrO9wZDZD", version="2.11"))
	pub_ids = face.extract_ids("out.txt")

	for pub_id in pub_ids:

		pub_info = face.get_pubs(pub_id)
		location = face.get_location(pub_id)
		pub_description = face.get_pub_description(pub_id).encode("utf-8")
		pub = eventClass.Pubs(pub_id,pub_info[0],pub_description,location)
		database.add_pub(pub)
		events = face.get_events(pub_id)

		if len(events) != 0:

			for event in events["data"]:
				event_date_and_time = face.get_date_and_time(event["start_time"].split("T"))
				num_days = face.date_diff(event_date_and_time[0])

				if num_days >= 0 and num_days <= 7:
					event = eventClass.Events(event.get("id"),event.get("name"),event.get("description"),event_date_and_time[1],event_date_and_time[0],event.get("ticket_uri"),location," ",pub_id)
					database.add_events(event)

	conn.commit()
	database.print_all_pubs()
	conn.close()





if __name__ == '__main__':
	main()