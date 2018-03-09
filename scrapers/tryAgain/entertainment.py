from bs4 import BeautifulSoup as Soup
import requests


def get_date_time(line):
    if "am" in line or "pm" in line:
        time = [deat for deat in line.split(" ") if "am" in deat or "pm" in deat][0] #XX.XXam/pm
        if "pm" in time:
            time = time[:-2]
            # hour, minute = str(int(time[:-2].split(".")[0])+12), str(time[:-2].split(".")[1])
            hour, minute = time.split(".")
            hour = int(hour) + 12
            if int(hour) > 23:
                hour = int(hour)-12
        else:
            time = time[:-2]
            hour, minute = (time.split("."))
            if int(hour) == 12:
                hour = int(hour) -12
    else:
        hour, minute = ("", "")
    if "-" in line:
        line = " ".join(line.split("-")[:1]).strip()
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
    for i, j in enumerate(months):
        if j in line:
            month = i+1
    day, year = [deat for deat in line.split(" ") if deat.isdigit()]
    return "-".join([str(year), str(month), str(day)]) + " " + str(hour)+":"+str(minute)


def get_links():
    my_url = "http://entertainment.ie/music/listings/"
    page_raw = requests.get(my_url)
    page_html = Soup(page_raw.content, 'html5lib')
    next_pages = page_html.find("span", {"class": "paginglinks"})
    for page in next_pages.find_all("a")[:2]:
        for link in page_html.find_all("a", {"class": "blcklink"})[1:]:
            # print "http://www.entertainment.ie" + link.get('href')
            yield "http://www.entertainment.ie" + link.get('href')
        print("______________________________________________")
        my_url = "http://entertainment.ie" + page.get("href").replace(" ", "%20") # page_html.find("a", {"title": "Next page of events"}).get("href")
        page_raw = requests.get(my_url)
        page_html = Soup(page_raw.content, 'html5lib')


def get_info():
    for link in get_links():
        headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36'}
        page_raw = requests.get(link, headers=headers)
        page_html = Soup(page_raw.content, 'html5lib')
        block = page_html.find("div", {"class": "listing maincol"})
        name = block.find("h1")
        location = block.find("p", {"class": "venue"})
        time = location.next_sibling
        tickets = block.find("span", {"class": "ticketsInfo"})
        desc = page_html.find("div", {"class": "sharing"}).next_sibling.next_sibling
        datetime = get_date_time(time.text.strip())
        if tickets:
            tickets = (tickets.text.split(" ")[-1]).encode("utf-8")
        else:
            tickets = ""
        if desc:
            desc = desc.text.strip()
        else:
            desc = ""
        link = ""
        print("ENTERTAINMENT")
        yield name.text, location.text, tickets, desc, datetime, link



# for l in  get_info():
#     print l
#     break
# YYYY-MM-DD HH:MM


# print(get_date_time("Thu 01 March 2018 - Sun 04 March 2018 8.00pm"))