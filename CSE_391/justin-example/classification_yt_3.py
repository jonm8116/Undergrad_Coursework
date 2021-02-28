import tensorflow as tf
from tensorflow import keras
from tabulate import tabulate
import numpy as np
import urllib
import json

def getApiKey(filename):
    api_key_file = open(filename, 'r')
    return api_key_file.read().rstrip()

def standardizeTuple(t, d):
    t -= np.mean(d, axis=0)
    t /= np.std(d, axis=0)
    return t

def standardize(a):
    d = np.array(a)
    d -= np.mean(d, axis=0)
    d /= np.std(d, axis=0)
    return np.array(d)

def getOneVideoStats(video_id, api_key):
    searchUrl="https://www.googleapis.com/youtube/v3/videos?id="+video_id+"&key="+api_key+"&part=statistics,snippet,content_details"
    response = urllib.request.urlopen(searchUrl).read()
    data = json.loads(response)
    try:
        viewCount = data['items'][0]['statistics']['viewCount']
        likeCount = data['items'][0]['statistics']['likeCount']
        dislikeCount = data['items'][0]['statistics']['dislikeCount']
        commentCount = data['items'][0]['statistics']['commentCount']
        return [[viewCount,likeCount,dislikeCount,commentCount]]
    except (KeyError, IndexError):
        return

def main():
    nontrending_stats_non = np.load('nontrending_stats.npy')
    trending_stats_non = np.load('trending_stats.npy')
    # combine trending and nontrending data, then standardize them
    all_data = np.concatenate((trending_stats_non[0:950,:], nontrending_stats_non[0:950]), axis=0)
    standardized_data = standardize(all_data)
    # sample tuple for a random video (views, likes, dislikes, commentCount)
    exampleTuple = [[1713501,84894,2855,15155]]
    exampleTuple = standardizeTuple(exampleTuple, all_data)
    # split em up
    trending_stats = standardized_data[0:950,:]
    nontrending_stats = standardized_data[951:,:]
    # curate sets of data necessary for neural net
    train_data = np.concatenate((trending_stats[0:400,:], nontrending_stats[0:400,:]), axis = 0)
    test_data = np.concatenate((trending_stats[401:801,:], nontrending_stats[401:801,:]), axis = 0)
    train_labels = np.concatenate((np.ones(400), np.zeros(400)), axis = 0)
    test_labels = np.concatenate((np.ones(400), np.zeros(400)), axis = 0)


    model = keras.Sequential([
        keras.layers.Dense(512, input_dim=4,activation=tf.nn.relu),
        keras.layers.Dense(512, activation=tf.nn.relu),
        keras.layers.Dense(512, activation=tf.nn.relu),
        keras.layers.Dense(2, activation=tf.nn.softmax)
    ])

    model.compile(optimizer=tf.train.AdamOptimizer(),
                    loss ='sparse_categorical_crossentropy',
                    metrics=['accuracy'])

    model.fit(train_data, train_labels, epochs=50)

    test_loss, test_acc = model.evaluate(test_data, test_labels)

    print("\n\nTest Set Accuracy:" + str(test_acc))


    # extra code so you can test individual video ids and see if trending or nontrending
    apiKey = getApiKey('api_key.txt')
    videoId = ''
    while videoId != 'quit':
        print("Predict a video by entering a video id (or type 'quit' to exit): ")
        videoId = input()
        video = np.array(getOneVideoStats(videoId, apiKey), dtype='|S10').astype(float)
        # print table of video data
        print("\n" + tabulate(video, headers=['ViewCount', 'LikeCount', 'DislikeCount', 'CommentCount']))
        video = standardizeTuple(video, all_data)
        # if video data was empty dont do a prediction
        if (len(video) != 0):
            prediction = model.predict(video)
            # print table of prediction probabilities
            print("\n" + tabulate(prediction, headers=['P(Non-Trending)', 'P(Trending)']))
            # print prediction for video
            if (np.argmax(prediction) == 1):
                print("\nPredicted Trending Video\n")
            else:
                print("\nPredicted Non-Trending Video\n")
        else:
            print("Video missing views, likes, dislikes, or commentCount...")


main()
