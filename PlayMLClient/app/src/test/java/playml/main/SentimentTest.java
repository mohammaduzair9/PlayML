package playml.main;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import playml.model.Sentiments;
import playml.model.User;

import static org.junit.Assert.assertEquals;

public class SentimentTest {

    @Test
    public void sentimentset() throws Exception {

        Sentiments Sen = new Sentiments();
        List<String> s = new ArrayList<>();
        s.add("asd");
        Sen.setStatements(s);

        assertEquals(Sen.getStatements(), s);
    }
}