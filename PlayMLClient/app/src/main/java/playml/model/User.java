package playml.model;

/**
 *
 * @author UZAIR
 */
public class User {

    private int id;
    private String username;
    private String password;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setUserName(String username){
        this.username = username;
    }

    public String getUserName(){
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }


}
