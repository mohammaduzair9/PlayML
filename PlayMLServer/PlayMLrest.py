from bottle import *
from Training import SentimentTraining
import pandas as pd
import numpy as np


obj = SentimentTraining()

@get('/playml')
def playml():
    return "<html>main page</html>"

@get('/playml/sentiment/predict/<statement>')
def predict(statement):
    response.content_type = 'application/json'
    test = pd.DataFrame({'statements': statement}, index=[0])
    sentiment = obj.predict(test)
    json_sentiment = pd.DataFrame({'sentiments': sentiment}).to_json(orient='records')
    return json_sentiment


@post('/playml/sentiment/train')
def train():
    print(request.json)
    train = pd.read_json(request.body, orient='records');
    obj.set_data(train)
    obj.train_naive_bayes()
    
run(host='10.99.23.175', port=8080, reloader=True, debug=True)