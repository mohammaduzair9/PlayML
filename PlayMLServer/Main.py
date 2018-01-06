from Training import SentimentTraining
import pandas as pd                     # import pandas


def main():

    train = pd.DataFrame({'sentiment': [1,0,1,0], 'statement': ['i am happy', 'hate you', 'miss u','go away']})
    test = pd.DataFrame({'statement': ['i am happy']})

    obj = SentimentTraining(train)
    obj.train_naive_bayes()
    sentiment = obj.predict(test)

    print(sentiment)


if __name__ == "__main__":
    main()