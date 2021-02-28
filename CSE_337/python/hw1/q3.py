from bs4 import BeautifulSoup
from urllib.request import urlopen

url = 'https://finance.google.com/finance/market_news'

articlelist = []
#order: title, source,date

def scanDivs(url):
	client = urlopen(url)
	page_html = client.read()
	client.close()
	page_soup = BeautifulSoup(page_html, 'html.parser')
	divs = page_soup.findAll("div", {"class": "g-section news sfe-break-bottom-16"})
	
	title=''
	source=''
	date=''
	#file to write to
	writefile = open('top10articles.txt', 'w')
	
	article=[]
	for div in divs:
		titlestr = div.span.text
		titlestr.replace('\n', '')
		title = titlestr
		
		source = div.a['href']
		source = source.split('url=')[1]
		datestr = (div.div.text).replace('\n', '')
		properdate = datestr.split("-")
		date=properdate[1]
		try:
			writefile.write(title+","+source+","+date)
		except UnicodeEncodeError:
			pass
		
		article.append(title)
		article.append(source)
		article.append(date)
		articlelist.append(article)
		
	#print(articlelist)
	writefile.close()
	
scanDivs(url)