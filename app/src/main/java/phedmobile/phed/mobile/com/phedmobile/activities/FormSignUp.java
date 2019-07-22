package phedmobile.phed.mobile.com.phedmobile.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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

import phedmobile.phed.mobile.com.phedmobile.model.User;
import phedmobile.phed.mobile.com.phedmobile.tools.SharedPrefManager;
import phedmobile.phed.mobile.com.phedmobile.tools.Tools;

import phedmobile.phed.mobile.com.phedmobile.R;
import phedmobile.phed.mobile.com.phedmobile.tools.URLs;
import phedmobile.phed.mobile.com.phedmobile.tools.VolleySingleton;

public class FormSignUp extends AppCompatActivity {


    AutoCompleteTextView editTextUsername, editTextEmail,editTextFullName,editTextPhoneNo,edtAccountNo;
    EditText editTextPassword,editTextPassword2;
    RadioGroup radioGroupGender;
    ProgressBar progressBar;
    Button email_sign_in_button;
    LinearLayout lyt_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_sign_up);
        initToolbar();

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextPhoneNo = findViewById(R.id.editTextPhoneNo);
        editTextPassword =  findViewById(R.id.editTextPassword);
        editTextPassword2 =  findViewById(R.id.editTextPassword2);
        radioGroupGender = (RadioGroup) findViewById(R.id.radioGender);
        email_sign_in_button = findViewById(R.id.email_sign_in_button);
        edtAccountNo = findViewById(R.id.editTextAccountNo);
        lyt_progress = (LinearLayout) findViewById(R.id.lyt_progress);
        lyt_progress.setVisibility(View.GONE);
        lyt_progress.setAlpha(1.0f);
        //recyclerView.setVisibility(View.GONE);

        email_sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void createUser(){
        lyt_progress.setVisibility(View.VISIBLE);
        final String fullname = editTextFullName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String phoneno = editTextPhoneNo.getText().toString().trim();
        final String password2 = editTextPassword2.getText().toString().trim();
        final String accountno = edtAccountNo.getText().toString().trim();

        final String accounttype = ((RadioButton) findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();

        //first we will do the validations

       if (TextUtils.isEmpty(fullname)) {
            editTextFullName.setError("Please enter your full name");
            editTextFullName.requestFocus();
            lyt_progress.setVisibility(View.GONE);
            return;
        }



       /*if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter your email");
            editTextEmail.requestFocus();
            return;
        }*/
        if (TextUtils.isEmpty(phoneno)) {
            editTextPhoneNo.setError("Please enter your phone number");
            editTextPhoneNo.requestFocus();
            lyt_progress.setVisibility(View.GONE);
            return;
        }


       /* if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }*/

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Enter a password");
            editTextPassword.requestFocus();
            lyt_progress.setVisibility(View.GONE);
            return;
        }
        if (password.length()<6) {
            editTextPassword.setError("password must be at least 6 character long");
            editTextPassword.requestFocus();
            lyt_progress.setVisibility(View.GONE);
            return;
        }
        if (password.compareTo(password2)!=0) {
            editTextPassword2.setError("password does not match");
            editTextPassword2.requestFocus();
            lyt_progress.setVisibility(View.GONE);
            return;
        }

        HashMap datam =new HashMap();
        datam.put("AccountType",accounttype);
        datam.put("CustomerAccountNo",accountno);
        datam.put("CustomerEmail",email);
        datam.put("CustomerName",fullname);
        datam.put("CustomerPassword",password);
        datam.put("CustomerPhone",phoneno);
        datam.put("SecretQuestion","dd");
        datam.put("CustomerAPIKey","dd");

        final RequestQueue requstQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, URLs.URL_REGISTER,new JSONObject(datam),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        lyt_progress.setVisibility(View.GONE);
                        try {
                            if(!response.getString("CustomerAPIKey").isEmpty()){
                                Log.e("ServerResponse",response.toString());

                                User user = new User(
                                        response.getString("CustomerAPIKey"),
                                        response.getString("CustomerAccountNo"),
                                        response.getString("AccountType"));

                                toastIconSuccess("You've been registered on PHEDMobile,please login");
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                Intent intent = new Intent(getApplicationContext(), LoginCardOverlap.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK );
                                startActivity(intent);
                                overridePendingTransition(R.anim.anim_slide_in_left,R.anim.anim_slide_out_left);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
//                            Log.e("ServerResponse",e.getMessage());
                            e.printStackTrace();
                        }
                       // Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                lyt_progress.setVisibility(View.GONE);
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    toastIconError("There was a Communication Error!");
                } else if (error instanceof AuthFailureError) {
                    toastIconError("There was an Authentication Error!");
                } else if (error instanceof ServerError) {
                    toastIconError("There was a Server Side Error!");
                } else if (error instanceof NetworkError) {
                    toastIconError("There was a Network Error!");
                } else if (error instanceof ParseError) {

                    toastIconError("There was a Parser Error!");

                } else {
                    // VolleyLog.d(TAG, "Error: " + error.getMessage());
                    //Showing toast

                    toastIconError(error.getMessage());
                }
            }
        }) ;

        VolleySingleton.getInstance(this).addToRequestQueue(jsonobj);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void toastIconError(String text) {
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);

        //inflate view
        View custom_view = getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText(text);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_error_outline_black_24dp);
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(getResources().getColor(R.color.red_300));

        toast.setView(custom_view);
        toast.show();
    }

    private void toastIconSuccess(String text) {
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);

        //inflate view
        View custom_view = getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText(text);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_check_black_24dp);
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(getResources().getColor(R.color.green_500));

        toast.setView(custom_view);
        toast.show();
    }
}
