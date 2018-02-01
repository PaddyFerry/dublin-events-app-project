import facebook
from datetime import date
from datetime import datetime
class FacebookScraper(object):

	def __init__(self,graph):
		self.graph = graph

	def extract_ids(self,file_name):
		with open(file_name) as f:
			list_of_ids = []
			for line in f:
				line = line.strip().split("-")
				list_of_ids.append(line[1])
			return list_of_ids

	def get_events(self, id):
		try:
			page = self.graph.get_object(id=id, fields="events")
			return page["events"]
		except KeyError:
			return []


	def date_diff(self, event_date):
		now = datetime.now()
		eve_date = event_date.split("-")
		eve_date = date(int(eve_date[0]), int(eve_date[1]), int(eve_date[2]))
		cur_date = date(now.year, now.month, now.day)
		return (eve_date-cur_date).days


	def get_date_and_time(self, date_and_time):
		date = date_and_time[0]
		time = date_and_time[1].split(":")
		time = time[0]+":"+time[1]
		return(date, time)


	def get_pubs(self, id):
		try:
			page = self.graph.get_object(id=id, fields="name ,location", limit=1)
			return page["name"], page["location"]
		except KeyError:
			return []



def main():
	face = FacebookScraper(facebook.GraphAPI(access_token="EAANzGl7D69MBAEzqZB5n2oAFSxzf6YrY3bDgJoXSdmNNn73v4mtwCZCw6qQmuRY78n3QZAmBuyMRePFo4LJCdOE3mCHVcDOZB0otFKjFOZCO3cja9Km4ZAF2eciXlFIKr536h6gLwZBJJZBZCTdBlNKCBrAGEiVzxwonBJgA9bIrO9wZDZD", version="2.11"))
	page_ids = face.extract_ids("out.txt")
	for page_id in page_ids:
		events = face.get_events(page_id)
		if len(events) != 0:
			for event in events["data"]:
				event_date_and_time = face.get_date_and_time(event["start_time"].split("T"))
				num_days = face.date_diff(event_date_and_time[0])
				if num_days >= 0 and num_days <= 7:
					
					pub_info = face.get_pubs(page_id)
					location_list = pub_info[1].values()
					pub_string = pub_info[0]", "+location_list[0]+", "+location_list[2]+", "+location_list[4]+"."
					print event.get("name"),event.get("id"),event.get("description"),event_date_and_time[0],event_date_and_time[1],event.get("ticket_uri")

if __name__ == '__main__':
	main()