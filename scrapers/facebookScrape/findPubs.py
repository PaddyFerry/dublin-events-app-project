from bs4 import BeautifulSoup
import urllib2,io,facebook

file = io.open("out.txt","a",encoding="utf8")
view = urllib2.urlopen("https://www.visitdublin.com/see-do/eat-drink-nightlife/bars-and-clubs?pa=0&pp=100&pg=2").read()
soup = BeautifulSoup(view,'lxml')
res = soup.find('ul', {"class":"no-bullet listing-results"})
list_of_pubs = soup.find('div',class_='large-9 small-12 columns ng-scope')
descriptions = res.find_all('h3')
for desc in descriptions:
    file.write(desc.getText()+"\n")
file.close()
#
# graph = facebook.GraphAPI(access_token="""EAANzGl7D69MBAMd3ooPuxbDH58EUQkI7e05ZA2z3fIby6JD9yOFi32jDRqKIGrQkUQW1dcm0s
#                                         4FVYE03d7xtxWQKBnIGeRY2XNPcWrsjVWNZBfzXku46xkaViNTU2Hj2qxxak8KPjhXS9t
#                                         t8ZByY3ZCHiICsc2Q5tPq1n7ZBW3bEHo9sYIx8E""",
#                           version="2.11")
#
# file = io.open("out.txt","a",encoding="utf8")
# with open("out.txt") as f:
#     for line in f:
#         print("hi")
#         line = line.strip()
#         place_results = 0
#         page_results = 0
#         try:
#             place = graph.search(type='place', q=line, limit=1)
#             place_id = place["data"][0]["id"]
#             obj = graph.get_object(id=place_id,fields="location")
#             place_results = obj.get("location")
#             print("Trying place")
#             if place_results is not None:
#                 if "Ireland" in place_results.values():
#                     file.write((line+"-"+place['data'][0]['id']+"\n"))
#                     print("Added")
#                     print(place_results)
#
#                 else:
#                     place = graph.search(type='page', q=line, limit=1)
#                     page_id = place["data"][0]["id"]
#                     obj = graph.get_object(id=page_id,fields="location")
#                     page_results = obj.get("location")
#                     print("Trying page")
#                     if page_results is not None:
#                         if "Ireland" in page_results.values():
#                             file.write((line+"-"+place['data'][0]['id']+"\n"))
#                             print("Added")
#                             print(page_results)
#
#                     else:
#                         print("Not in Ireland")
#
#         except IndexError:
#             try:
#                 place = graph.search(type='page', q=line, limit=1)
#                 place_id = place["data"][0]["id"]
#                 obj = graph.get_object(id=place_id,fields="location")
#                 page_results = obj.get("location")
#                 print("Skip place trying page")
#                 if page_results is not None:
#                     if "Ireland" in page_results.values():
#                         file.write((line+"-"+place['data'][0]['id']+"\n"))
#                         print(page_results)
#                         print("Added")
#
#             except IndexError:
#                 print "No Info found"
#                 continue
# file.close()