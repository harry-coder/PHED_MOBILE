package phedmobile.phed.mobile.com.phedmobile.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import phedmobile.phed.mobile.com.phedmobile.R;
import phedmobile.phed.mobile.com.phedmobile.model.CustomerAdapter;
import phedmobile.phed.mobile.com.phedmobile.model.UserAccounts;

public class CardCollections extends AppCompatActivity {


    String Usersname;
    String AccountType;
    String AccountNo;
    String PhoneNo;
    String APIKey;
    RecyclerView mSchedualRecyclerView;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<UserAccounts> userList;

    CustomerAdapter customerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_collections);

//            CardView mEmailSignInButton = (CardView) findViewById(R.id.plusone);
//            mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    System.out.println("Oh Yeahhhhhh");
//                }
//            });
//
        initRecyclerView();

        customerAdapter=new CustomerAdapter(this);
        mSchedualRecyclerView.setAdapter(customerAdapter);

        setmCustomerList();

    }


    private void initRecyclerView() {
        mSchedualRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_doctor_schedual);
        mSchedualRecyclerView.setHasFixedSize(true);
        //setting animation
        mSchedualRecyclerView.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(this);
        //binding layout with recycler view
        mSchedualRecyclerView.setLayoutManager(layoutManager);
        //assigning adapter to RecyclerView

    }


    private void setmCustomerList() {

        final ArrayList<UserAccounts> mCustomerList = new ArrayList<UserAccounts>();

        final String mAPIKey = LoginCardOverlap.APIKey.toString();

        System.out.println("Mobile API KEy First " + mAPIKey);

//        HashMap datam = new HashMap();
//
//        datam.put("APIKey", mAPIKey);

        // http://phedmobile.nepamsonline.com/api/PHEDMobile/Login


        final RequestQueue requstQueue = Volley.newRequestQueue(getApplicationContext());


        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();


        //   final TextView textView = (TextView) findViewById(R.id.text);

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://phedmobile.nepamsonline.com/api/PHEDMobile/CustomerAccounts";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                //textView.setText("Response is: "+ response.substring(0,500));
                //System.out.println("Response is: "+ response.substring(0,500));

                UserAccounts customer;
                try {
                    JSONArray jsonResponse = new JSONArray(response);


                    System.out.println("Length of the Array " + jsonResponse.length());

                   // userList = parseResponse(jsonResponse);

                    customerAdapter.setSource( parseResponse(jsonResponse));

                    pd.dismiss();

                }
                catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "An error occured, please try again", Toast.LENGTH_LONG).show();
                    //redirect to the Dashboard SCreen
                    Intent i = new Intent(CardCollections.this, DashboardPayBill.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    pd.dismiss();
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("That didn't work!");

                System.out.println("Response is: " + error.getMessage());
                Intent i = new Intent(CardCollections.this, DashboardPayBill.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                pd.dismiss();
            }
        }) {
            // here is params will add to your url using post method
            @Override
            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                params.put("app", getString(R.string.app_name));
//                //params.put("2ndParamName","valueoF2ndParam");

                Map<String, String> datam = new HashMap<String, String>();

                datam.put("APIKey", mAPIKey);

                return datam;
            }
        };

        ;

// Add the request to the RequestQueue.
        queue.add(stringRequest);


        //  VolleySingleton.getInstance(this).addToRequestQueue(jsonobj);


        //System.out.println("" +);


    }


    public ArrayList<UserAccounts> parseResponse(JSONArray jsonResponse) throws JSONException {
        UserAccounts customer;
        ArrayList<UserAccounts> customerList = new ArrayList();

        TextView e = (TextView) findViewById(R.id.NoofAccounts);
        e.setText(  jsonResponse.length() + " Account(s)");


        for (int i = 0; i < jsonResponse.length(); i++) {

            JSONObject jsonObject1 = jsonResponse.getJSONObject(i);
            customer = new UserAccounts();
            customer.mCustomerAccountName = jsonObject1.optString("AccountName");
            customer.mCustomerAccountNo = jsonObject1.optString("AccountNumber");
            customer.mCustomerAccountType = jsonObject1.optString("AccountType");
            customerList.add(customer);
        }

        return customerList;
    }
}





