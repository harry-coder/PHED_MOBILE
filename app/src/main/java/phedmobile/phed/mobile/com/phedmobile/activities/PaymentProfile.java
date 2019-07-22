package phedmobile.phed.mobile.com.phedmobile.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.flutterwave.raveandroid.RaveConstants;
import com.flutterwave.raveandroid.RavePayActivity;
import com.flutterwave.raveandroid.RavePayManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import phedmobile.phed.mobile.com.phedmobile.R;
import phedmobile.phed.mobile.com.phedmobile.model.CustomerAdapter;
import phedmobile.phed.mobile.com.phedmobile.model.UserAccounts;
import phedmobile.phed.mobile.com.phedmobile.tools.Tools;

public class PaymentProfile extends AppCompatActivity {
    String Usersname;
    String AccountType;
    String AccountNo;
    String PhoneNo;
    String APIKey;
    String Name;


    public static String PaymentLogId;
    public static String CustReference;
    public static String AlternateCustReference;
    public  static String Amount;
    public   static String PaymentMethod;
    public   static String PaymentReference;
    public  static String TerminalID;
    public  static String ChannelName;
    public static String Location;
    public static String PaymentDate;
    public static String InstitutionId;
    public static String InstitutionName;
    public  static String BankName;
    public  static String BranchName;
    public static String CustomerName;
    public static String OtherCustomerInfo;
    public static String ReceiptNo;
    public static String CollectionsAccount;
    public  static String BankCode;
    public  static String CustomerAddress;
    public static String CustomerPhoneNumber;
    public static String DepositorName;
    public static String DepositSlipNumber;
    public  static String PaymentCurrency;
    public  static String ItemName;
    public  static String ItemCode;
    public static String ItemAmount;
    public static String PaymentStatus;
    public static String IsReversal;
    public static String SettlementDate;
    public static String Teller;
    public static String _AccountNo;
    public static String _MeterNo;
    public  static String _Arrears;
    public  static String _CurrentAmount;
    public  static String Token;
    public  static String Units;
    public static String Tariff;
    public static String Details1;
    public static String ReceiptNumber;








