class Events(object):

	def __init__(self,event_id,name="",description="",time="",date="",ticket_url="",location="",pub_id=""):
		self.event_id = event_id
		self.name = name
		self.description = description
		self.time = time
		self.date = date
		self.ticket_url = ticket_url
		self.location = location
		self.pub_id = pub_id

	def __str__(self):
		return self.name

class Pubs(object):

	def __init__(self,pub_id,name="",description="",location=""):
		self.pub_id = pub_id
		self.name = name
		self.description = description
		self.location = location