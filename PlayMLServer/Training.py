import re                               # import reguar expressions
import pandas as pd                     # import pandas
from bs4 import BeautifulSoup           # import beautiful soap
from nltk.corpus import stopwords       # import stopwords from nltk
import numpy as np
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.naive_bayes import MultinomialNB


class SentimentTraining:

    data = pd.DataFrame(columns=['sentiments','statements'])
    cleaned_data = []
    labels = []
    data_size = 0
    trainingData = []
    trainingLabels = []
    clf = MultinomialNB(alpha=0.00005)  # alpha=0 means no laplace smoothing
    vectorizer = CountVectorizer(analyzer="word", tokenizer=None, preprocessor=None, stop_words=None, max_features=5000)

    def __init__(self):
        data_size =0

    def set_data(self,data):
        self.data = data
        self.labels = data['sentiments']
        self.data_size = data["statements"].size


    def clean_train_data(self, dataSentiment):
        # 1. Remove HTML
        raw_no_html = BeautifulSoup(dataSentiment, "html.parser")
        statements = raw_no_html.get_text()
        # 2. Remove non-letters
        clean_punc_statements = re.sub("[^a-zA-Z]", " ", statements)
        # 3. Convert to lower case, split into individual words
        statement_words = clean_punc_statements.lower().split()
        # 4. Remove stop words
        stops = set(stopwords.words('english'))
        nostop_statement_words = [w for w in statement_words if not w in stops]
        # 5. Joint back and return the joined sentence
        statement_sentence = " ".join(nostop_statement_words);
        return (statement_sentence)


    def train_naive_bayes(self):


        #Cleaning Sentiments
        for i in range(0, self.data_size):
            print("Cleaned Reviews : %d" % i)
            self.cleaned_data.append(self.clean_train_data(self.data["statements"][i]))

        self.labels.append(self.data["sentiments"])
        print("\nCreating the bag of words...")
        data_features = self.vectorizer.fit_transform(self.cleaned_data)
        data_features = data_features.toarray()

        print ("Printing Vocabulary :")
        print (self.vectorizer.vocabulary_)

        # Fitting the model to Naive Bayes Classifier
        self.clf.fit(np.array(data_features), np.array(self.labels))
        self.cleaned_data = []


    def predict(self,predict_data):

        clean_predict_data = []
        print("\nCleaning Prediction...")
        for i in range(0, predict_data['statements'].size):
            print("Cleaned Prediction : %d" % i)
            clean_predict_data.append(self.clean_train_data(predict_data["statements"][i]))

        data_features = self.vectorizer.transform(clean_predict_data)
        data_features = data_features.toarray()
        sentiment = self.clf.predict(np.array(data_features))
        return sentiment

