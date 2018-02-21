import facebook
from datetime import date
from datetime import datetime
from geopy.geocoders import Nominatim

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

	def get_pub_description(self,pub_id):
		try:
			page = self.graph.get_object(id=pub_id,fields="description")
			return "".join(page["description"].split("\n"))

		except KeyError:
			return "No description available"

	def get_location(self,pub_id):
		pub_info = self.get_pubs(pub_id)
		location_list = pub_info[1].values()
		x = [x for x in location_list if ("-6") in unicode(x)]
		y = [y for y in location_list if ("53") in unicode(y)]
		geolocator = Nominatim()
		if x and y:
			loc = geolocator.reverse("{},{}".format(y[0],x[0]))
			return loc.address
		else:
		 return "No address info"