    String AmountPaid;
    public  static String ConsummerName = "";
    static String MeterNo = "";
    static String CustomerNo = "";
    static String ConsumetType = "";
    static String Arrears = "";
    static String CurrentAmount = "";
    static String TotalBill = "";
    static String Address = "";
    static String TarriffCode = "";
    static String IBCName = "";
    static String BSCName = "";
    static String SparkMeterBalance = "";
    private EditText inputamount1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment_form);

        EditText text = (EditText) findViewById(R.id.payamount);

        initToolbar();

        Usersname = LoginCardOverlap.Usersname.toString();

        AccountType = CustomerAdapter.aAccountType.toString();

        AccountNo = CustomerAdapter.aAccountNo.toString();
        PhoneNo = LoginCardOverlap.PhoneNo.toString();
        //call the DL Enhance API to get the Customer's details and then show the
        System.out.println("Account Details " + AccountNo);
        System.out.println("Phone Details " + PhoneNo);
        System.out.println("User Name " + Usersname);

        try {

            TextView PhoneDetails = (TextView) findViewById(R.id.debtprofile);

            Date Date_ = new Date();

            PhoneDetails.setText("Debts as at " + new SimpleDateFormat("MM-dd-yyyy").format(Date_));

            System.out.println("Debts as at " + Date_);

            createUser("phed", "2E639809-58B0-49E2-99D7-38CB4DF2B5B20", AccountNo, PhoneNo, Usersname, AccountType);

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        //Generate a Random Number
        Button paybill = findViewById(R.id.paybill);

        //Pass in the parameters from the Customer after the Customer has logged into the Application

// final long TransId = generateRandom(12);
// final String strLong = Long.toString(TransId);
        paybill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                //CompletePayment(message);

                final EditText inpuamt = (EditText) findViewById(R.id.inpuamt);
                AmountPaid = inpuamt.getText().toString();


                System.out.println(AmountPaid);

                new RavePayManager(PaymentProfile.this).setAmount(Double.parseDouble(AmountPaid))
                        .setCountry("NG")
                        .setCurrency("NGN")
                        .setEmail(Usersname)
                        .setfName(Name)
                        .setlName(Name)
                        .setNarration("PHEDBill Payment")
                        .setPublicKey("FLWPUBK-407f35d5f42506677a3de32c19660662-X")
                        .setEncryptionKey("a1319e6c3814efc03460cd3c")
                        .setTxRef("1334566436747574353")
                        .acceptAccountPayments(true)
                        .acceptCardPayments(true)
                        .acceptMpesaPayments(false)
                        .acceptAchPayments(false)
                        .acceptGHMobileMoneyPayments(false)
                        .acceptUgMobileMoneyPayments(false)
                        .onStagingEnv(false)
                        .allowSaveCardFeature(true)
                        .isPreAuth(true)
                        .initialize();
                        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void createUser(final String Username, final String Password, final String CustomerNumber, final String Mobile_Number, final String Mailid, final String CustomerType)
            throws JSONException {

        //having username and Password, Go to the Volley URL and validate

        System.out.println("Reaching this Point from CreateUser");


        // http://phedmobile.nepamsonline.com/api/PHEDMobile/Login




        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Getting details...");
        pd.show();


        //get the details of the SparkMeter Guy


        final String substrings =  TextUtils.substring(CustomerNumber, 0, 2);
        String substrings2 =  CustomerNumber.substring(0, 2);


        System.out.println("DOES THIS LOOK LIKE A CUSTOME NO" + CustomerNumber);

        System.out.println("heeeeeeeeeeeeeeeeeee "+substrings);

        System.out.println("HABAHA BABABABABAB A ABABAB "+substrings2);

      if(substrings.equalsIgnoreCase("SM"))
      {
          System.out.println("ITS REAACHING HERER E ER ER ER ER ER oooooooooo reaching here");
          //this is a SPARK Meter
         // final RequestQueue requstQueue = Volley.newRequestQueue(getApplicationContext());
          RequestQueue queue = Volley.newRequestQueue(this);
          String url = "http://phedmobile.nepamsonline.com/api/PHEDMobile/SparkMeterBalance";
          StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                  System.out.println("JSON OBJECT "+ response);
                  UserAccounts customer;
                  try {
                      JSONArray jsonResponse = new JSONArray(response);

                      System.out.println("Length of the Array " + jsonResponse.length());
                      // = jsonResponse.getJSONObject(0).toString();
                      JSONObject jsonObject1 = jsonResponse.getJSONObject(0);

                      SparkMeterBalance = jsonObject1.optString("Balance");

                      System.out.println("SPARK METER BALANCE "+SparkMeterBalance);
                      pd.dismiss();
                  }
                  catch (JSONException e) {
                      Toast.makeText(getApplicationContext(), "Could not retrieve balance", Toast.LENGTH_LONG).show();

                      pd.dismiss();
                      System.out.println("FATAL ERROR "+e.getMessage());
                      e.printStackTrace();
                  }
              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {

                  System.out.println("Response is: " + error.getMessage());

                  pd.dismiss();
              }
          }) {

              @Override
              protected Map<String, String> getParams() {
                  Map<String, String> datam = new HashMap<String, String>();

                  datam.put("APIKey", CustomerNumber);

                  return datam;
              }
          };


          queue.add(postRequest);

      }












        final RequestQueue requstQueue = Volley.newRequestQueue(getApplicationContext());

        final String URL = "https://dlenhance.phed.com.ng/dlenhanceapi/Collection/GetCustomerInfo";

        StringRequest postRequests = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        try {
                            //  JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonResponse = new JSONArray(response);




                            String ConsumetType = "";

                            String Arrears = "";
                            String CurrentAmount = "";
                            String TotalBill = "";
                            String Address = "";
                            String TarriffCode = "";
                            String IBCName = "";
                            String BSCName = "";
                            String  _ConsummerName = "";
                            String  _MeterNo = "";
                            String _CustomerNo ="";
                            for (int i = 0; i < jsonResponse.length(); i++) {
                                JSONObject jsonObject1 = jsonResponse.getJSONObject(i);
                                ConsummerName = jsonObject1.optString("CONS_NAME");
                                MeterNo = jsonObject1.optString("METER_NO");
                                CustomerNo = jsonObject1.optString("CUSTOMER_NO");
                                ConsumetType = jsonObject1.optString("CONS_TYPE");
                                Arrears = jsonObject1.optString("ARREAR");
                                CurrentAmount = jsonObject1.optString("CURRENT_AMOUNT");
                                TotalBill = jsonObject1.optString("TOTAL_BILL");
                                Address = jsonObject1.optString("ADDRESS");
                                TarriffCode = jsonObject1.optString("TARIFFCODE");
                                IBCName = jsonObject1.optString("IBC_NAME");
                                BSCName = jsonObject1.optString("BSC_NAME");
                                _CustomerNo = jsonObject1.optString("CUSTOMER_NO");
                                _MeterNo = jsonObject1.optString("METER_NO");
                                _ConsummerName = jsonObject1.optString("CONS_NAME");
                            }

                            //Assign the values to a Textview Here

//                            TextView PhoneDetails = (TextView) findViewById(R.id.AccountDetails);
//                            PhoneDetails.setText( ConsummerName  +  " | "+ ConsumetType );


                            TextView b = (TextView) findViewById(R.id.debtprofile);
                            b.setText(_ConsummerName);


                            TextView d = (TextView) findViewById(R.id.AccountDetails2);
                            d.setText(_CustomerNo + " | " + ConsumetType);

                            TextView e = (TextView) findViewById(R.id.arrears);
                            e.setText("₦ " + Arrears);



                            if(substrings.equalsIgnoreCase("SM")) {
                                TextView nn = (TextView) findViewById(R.id.sparkMeter2);
                                nn.setText(SparkMeterBalance);
                                nn.setVisibility(View.VISIBLE);
                            }
                            else {
                                TextView nn = (TextView) findViewById(R.id.sparkMeter2);
                                // nn.setText(SparkMeterBalance);
                                nn.setVisibility(View.INVISIBLE);
                                TextView bb = (TextView) findViewById(R.id.sparkMeter1);
                                // nn.setText(SparkMeterBalance);
                                bb.setVisibility(View.INVISIBLE);   }

                            TextView f = (TextView) findViewById(R.id.CurrentDebts);
                            f.setText("₦ " + CurrentAmount);

                            TextView g = (TextView) findViewById(R.id.debtsduetoday);
                            g.setText("₦ " + TotalBill);

                            System.out.println("ACCOOUNT MEDIA "+CustomerNo);

                            System.out.println("METER NUMBER "+ MeterNo);


                        } catch (JSONException e) {

                            toastIconError("An error Occured, Please check Network or Account Details");
                            //redirect to the Dashboard SCreen
                            Intent i = new Intent(PaymentProfile.this, CardCollections.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);

                            System.out.println(e.getMessage());
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Intent i = new Intent(PaymentProfile.this, DashboardPayBill.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        System.out.println(error.getMessage());
                    }
                }
        ) {
            // here is params will add to your url using post method
            @Override
            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                params.put("app", getString(R.string.app_name));
//                //params.put("2ndParamName","valueoF2ndParam");

                Map<String, String> datam = new HashMap<String, String>();

                datam.put("username", Username);
                datam.put("apikey", Password);
                datam.put("CustomerNumber", CustomerNumber);
                datam.put("Mobile_Number", Mobile_Number);
                datam.put("Mailid", Mailid);
                datam.put("CustomerType", CustomerType);
                return datam;
            }
        };
        Volley.newRequestQueue(this).add(postRequests);


    }


