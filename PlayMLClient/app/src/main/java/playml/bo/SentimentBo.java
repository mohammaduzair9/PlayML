package playml.bo;


import org.springframework.web.client.RestTemplate;

import playml.model.Sentiments;

public class SentimentBo {

    public static final String REST_SERVICE_URI = "http://10.0.2.2:8080/playml";
    RestTemplate restTemplate = new RestTemplate();

    /* POST SENTIMENT OBJECT TO SERVER FOR TRAINING*/
    public Sentiments postSentiment(Sentiments sen){

        Sentiments s = null;
//        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//        s = restTemplate.postForObject(REST_SERVICE_URI+"/sentiment/train/" , sen , Sentiments.class);

        return s;
    }

    /* POST SENTIMENT TO SERVER FOR PREDICTION*/
    public int getPrediction(String s){


//        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//        int r = restTemplate.postForObject(REST_SERVICE_URI+"/sentiment/predict/" , s , Integer.class);

        return 1;
    }
}
