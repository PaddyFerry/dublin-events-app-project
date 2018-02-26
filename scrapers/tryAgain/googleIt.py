from bs4 import BeautifulSoup as Soup
import requests as re


def google_it(place):
    url = "https://www.google.ie/search?q="
    headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36'}
    raw = re.get(url + place + "+dublin" + "&cr=IE")
    with open("fuck.html", "w") as f:
        f.write(raw.content)
    html = Soup(raw.content, "html5lib")
    result = html.find("div", {"class": "_uXc hp-xpdbox"})
    phone_add = tuple(i.get_text() for i in (result.findAll("span", {"class": "_tA"}))
                 if "Dublin" in i.get_text() or "(01)" in i.get_text())
    if len(phone_add) > 1:
        phone = phone_add[1]
        address = phone_add[0]
    else:
        phone = ""
        address = phone_add[0]
    return (result.find("div", {'class': "_B5d"}).get_text(),
            result.find("span", {"class": "_kgd"}).get_text(),
            result.find("div", {"class": "_POh"}).get_text(),
            phone,
            address)

test = google_it("blackbird")
print test


