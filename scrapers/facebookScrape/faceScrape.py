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

	def get_events(self,id):
		try:
			page = self.graph.get_object(id=id,fields= "events")
			return page["events"]
		except KeyError:
			return []

	def date_diff(self,eve_date):
		now = datetime.now()
		eve_date = eve_date[0].split("-")
		eve_date = date(int(eve_date[0]),int(eve_date[1]),int(eve_date[2]))
		cur_date = date(now.year,now.month,now.day)
		return (eve_date-cur_date).days


def main():
	face = FacebookScraper(facebook.GraphAPI(access_token="EAANzGl7D69MBAEzqZB5n2oAFSxzf6YrY3bDgJoXSdmNNn73v4mtwCZCw6qQmuRY78n3QZAmBuyMRePFo4LJCdOE3mCHVcDOZB0otFKjFOZCO3cja9Km4ZAF2eciXlFIKr536h6gLwZBJJZBZCTdBlNKCBrAGEiVzxwonBJgA9bIrO9wZDZD", version="2.11"))
	page_ids = face.extract_ids("out.txt")
	for page_id in page_ids:
		events = face.get_events(page_id)
		if len(events) != 0:
			for event in events["data"]:
				date = event["start_time"].split("T")
				num_days = face.date_diff(date)
				if num_days >= 0 and num_days <= 7:
					try:
						print event["name"],event["description"],event["start_time"]
					except KeyError:
						print ("no info")

if __name__ == '__main__':
	main()