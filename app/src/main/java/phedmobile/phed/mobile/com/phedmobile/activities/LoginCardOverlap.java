package phedmobile.phed.mobile.com.phedmobile.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import phedmobile.phed.mobile.com.phedmobile.R;
import phedmobile.phed.mobile.com.phedmobile.model.User;
import phedmobile.phed.mobile.com.phedmobile.tools.SharedPrefManager;
import phedmobile.phed.mobile.com.phedmobile.tools.Tools;
import phedmobile.phed.mobile.com.phedmobile.tools.VolleySingleton;

public class LoginCardOverlap extends AppCompatActivity {

    private View parent_view;
    EditText et_userName,et_password;

 public  static    String Usersname ;
    public  static    String AccountType;
    public  static    String AccountNo;
    public  static    String PhoneNo;
    public  static    String APIKey;public  static    String CustomerEmail;

    private void createUser(String Username, String Password)
            throws JSONException {

        //having username and Password, Go to the Volley URL and validate

        HashMap datam = new HashMap();

        datam.put("CustomerEmail", Username);
        datam.put("CustomerPassword", Password);
       // http://phedmobile.nepamsonline.com/api/PHEDMobile/Login


        final RequestQueue requstQueue = Volley.newRequestQueue(getApplicationContext());


        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();


        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, "http://phedmobile.nepamsonline.com/api/PHEDMobile/Login",new JSONObject(datam),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (response.has("CustomerAPIKey") && !response.isNull("CustomerAPIKey")) {
                                // Do something with object.
                                APIKey    =  response.getString("CustomerAPIKey");
                                PhoneNo =  response.getString("CustomerPhone");
                                AccountNo =  response.getString("CustomerAccountNo");
                                AccountType   =  response.getString("AccountType");
                                Usersname   =  response.getString("CustomerName");
                                CustomerEmail =  response.getString("CustomerEmail");
                                User user = new User(response.getString("CustomerAPIKey"),
                                response.getString("CustomerAccountNo"),
                                response.getString("AccountType"));



                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                Intent intent = new Intent(getApplicationContext(), DashboardPayBill.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                pd.dismiss();

                                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                                finish();
                            }
                            else
                            {
                                pd.dismiss();
                                Toast.makeText(getApplicationContext(),"Invalid Username and Password",Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                        catch (JSONException e) {

                            System.out.println("ServerResponse"+ e.getMessage());
                            Log.e("ServerResponse",e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), "Communication Error!", Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getApplicationContext(), "Authentication Error!", Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getApplicationContext(), "Server Side Error!", Toast.LENGTH_LONG).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(getApplicationContext(), "Parse Error!", Toast.LENGTH_LONG).show();
                }
                else {
                    // VolleyLog.d(TAG, "Error: " + error.getMessage());
                    //Showing toast
                    pd.dismiss();
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

        }) ;

        VolleySingleton.getInstance(this).addToRequestQueue(jsonobj);

    }















    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_card_overlap);

        et_userName=findViewById(R.id.sign_in_username);
        et_password=findViewById(R.id.sign_in_password);
        parent_view = findViewById(android.R.id.content);

        ((View) findViewById(R.id.sign_up)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FormSignUp.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
            }
        });



            Button email_sign_in_button = findViewById(R.id.email_sign_in_button2);
            email_sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get theUsername and the Password
                 String Username=et_userName.getText().toString();
                 String Password = et_password.getText().toString();


                if(TextUtils.isEmpty(Username)){
                    et_userName.setError("Please Enter the Username");
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    et_password.setError("Please Enter the Password");
                    return;
                }


                if(!TextUtils.isEmpty(Username) && !TextUtils.isEmpty(Password)){


                    //login to the API to check if the Username and Password is Correct

                    try {
                        createUser(Username,Password);




                    } catch (JSONException e) {
                        e.printStackTrace();
                        return;
                    }


                }
                //call the HTTP and return the Success message asssociated with the application

            }
        });





        Tools.setSystemBarColor(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
