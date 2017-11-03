from bs4 import BeautifulSoup
import urllib

class enterScrape(object):

	def __init__(self,url=""):
		self.url = url

	def getLink(self):
		linkList = []
		soup = BeautifulSoup(self.openLink(),'html.parser')
		linkDiv = soup.find_all('a',class_ = 'blcklink')
		parentUrl = self.url.rsplit("/",3)
		for i in linkDiv:
			linkList.append(parentUrl[0]+ i.get('href'))
		return linkList

	def getInfo(self,url):
		soup = BeautifulSoup(url,'html.parser')
		linkDiv = soup.find_all('div',class_ = 'eventDetails')
		for i in linkDiv:
			print i.p
		return linkDiv

	def openLink(self):
		return urllib.urlopen(self.url).read()


def main():
	url ='http://entertainment.ie/music/listings/'
	scrp = enterScrape(url)
	links = scrp.getLink()
	url = urllib.quote(links[0].encode('utf8'), ':/')
	testyLink = urllib.urlopen(url).read()
	print scrp.getInfo(testyLink)


if __name__ == '__main__':
	main()