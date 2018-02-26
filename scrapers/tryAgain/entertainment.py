from bs4 import BeautifulSoup as Soup
import requests


def get_links():
    my_url = "http://entertainment.ie/music/listings/"
    page_raw = requests.get(my_url)
    page_html = Soup(page_raw.content, 'html5lib')
    for i in range(6):
        for link in page_html.find_all("a", {"class": "blcklink"}):
            print "http://www.entertainment.ie" + link.get('href')
            # yield "http://www.entertainment.ie" + link.get('href')
        print("______________________________________________")
        my_url = "http://www.entertainment.ie" + page_html.find("a", {"title": "Next page of events"}).get("href")
        page_raw = requests.get(my_url)
        page_html = Soup(page_raw.content, 'html5lib')


get_links()

