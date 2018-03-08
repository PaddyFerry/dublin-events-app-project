from __future__ import division
from bs4 import BeautifulSoup as soup
import requests, json


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
    return [(url+"?crt=regular&page="+str(i)+"&sort=best") for i in range(2,6)]

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


def get_info(url):
    re = requests.get(url)
    html = soup(re.content, 'html5lib')

    name = (html.h1.get_text())
    location = (html.find('a', {'href': '#listing-organizer'})).get_text().strip()[3:]
    timegroup = (html.find('time', {'data-automation': 'event-details-time'}))
    time = (timegroup.find_all('p')[1]).get_text().strip()[:-3]
    date = (html.find('time').get('datetime'))
    tickets = (html.find('div', {'class': 'js-panel-display-price'}).get_text().strip())
    desc = (html.find('div', {'data-automation': 'listing-event-description'}).get_text().strip())

    return {'name': name,
            'location': location,
            'time': time,
            'date': date,
            'tickets': tickets,
            'description': desc,
            "scraper": "eventbrite"}


def main():
    l = get_all_links(urls[:1])
    success = 0
    fails = 0
    dicts = []
    i = 0

    for link in l:
        print(str((i/len(l))*100)+"%")
        try:
            tt = get_info(link)
        except:
            print("FAILED ON :" + link)
            print("***********")
            fails += 1
            i += 1
            continue

        dicts.append(tt)
        success += 1
        i += 1

    print("fails: ", fails)
    print("successes: ", success)
    return dicts

if __name__ == '__main__':
    main()
