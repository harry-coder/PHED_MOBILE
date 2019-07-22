package phedmobile.phed.mobile.com.phedmobile.model;

/**
 * Created by Belal on 9/5/2017.
 */


//this is very simple class and it only contains the user attributes, a constructor and the getters
// you can easily do this by right click -> generate -> constructor and getters
public class User {

   private int id;
    private String fullname, username,phone,email,password;
    private String apikey;
    private String accountnumber;

    public String getApikey() {
        return apikey;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public String getAccounttype() {
        return accounttype;
    }

    private String accounttype;

    public User(int id, String fullname, String username, String phone, String email, String password) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
    public User(int id, String fullname, String username, String phone, String email) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.phone = phone;
        this.email = email;
    }
    public User(String apikey, String accountnumber, String accounttype) {

        this.fullname = apikey;
        this.username = accountnumber;
        this.phone = accounttype;

    }

    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


}