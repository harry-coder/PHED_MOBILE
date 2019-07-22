package phedmobile.phed.mobile.com.phedmobile.activities;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import phedmobile.phed.mobile.com.phedmobile.R;
import phedmobile.phed.mobile.com.phedmobile.tools.VolleySingleton;

public class addAccounts extends AppCompatActivity  {

    EditText AccountName,Email,PhoneNo,AccountNo, AccountType;

    Button email_sign_in_button;
    LinearLayout lyt_progress;
    static  String AccountTypes;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_accounts);
        initComponent();

        AccountName = findViewById(R.id.addName);
        Email = findViewById(R.id.addemail);
        PhoneNo = findViewById(R.id.addphone);
        AccountNo =  findViewById(R.id.addaccountnumber);


        email_sign_in_button = findViewById(R.id.email_sign_in_button2);



        email_sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAcccounts();
            }
        });



        ((View) findViewById(R.id.viewAccounts)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), FormSignUp.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK );
//                startActivity(intent);
//                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);


                Intent intent = new Intent(getApplicationContext(), CardCollections.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left,R.anim.anim_slide_out_left);

            }
        });

    }

    private void AddAcccounts() {
        //Call the Add Account here
String _accountname = AccountName.getText().toString();
String _email =  Email.getText().toString();
String _Phone = PhoneNo.getText().toString();
String _AccountNumber = AccountNo.getText().toString();
String _accounttype = AccountTypes;
String APIKey = LoginCardOverlap.APIKey.toString();

        System.out.println("Account Type" + _accounttype);

        HashMap datam =new HashMap();
        datam.put("AccountName",_accountname);
        datam.put("AccountNumber",_AccountNumber);
        datam.put("AccountType",_accounttype);
        datam.put("CustomerEmail",_email);
        datam.put("CustomerAPIKey",APIKey);


        final RequestQueue requstQueue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, "http://phedmobile.nepamsonline.com/api/PHEDMobile/AddAccounts",new JSONObject(datam),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if(!response.getString("CustomerAPIKey").isEmpty()){
                                Log.e("ServerResponse",response.toString());
                                Intent intent = new Intent(getApplicationContext(), DashboardPayBill.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK );
                                startActivity(intent);
                                overridePendingTransition(R.anim.anim_slide_in_left,
                                        R.anim.anim_slide_out_left);
                                toastIconSuccess("The account has been added");
                              //  Toast.makeText(getApplicationContext(),"Account Added",Toast.LENGTH_LONG).show();

                            }

                        } catch (JSONException e) {

                            toastIconError(e.getMessage());
                            e.printStackTrace();
                        }
                        // Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                lyt_progress.setVisibility(View.GONE);
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    toastIconError("There was a communication Error");
                } else if (error instanceof AuthFailureError) {
                    toastIconError("There was an Authentication Error");
                } else if (error instanceof ServerError) {
                    toastIconError("There was a Server Error");
                } else if (error instanceof NetworkError) {
                    toastIconError("There was a Network Error");
                } else if (error instanceof ParseError) {
                    toastIconError("There was a Parse Error");
                } else {
                    // VolleyLog.d(TAG, "Error: " + error.getMessage());
                    //Showing toast
                    toastIconError(error.getMessage());
                }
            }
        }) ;

        VolleySingleton.getInstance(this).addToRequestQueue(jsonobj);

    }
    private void initComponent() {
//        (findViewById(R.id.fab_done)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
//            }
//        });


        (findViewById(R.id.addet_gender)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGenderDialog(v);
            }
        });
    }

    private void showGenderDialog(final View v) {
        final String[] array = new String[]{
                "PREPAID", "POSTPAID"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Account Type");
        builder.setSingleChoiceItems(array, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((EditText) v).setText(array[i]);

                AccountTypes = array[i];

                dialogInterface.dismiss();
            }
        });
        builder.show();
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
