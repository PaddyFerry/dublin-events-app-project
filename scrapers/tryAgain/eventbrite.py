from __future__ import division
from bs4 import BeautifulSoup as soup
import requests
from unidecode import unidecode


urls = ["https://www.eventbrite.ie/d/ireland--dublin/music--events/",
        "https://www.eventbrite.ie/d/ireland--dublin/arts--events/",
        "https://www.eventbrite.ie/d/ireland--dublin/science-and-tech--events/",
        "https://www.eventbrite.ie/d/ireland--dublin/business--events/",
        "https://www.eventbrite.ie/d/ireland--dublin/health--events/",
        "https://www.eventbrite.ie/d/ireland--dublin/family-and-education--events/",
        "https://www.eventbrite.ie/d/ireland--dublin/free--events/",
        "https://www.eventbrite.ie/d/ireland--dublin/food-and-drink--events/"]

#https://www.eventbrite.ie/d/ireland--dublin/music--events/?crt=regular&page=2&sort=best


def get_event_link(url):
    re = requests.get(url)
    html = soup(re.content, 'html5lib')

    x = html.find('section', {'class': 'l-mar-top-3 js-search-content g-vertical-group g-cell-1-1 g-cell-lg-9-12 '})

    events = (x.find_all("a", {'data-automation': "event-card"}))
    links = []
    for event in events:
        links.append(event.get('href'))
    return links


def get_next_pages(url):
    return [(url+"?crt=regular&page="+str(i)+"&sort=best") for i in range(2, 4)]


def get_all_links(bigList):
    all_links = []
    for url in bigList:
        for final_link in ([url]+get_next_pages(url)):
            all_links.append(final_link)
    linkys = []
    for links in all_links:
        for link in get_event_link(links):
            linkys.append(link)
    return linkys


def get_details(url):
    re = requests.get(url)
    html = soup(re.content, 'html5lib')
    name = html.h1.get_text()
    datetimelocation = html.find_all("div", {"class": "event-details__data"})[:2]
    datetime = datetimelocation[0].find("meta")["content"].split("+")
    datetime = datetime[0].replace("T", " ")
    location = datetimelocation[1].p.text
    tickets = html.find("div", {"class": "js-display-price"}).text.strip()
    tickets = tickets.replace(u" \u2013 ", "/")
    # tickets = tickets.replace(u"\u20ac", u"\xe2\x82\xac")
    desc = html.find("div", {"data-automation": "listing-event-description"}).text.strip()
    if len(desc) > 900:
        desc = desc[:899]
    piclink = html.find("picture")["content"]
    return name, location, tickets, desc, datetime, piclink



def get_info():
    l = get_all_links(urls[1:3])
    for link in l:
        try:
            yield get_details(link)
        except:
            continue