//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//
//        // Initialize a new JsonArrayRequest instance
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
//                Request.Method.POST,
//                URL,
//                new JSONArray(datam),
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        // Do something with response
//                        //mTextView.setText(response.toString());
//
//                        // Process the JSON
//
//
//                            System.out.println("Amamanaman anddndndmdsd " + response.toString());
//                            // Loop through the array elements
////                            for(int i=0;i<response.length();i++){
////                                // Get current json object
////                                JSONObject student = response.getJSONObject(i);
////
////                                // Get the current student (json object) data
//////                                String firstName = student.getString("firstname");
//////                                String lastName = student.getString("lastname");
//////                                String age = student.getString("age");
//////
//////                                // Display the formatted json data in text view
//////                                mTextView.append(firstName +" " + lastName +"\nAge : " + age);
//////                                mTextView.append("\n\n");
////                            }
//
//
//                    }
//                },
//                new Response.ErrorListener(){
//                    @Override
//                    public void onErrorResponse(VolleyError error){
//                        // Do something when error occurred
//                        System.out.println("Amamanaman anddndndmdsd " + error.getMessage());
//                    }
//                }
//        );
//
//        // Add JsonArrayRequest to the RequestQueue
//        requestQueue.add(jsonArrayRequest);


// Define the POST request
//        JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(datam),
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            VolleyLog.v("Response:%n %s", response.toString(4));
//
//                            System.out.println("alloooooooooooowee111111111111111111 ");
//                            System.out.println("Response:%n %s"+ response.toString());
//                        } catch (JSONException e) {
//
//                            System.out.println("alloooooooooooo2222222222222222222222222 ");
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                System.out.println("alloooooooooooowee333333333333333333333 " + error.getMessage());
//                VolleyLog.e("Error: ", error.getMessage());
//            }
//        });


