package phedmobile.phed.mobile.com.phedmobile.tools;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import phedmobile.phed.mobile.com.phedmobile.activities.LoginSimpleGreen;
import phedmobile.phed.mobile.com.phedmobile.model.User;

/**
 * Created by Belal on 9/5/2017.
 */

//here for this class we are using a singleton pattern

public class SharedPrefManager {

    //the constants
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_FULLNAME = "keyfullname";
    private static final String KEY_PHONENUMBER = "keyphoneno";
    private static final String KEY_ID = "keyid";
    private static final String KEY_APIKEY = "APIKey";
    private static final String KEY_ACCOUNTNO = "AccountNo";
    private static final String KEY_ACCOUNTTYPE = "AccountType";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_APIKEY, user.getApikey());
        editor.putString(KEY_ACCOUNTNO, user.getAccountnumber());
        editor.putString(KEY_ACCOUNTTYPE, user.getAccounttype());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_APIKEY, null),
                sharedPreferences.getString(KEY_ACCOUNTNO, null),
                sharedPreferences.getString(KEY_ACCOUNTTYPE,null),
                sharedPreferences.getString(KEY_EMAIL, null)
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginSimpleGreen.class));
    }
}