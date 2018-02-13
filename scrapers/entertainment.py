from __future__ import division
from bs4 import BeautifulSoup as soup
import requests


def get_links():
    my_url = "http://entertainment.ie/music/listings/"

    page_raw = requests.get(my_url)
    page_html = soup(page_raw.content, 'html.parser')

    urls = []

    for x in page_html.findAll("a",{"class":"blcklink"}):
        try:
            s = str(x.get('href'))
            s = 'http://entertainment.ie' + s
            urls.append(s)
        except:
            pass
    return(urls)


def convTime(time):
    if time[-2:] == "pm" and int(time[:-5])<12:
        return str(int(time[:-5])+12)+":" + time[-4:-2]
    else:
        return time[:-5] + ":" + time[-4:-2]


def convMonth(month):
    months = ['January',
              'February',
              'March',
              'April',
              'May',
              'June',
              'July',
              'August',
              'September',
              'October',
              'November',
              'December']
    for i,j in enumerate(months):
        if month == j:
            if i+1 < 10:
                return "0" + str(i+1)
            return str(i+1)


def time_date(test):
    test = test.split()
    if len(test) == 6:
        date = test[1:4]
        time = test[-1]
        newDate = str(date[2]) + "-"+ str(convMonth(date[1]))+"-" + str(date[0])
        return convTime(time), newDate
    else:
        date = test[1:4]
        newDate = str(date[2]) + "-" + str(convMonth(date[1])) + "-" + str(date[0])
        return None, newDate


def get_d(b):
    event = {"name": b[0],
             "location": b[1],
             "time": (time_date(b[2]))[0],
             "date": (time_date(b[2]))[1],
             "description": b[-1],
             "tickets": "",
             "scraper": "entertainment"}
    try:
        event["tickets"] = ([test for test in b if 'Ticket' in test][0]).split(" ")[-1]
    except:
        pass
    return event


def one_page(url):
    page = requests.get(url)
    html = soup(page.content, 'html5lib')

    div = html.find('div', {'class': 'listing maincol'})
    l = []
    l.append(div.h1.text)
    for line in div.find_all('p'):
        t = (line.text.strip())

        if t != "":
            l.append(t)
    return len(l), l


def main():
    links = get_links()
    dicts = []
    i = 0
    for link in links[3:]:
        a, b = one_page(link)
        print(str((i / len(links)) * 100) + "%")
        dicts.append(get_d(b))
        i += 1
    return dicts


if __name__ == '__main__':
    main()



