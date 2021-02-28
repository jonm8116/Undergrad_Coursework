import urllib, json 
import elasticsearch 
from elasticsearch import Elasticsearch, helpers

data = ""
es = Elasticsearch([{'host':'localhost', 'port':'9200'}])

url = "https://grading.cse356.compas.cs.stonybrook.edu/hw7/movies.json"
response = urllib.urlopen(url)
data = json.loads(response.read())

#es.index(index='hw7', ignore=400, body=data)
helpers.bulk(es, data, index='hw7', doc_type='my-type')
