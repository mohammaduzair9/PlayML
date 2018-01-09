package playml.bo;


import android.os.AsyncTask;
import android.util.Log;
//import android.util.Log;

// import org.json.JSONArray;
// import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import playml.model.Predict;
import playml.model.Sentiments;

public class SentimentBo {
    public static final String REST_SERVICE_URI = "http://10.99.23.175:8080/playml";
    RestTemplate restTemplate = new RestTemplate();

    /* POST SENTIMENT OBJECT TO SERVER FOR TRAINING*/
    public void postSentiment(Sentiments sen){
        Sentiments s = null;
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        s = restTemplate.postForObject(REST_SERVICE_URI+"/sentiment/train" , sen , Sentiments.class);
    }

    /* POST SENTIMENT TO SERVER FOR PREDICTION*/
    public int getPrediction(String s){
        Log.v("predict",s);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Predict[] r = restTemplate.getForObject(REST_SERVICE_URI+"/sentiment/predict/"+s, Predict[].class);
        return r[0].getSentiments();
    }
}
