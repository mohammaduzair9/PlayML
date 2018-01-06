package playml.model;

import java.util.List;

/**
 *
 * @author UZAIR
 */
public class Sentiments {

    private List<String> statements;
    private List<Integer> sentiments;

    public void setStatements(List<String> statements){
        this.statements = statements;
    }

    public List<String> getStatements(){
        return this.statements;
    }

    public void setSentiments(List<Integer> sentiments){
        this.sentiments = sentiments;
    }

    public List<Integer> getSentiments(){
        return this.sentiments;
    }



}
