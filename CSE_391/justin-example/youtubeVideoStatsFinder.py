import pandas as pd
import numpy as np
import requests
import json
import urllib
import time

# define number of video ids to be grabbed in api request at a time
groupSize = 50

def getApiKey(filename):
    api_key_file = open(filename, 'r')
    return api_key_file.read().rstrip()

def getListGroupIds(filename):
    # load dataframe from csv
    df = pd.read_csv(filename, error_bad_lines=False)
    # drop null values
    df = df.dropna(axis=0, how='any')
    # purely get 1st column
    videoIds = df.iloc[:, 1]
    # convert to 1d array
    videoIds = videoIds.ravel()

    individualStr = ""
    listGroupsOfIds = []
    i = 0
    for videoId in videoIds:
        if (i == 0):
            individualStr += videoId
            i += 1
        else:
            if (i < groupSize):
                i += 1
                individualStr += ',' + videoId
            else:
                listGroupsOfIds.append(individualStr)
                individualStr = ""
                i = 0
    return listGroupsOfIds

class videoDetails:
    def __init__(self):
        self.videoId = []
        self.videoTitle = []
        self.channelTitle = []
        self.viewCount = []
        self.likeCount = []
        self.dislikeCount = []
        self.favoriteCount = []
        self.commentCount = []
        self.publishedAt = []
        self.categoryId = []
        self.contentDuration = []
        self.tags = []

def getVideoDetails(video_id, detailsClass, api_key):
    try:
        searchUrl="https://www.googleapis.com/youtube/v3/videos?id="+video_id+"&key="+api_key+"&part=statistics,snippet,content_details"
        response = urllib.urlopen(searchUrl).read()
        data = json.loads(response)
        for i in range(0, groupSize):
            detailsClass.videoId.append(data['items'][i]['id'])
            detailsClass.videoTitle.append(data['items'][i]['snippet']['title'])
            detailsClass.channelTitle.append(data['items'][i]['snippet']['channelTitle'])
            detailsClass.viewCount.append(data['items'][i]['statistics']['viewCount'])
            detailsClass.likeCount.append(data['items'][i]['statistics']['likeCount'])
            detailsClass.dislikeCount.append(data['items'][i]['statistics']['dislikeCount'])
            detailsClass.favoriteCount.append(data['items'][i]['statistics']['favoriteCount'])
            detailsClass.commentCount.append(data['items'][i]['statistics']['commentCount'])
            detailsClass.contentDuration.append(data['items'][i]['contentDetails']['duration'])
            detailsClass.publishedAt.append(data['items'][i]['snippet']['publishedAt'])
            detailsClass.categoryId.append(data['items'][i]['snippet']['categoryId'])
            try:
                detailsClass.tags.append(data['items'][i]['snippet']['tags'])
            except (IndexError, KeyError):
                detailsClass.tags.append([])
    except (IndexError, KeyError):
        return

def detailsObjectToArray(detailsObject):
    return zip(detailsObject.viewCount, detailsObject.likeCount,
        detailsObject.dislikeCount, detailsObject.commentCount,
        detailsObject.publishedAt, detailsObject.categoryId, detailsObject.tags)

def main():
    start_time = time.time()
    print("Getting video statistics...")
    apiKey = getApiKey('api_key.txt')

    listGroupsOfTrendingIds = getListGroupIds('trending-yt.csv')
    listGroupsOfNonTrendingIds = getListGroupIds('nontrending-yt.csv')
    # initialize trending and nontrending details objects
    nontrendingDetails = videoDetails()
    trendingDetails = videoDetails()
    # modify 2nd parameter of slice to specify number of groups of 50 to query
    sliceTrending = slice(0, len(listGroupsOfTrendingIds))
    sliceNonTrending = slice(0, len(listGroupsOfNonTrendingIds))

    # get video stats for each list of videos
    for group in listGroupsOfNonTrendingIds[sliceNonTrending]:
        getVideoDetails(group, nontrendingDetails, apiKey)
    for group in listGroupsOfTrendingIds[sliceTrending]:
        getVideoDetails(group, trendingDetails, apiKey)

    # convert class objects to tuples
    nontrending_details = detailsObjectToArray(nontrendingDetails)
    trending_details = detailsObjectToArray(trendingDetails)

    print "\n\nNontrending video details \n ------------------------- \n"
    for details in nontrending_details:
        print details

    print "\n\nTrending video details \n ------------------------- \n"
    for details in trending_details:
        print details
    print "Took ", time.time() - start_time, " seconds."



main()
