from bs4 import BeautifulSoup
import urllib

class enterScrape(object):

	def __init__(self,url=""):
		self.url = url

	def getLink(self):
		linkList = []
		soup = BeautifulSoup(self.url,'html.parser')
		linkDiv = soup.find_all('a',class_ = 'blcklink')
		urls = 'http://entertainment.ie'
		for i in linkDiv:
			linkList.append(urls + i.get('href'))
		return linkList

	def getInfo(self,url):
		soup = BeautifulSoup(url,'html.parser')
		linkDiv = soup.find_all('div',class_ = 'eventDetails')
		for i in linkDiv:
			print i.p
		return linkDiv


def main():
	url = urllib.urlopen('http://entertainment.ie/music/listings/').read()
	scrp = enterScrape(url)
	links = scrp.getLink()
	url = urllib.quote(links[0].encode('utf8'), ':/')
	testyLink = urllib.urlopen(url).read()
	print scrp.getInfo(testyLink)


if __name__ == '__main__':
	main()