// Add the request object to the queue to be executed
    //ApplicationController.getInstance().addToRequestQueue(req);
    // VolleySingleton.getInstance(this).addToRequestQueue(req);
//        String url =  "http://dlenhanceuat.phed.com.ng/dlenhanceapi/Collection/GetCustomerInfo";
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                Request.Method.POST, url, new JSONObject(datam),
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject jsonObject) {
//                        //Log.d(App.TAG, jsonObject.toString());
//                        System.out.println("alloooooooooooowee "+jsonObject.toString());
//                    }
//                }, new Response.ErrorListener (){
//
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//              //  Log.d(App.TAG,volleyError.toString());
//            }
//        }
//        );
//        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
//


//    private String current = "";
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        if(!s.toString().equals(current)){
//           // inputamount1 d =  findViewById(R.id.inputamount1);
//            inputamount1.removeTextChangedListener((TextWatcher) this);
//
//            String cleanString = s.toString().replaceAll("[$,.]", "");
//
//            double parsed = Double.parseDouble(cleanString);
//            String formatted = NumberFormat.getCurrencyInstance().format((parsed/100));
//
//            current = formatted;
//            inputamount1.setText(formatted);
//            inputamount1.setSelection(formatted.length());
//
//            inputamount1.addTextChangedListener((TextWatcher) this);
//        }
//    }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*
         *  We advise you to do a further verification of transaction's details on your server to be
         *  sure everything checks out before providing service or goods.
         */

        System.out.println("Reaching RAVE");

        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null)
        {
            String message = data.getStringExtra("response");

            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                //100 is the Minimum
                //  CompletePaymentTest(CustomerNo,MeterNo);
              CompletePaymentTest(message,CustomerNo,MeterNo, AmountPaid, ConsummerName, LoginCardOverlap.PhoneNo.toString(), Address.toString());

            }
            else if (resultCode == RavePayActivity.RESULT_ERROR) {
                toastIconError("An Error Occured");
            }
            else if (resultCode == RavePayActivity.RESULT_CANCELLED) {

                toastIconError("Transaction Cancelled");
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    public static long generateRandom(int length) {

        Random random = new Random();

        char[] digits = new char[length];

        digits[0] = (char) (random.nextInt(9) + '1');

        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }

        return Long.parseLong(new String(digits));

        //2029096198 [1900]
    }

    public static String setDateParsing(String date) throws ParseException {
        // This is the format date we want
        DateFormat mSDF = new SimpleDateFormat("hh:mm a");
        // This format date is actually present
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm");
        return mSDF.format(formatter.parse(date));
    }

    public void CompletePaymentTest(String message,String _CustomerNo,String __MeterNo, String _Amount,String _CustomerName, String _CustomerPhone, String _CustomerAddress)
    {
            //Get the Constituent Elements
            System.out.println("Success reaching here 2");
            Date Date_ = new Date();

        JSONObject  jsonobject = null;
        try {
            jsonobject = new JSONObject(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//            //read the items of the JOSN Object
//            JSONObject dataObject = jsonobject.getJSONObject("data");


//            //read the items of the JOSN Object
        JSONObject dataObject = null;
        try {
            dataObject = jsonobject.getJSONObject("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (dataObject != null) {
            try {
                PaymentLogId = dataObject.getString("txRef");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        CustReference = _CustomerNo;
            AlternateCustReference = __MeterNo;
            Amount = _Amount;
            PaymentMethod = "Own Bank Cheque";
        if (dataObject != null) {
            try {
                PaymentReference = dataObject.getString("orderRef");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ;
            TerminalID = "empty";
            ChannelName = "BANKBRANCH";
            Location = "empty";
            PaymentDate = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss").format(Date_);
            InstitutionId = "empty";
            InstitutionName = "empty";
            BankName = "CITI BANK";
            BranchName = "CITI BANK";
            CustomerName = _CustomerName;
            OtherCustomerInfo = "empty";
            ReceiptNo = "empty";
            CollectionsAccount = "empty";
            BankCode = "023";
            CustomerAddress = _CustomerAddress;
            CustomerPhoneNumber = _CustomerPhone;
            DepositorName =  "Ebuka";
        if (dataObject != null) {
            try {
                DepositSlipNumber =  dataObject.getString("raveRef");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        PaymentCurrency = "NGN";
            ItemName = "empty";
            ItemCode = "01";
            ItemAmount = "1500";
            PaymentStatus = "SUCCESS";
            IsReversal = "empty";
            SettlementDate = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss").format(Date_);
            Teller = "empty";


        //deserialise the JSON respose here and Show the Activity for the Receipt

        //Call Notify Payment here DLEnhance aher and use it to Get the Customers Token.

        //call the Variables Here and Make use of them

        //having username and Password, Go to the Volley URL and validate

        System.out.println("Reaching this Point from CreateUser");

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("We're finalizing your Payment...");
        pd.show();


        final String URL = "https://dlenhance.phed.com.ng/dlenhanceapi/Collection/NotifyPayment";

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("NOTIFY PAYMENT RESONSE "+response);
                        pd.dismiss();
                        try {
                            //JSONObject jsonResponse = new JSONObject(response);
                              JSONArray jsonResponse = new JSONArray(response);

                            System.out.println("Success reaching here 1" + jsonResponse);


//                            [{"CUSTOMER_NO":"860002100901","METER_NO":"27100428914","RECEIPTNUMBER":"2802201961413",
// "PAYMENTDATETIME":"28-02-2019 14:48:06","AMOUNT":"1500","TOKENDESC":"44204207207730979078","UNITSACTUAL":"37.9",
// "TARIFF":"30.23000","STATUS":"SUCCESS",
// "DETAILS":[{"HEAD":"Arrears (NGN)","AMOUNT":"300.00"},{"HEAD":"Energy Value (NGN)","AMOUNT":"1142.86"},{"HEAD":"VAT (NGN)","AMOUNT":"57.14"}]}]
//
                           // Deserialise the JSON Array returned and use it to perform the necessary Functions needed

//make the array


                          for (int i = 0; i < jsonResponse.length(); i++) {
                              JSONObject jsonObject1 = jsonResponse.getJSONObject(i);
                              _AccountNo   = jsonObject1.optString("CUSTOMER_NO");
                              _MeterNo = jsonObject1.optString("METER_NO");
                                ReceiptNo = jsonObject1.optString("RECEIPTNUMBER");
                                PaymentDate = jsonObject1.optString("PAYMENTDATETIME");

                              _Arrears = jsonObject1.optString("ARREAR");
                              _CurrentAmount = jsonObject1.optString("AMOUNT");
                                Token = jsonObject1.optString("TOKENDESC");
                                Units = jsonObject1.optString("UNITSACTUAL");
                                Tariff = jsonObject1.optString("TARIFF");
                                Details1 = jsonObject1.optString("DETAILS");
                              ReceiptNumber = jsonObject1.optString("RECEIPTNUMBER");
                              System.out.println("Accccccount " + Token);
                              System.out.println("Tokeeeeeeeeeeeeeeeeen " + _AccountNo);

                              System.out.println("Tarifffffffffffffff  " + Tariff);

                              System.out.println("ReceiptNo " + ReceiptNo);
                              System.out.println("Ammmmmmmmmmmmmmmmmmmmount " + _CurrentAmount);
                              System.out.println("Ammmmmmmmmmmmmmmmmmmmount " + Details1);


                          }



//                            TextView ramount = (TextView) findViewById(R.id.receiptamount);
//                            ramount.setText(_CurrentAmount);


//                                    for (int i = 0; i < jsonResponse.length(); i++) {
//                                        JSONObject jsonObject1 = jsonResponse.getJSONObject(i);
//                                        ConsummerName = jsonObject1.optString("CONS_NAME");
//                                        MeterNo = jsonObject1.optString("METER_NO");
//                                        CustomerNo = jsonObject1.optString("CUSTOMER_NO");
//                                        ConsumetType = jsonObject1.optString("CONS_TYPE");
//                                        Arrears = jsonObject1.optString("ARREAR");
//                                        CurrentAmount = jsonObject1.optString("CURRENT_AMOUNT");
//                                        TotalBill = jsonObject1.optString("TOTAL_BILL");
//                                        Address = jsonObject1.optString("ADDRESS");
//                                        TarriffCode = jsonObject1.optString("TARIFFCODE");
//                                        IBCName = jsonObject1.optString("IBC_NAME");
//                                        BSCName = jsonObject1.optString("BSC_NAME");
//                                    }

//                                    TextView d = (TextView) findViewById(R.id.AccountDetails2);
//                                    d.setText(CustomerNo + " | " + ConsumetType);




                            Intent intent = new Intent(getApplicationContext(), PaymentSuccessDialog.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);

                        } catch (JSONException e) {

                            toastIconError("An error occured, please try again");
                            //redirect to the Dashboard SCreen
                            Intent i = new Intent(PaymentProfile.this, DashboardPayBill.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            System.out.println(e.getMessage());
                            e.printStackTrace();
                        }
                }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();

                        System.out.println("ERROR MESSAGE" + error.getMessage());
//                        Intent i = new Intent(PaymentProfile.this, DashboardPayBill.class);
//                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(i);
                        System.out.println(error.getMessage());
                    }
                }
        )

        {
            // here is params will add to your url using post method
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> datam = new HashMap<String, String>();
                datam.put("username", "phed");
                datam.put("apikey", "2E639809-58B0-49E2-99D7-38CB4DF2B5B20");
                datam.put("PaymentLogId", PaymentLogId);
             //   datam.put("CustReference", "860002100901");
                datam.put("AlternateCustReference", AlternateCustReference);
                datam.put("CustReference", CustReference);
              //  datam.put("AlternateCustReference", "27100315111");
                datam.put("Amount", Amount);
                datam.put("PaymentMethod", PaymentMethod);
                datam.put("PaymentReference", PaymentReference);
                datam.put("TerminalID", TerminalID);
                datam.put("ChannelName", ChannelName);
                datam.put("Location", Location);
                datam.put("PaymentDate", PaymentDate);
                datam.put("InstitutionId", InstitutionId);
                datam.put("InstitutionName", InstitutionName);
                datam.put("BankName", BankName);
                datam.put("BranchName", BranchName);
                datam.put("CustomerName", CustomerName);
                datam.put("OtherCustomerInfo", OtherCustomerInfo);
                datam.put("ReceiptNo", ReceiptNo);
                datam.put("CollectionsAccount", CollectionsAccount);
                datam.put("BankCode", BankCode);
                datam.put("CustomerAddress", CustomerAddress);
                datam.put("CustomerPhoneNumber", CustomerPhoneNumber);
                datam.put("DepositorName", DepositorName);
                datam.put("DepositSlipNumber", DepositSlipNumber);
                datam.put("PaymentCurrency", PaymentCurrency);
                datam.put("ItemName", ItemName);
                datam.put("ItemCode", ItemCode);
                datam.put("ItemAmount", ItemAmount);
                datam.put("PaymentStatus", PaymentStatus);
                datam.put("IsReversal", IsReversal);
                datam.put("SettlementDate", SettlementDate);
                datam.put("Teller", Teller);

                System.out.println("HIIIMOLOYAR"+ datam);
                return datam;
            }
        };

        Volley.newRequestQueue(this).add(postRequest);


        //Toast.makeText(this, "SUCCESS " + message, Toast.LENGTH_SHORT).show();

        //show the receipt and disply the Token on the receipt



    }





//    public void CompletePayment(String message)
//    {
//
//        try {
//            JSONObject jsonobject = new JSONObject(message);
//            //read the items of the JOSN Object
//            JSONObject dataObject = jsonobject.getJSONObject("data");
//
//
//            //Get the Constituent Elements
//
//            System.out.println("Success reaching here 2");
//            Date Date_ = new Date();
//
//            PaymentLogId = dataObject.getString("txRef");
//            CustReference = AccountNo;
//            AlternateCustReference = AccountNo;
//            Amount = AmountPaid;
//            PaymentMethod = "WEB";
//            PaymentReference = dataObject.getString("orderRef");LoginCardOverlap.PhoneNo.toString()dataObject.getString("txRef");
//            TerminalID = "empty";
//            ChannelName = "BANKBRANCH";
//            Location = "empty";
//            PaymentDate = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss").format(Date_);
//            InstitutionId = "empty";
//            InstitutionName = "empty";
//            BankName = "PHED BANK";
//            BranchName = "PHED MOBILE";
//            CustomerName = "PHED Customer";
//            OtherCustomerInfo = "empty";
//            ReceiptNo = "empty";
//            CollectionsAccount = "empty";
//            BankCode = "000";
//            CustomerAddress = "PHED Customer Address";
//            CustomerPhoneNumber = LoginCardOverlap.PhoneNo.toString();
//            DepositorName =  ConsummerName;
//            DepositSlipNumber = dataObject.getString("raveRef");
//            PaymentCurrency = "NGN";
//            ItemName = "empty";
//            ItemCode = "01";
//            ItemAmount = AmountPaid;
//            PaymentStatus = "SUCCESS";
//            IsReversal = "empty";
//            SettlementDate = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss").format(Date_);
//            Teller = "empty";
//
//            System.out.println("Success reaching here 3");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        //deserialise the JSON respose here and Show the Activity for the Receipt
//
//
//        //Call Notify Payment here DLEnhance aher and use it to Get the Customers Token.
//
//
//        //call the Variables Here and Make use of them
//
//        //having username and Password, Go to the Volley URL and validate
//
//        System.out.println("Reaching this Point from CreateUser");
//
//
//
//        final ProgressDialog pd = new ProgressDialog(this);
//        pd.setMessage("Finalizing Payment details...");
//        pd.show();
//
//
//        final String URL = "http://dlenhance.phed.com.ng/dlenhanceapi/Collection/NotifyPayment";
//
//        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        System.out.println("NOTIFY PAYMENT RESONSE "+response);
//                        pd.dismiss();
//                        try {
//                            JSONObject jsonResponse = new JSONObject(response);
//                            //  JSONArray jsonResponse = new JSONArray(response);
//
//                            System.out.println("Success reaching here 1");
//
////                                    for (int i = 0; i < jsonResponse.length(); i++) {
////                                        JSONObject jsonObject1 = jsonResponse.getJSONObject(i);
////                                        ConsummerName = jsonObject1.optString("CONS_NAME");
////                                        MeterNo = jsonObject1.optString("METER_NO");
////                                        CustomerNo = jsonObject1.optString("CUSTOMER_NO");
////                                        ConsumetType = jsonObject1.optString("CONS_TYPE");
////                                        Arrears = jsonObject1.optString("ARREAR");
////                                        CurrentAmount = jsonObject1.optString("CURRENT_AMOUNT");
////                                        TotalBill = jsonObject1.optString("TOTAL_BILL");
////                                        Address = jsonObject1.optString("ADDRESS");
////                                        TarriffCode = jsonObject1.optString("TARIFFCODE");
////                                        IBCName = jsonObject1.optString("IBC_NAME");
////                                        BSCName = jsonObject1.optString("BSC_NAME");
////                                    }
////
////                                    TextView d = (TextView) findViewById(R.id.AccountDetails2);
////                                    d.setText(CustomerNo + " | " + ConsumetType);
//                            Intent intent = new Intent(getApplicationContext(), PaymentSuccessDialog.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(intent);
//                            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
//
//                        } catch (JSONException e) {
//
//                            toastIconError("An error occured, please try again");
//                            //redirect to the Dashboard SCreen
//                            Intent i = new Intent(PaymentProfile.this, DashboardPayBill.class);
//                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(i);
//
//                            System.out.println(e.getMessage());
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        pd.dismiss();
//                        Intent i = new Intent(PaymentProfile.this, DashboardPayBill.class);
//                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(i);
//                        System.out.println(error.getMessage());
//                    }
//                }
//        )
//
//        {
//            // here is params will add to your url using post method
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> datam = new HashMap<String, String>();
//                datam.put("username", "phed");
//                datam.put("apikey", "2E639809-58B0-49E2-99D7-38CB4DF2B5B2");
//                datam.put("PaymentLogId", PaymentLogId);
//                datam.put("CustReference", "860002100901");
//                // datam.put("AlternateCustReference", AlternateCustReference);   datam.put("CustReference", CustReference);
//                datam.put("AlternateCustReference", "27100315111");
//                datam.put("Amount", Amount);
//                datam.put("PaymentMethod", PaymentMethod);
//                datam.put("PaymentReference", PaymentReference);
//                datam.put("TerminalID", TerminalID);
//                datam.put("ChannelName", ChannelName);
//                datam.put("Location", Location);
//                datam.put("PaymentDate", PaymentDate);
//                datam.put("InstitutionId", InstitutionId);
//                datam.put("InstitutionName", InstitutionName);
//                datam.put("BankName", BankName);
//                datam.put("BranchName", BranchName);
//                datam.put("CustomerName", CustomerName);
//                datam.put("OtherCustomerInfo", OtherCustomerInfo);
//                datam.put("ReceiptNo", ReceiptNo);
//                datam.put("CollectionsAccount", CollectionsAccount);
//                datam.put("BankCode", BankCode);
//                datam.put("CustomerAddress", CustomerAddress);
//                datam.put("CustomerPhoneNumber", CustomerPhoneNumber);
//                datam.put("DepositorName", DepositorName);
//                datam.put("DepositSlipNumber", DepositSlipNumber);
//                datam.put("PaymentCurrency", PaymentCurrency);
//                datam.put("ItemName", ItemName);
//                datam.put("ItemCode", ItemCode);
//                datam.put("ItemAmount", ItemAmount);
//                datam.put("PaymentStatus", PaymentStatus);
//                datam.put("IsReversal", IsReversal);
//                datam.put("SettlementDate", SettlementDate);
//                datam.put("Teller", Teller);
//
//                System.out.println("HIIIMOLOYAR"+ datam);
//                return datam;
//            }
//        };
//        Volley.newRequestQueue(this).add(postRequest);
//
//
//        //Toast.makeText(this, "SUCCESS " + message, Toast.LENGTH_SHORT).show();
//
//        //show the receipt and disply the Token on the receipt
//
//
//
//    }



}
