from bs4 import BeautifulSoup
import urllib2,io,facebook


# view = urllib2.urlopen("https://www.visitdublin.com/see-do/eat-drink-nightlife/bars-and-clubs?pa=0&pp=100&pg=2").read()
# soup = BeautifulSoup(view,'lxml')
# res = soup.find('ul',{"class":"no-bullet listing-results"})
# list_of_pubs = soup.find('div',class_='large-9 small-12 columns ng-scope')
# descriptions = res.find_all('h3')
# listy = []
# for desc in descriptions:
# 	file.write(desc.getText()+"\n")
# file.close()

graph = facebook.GraphAPI(access_token="EAANzGl7D69MBAEzqZB5n2oAFSxzf6YrY3bDgJoXSdmNNn73v4mtwCZCw6qQmuRY78n3QZAmBuyMRePFo4LJCdOE3mCHVcDOZB0otFKjFOZCO3cja9Km4ZAF2eciXlFIKr536h6gLwZBJJZBZCTdBlNKCBrAGEiVzxwonBJgA9bIrO9wZDZD", version="2.11")

places = graph.search(type='page',q='Whelans')
print places
