package practice.com.luminettestapp.Model;

public class User {

    private String user, pass;

    public User(){ }

    public User(String user, String pass){
        this.user = user;
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public String getUser() {
        return user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
