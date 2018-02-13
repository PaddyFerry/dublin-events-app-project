from collections import Counter
import json

f = open("test.json", "r")

data = json.loads(f.read())

countVenues = Counter(l["location"] for l in data)
countDates = Counter(l["time"] for l in data)

# for i in data:
#     print i["date"], i["location"], "|", i["scraper"]
print (Counter(l["scraper"] for l in data))
# for event in data:
#     if event["date"] == "2018-02-08":
#         print event["name"],"||",  event["location"], "||", event["scraper"]
# for i in countDates.most_common():
#     print i[0],i[1]

# print(countVenues.most_common(2))
# # (countVenues.most_common())
# for i in countVenues.most_common():
#     print i[1], i[0]
