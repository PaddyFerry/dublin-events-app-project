from bs4 import BeautifulSoup
import urllib2,io,facebook


# view = urllib2.urlopen("https://www.visitdublin.com/see-do/eat-drink-nightlife/bars-and-clubs?pa=0&pp=100&pg=2").read()
# soup = BeautifulSoup(view,'lxml')
# res = soup.find('ul',{"class":"no-bullet listing-results"})
# list_of_pubs = soup.find('div',class_='large-9 small-12 columns ng-scope')
# descriptions = res.find_all('h3')
# for desc in descriptions:
# 	file.write(desc.getText()+"\n")
# file.close()

graph = facebook.GraphAPI(access_token="EAANzGl7D69MBAEzqZB5n2oAFSxzf6YrY3bDgJoXSdmNNn73v4mtwCZCw6qQmuRY78n3QZAmBuyMRePFo4LJCdOE3mCHVcDOZB0otFKjFOZCO3cja9Km4ZAF2eciXlFIKr536h6gLwZBJJZBZCTdBlNKCBrAGEiVzxwonBJgA9bIrO9wZDZD", version="2.11")

file = io.open("out.txt","a",encoding="utf8")
with open("out.txt") as f:
	for line in f:
		line = line.strip()
		page_id = graph.search(type='place',q =line,limit= 1)
		#try:
		file.write((line+"-"+page_id['data'][0]['id']+"\n"))
		#except:
		print page_id
file.close()