from bs4 import BeautifulSoup
import urllib

class enterScrape(object):

	def __init__(self,url=""):
		self.url = url

	def getLink(self):
		linkList = []
		soup = BeautifulSoup(self.openLink(),'html.parser') # open the link
		linkDiv = soup.find_all('a',class_ = 'blcklink')
		parentUrl = self.url.rsplit("/",3) #show the link with no extra pages
		for i in linkDiv:
			linkList.append(parentUrl[0]+ i.get('href'))
		return linkList

	def getInfo(self,url):
		soup = BeautifulSoup(url,'html.parser')
		linkDiv = soup.find('div',class_ = 'listing maincol')
		name = soup.find('div',class_ = 'eventDetails')
		print name.find('h1').getText()
		ps = linkDiv.find_all('p')
		x = [x.getText().split('\n') for x in ps]
		i = 0
		while i < 6:
			try:
				print x[i][0]
				i += 1
			except:
				break


		return linkDiv

	def openLink(self):
		return urllib.urlopen(self.url).read() #open the link and view as HTML


def main():
	url ='http://entertainment.ie/music/listings/'
	scrp = enterScrape(url)
	links = scrp.getLink()
	for i in links:
		url = urllib.quote(i.encode('utf8'), ':/')
		encodedLink = urllib.urlopen(url).read()
		scrp.getInfo(encodedLink)


if __name__ == '__main__':
	main()