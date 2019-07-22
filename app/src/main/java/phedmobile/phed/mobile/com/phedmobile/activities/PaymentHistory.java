package phedmobile.phed.mobile.com.phedmobile.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import phedmobile.phed.mobile.com.phedmobile.R;
import phedmobile.phed.mobile.com.phedmobile.adapters.Adapter;
import phedmobile.phed.mobile.com.phedmobile.model.DataSet;
import phedmobile.phed.mobile.com.phedmobile.tools.Controller;
import phedmobile.phed.mobile.com.phedmobile.tools.URLs;

public class PaymentHistory extends AppCompatActivity {
    //    private RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
    private List<DataSet> list = new ArrayList<DataSet>();
    private ListView listView;
    private Adapter adapter;
    private JSONArray array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        listView = (ListView) findViewById(R.id.list);
//        final Adapter adapter = new Adapter(this, list);
//        listView.setAdapter(adapter);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
//
//            }
//        });
//        HashMap<String,String> param = new HashMap<String, String>();
//        param.put("APIKey","123800");
        JSONObject parameter = new JSONObject();
        try {
            parameter.put("APIKey", "9f72ca5c-b712-4072-b46a-dd662e1c4267");
            //parameter.put("Param2", "Value2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            array = new JSONArray(parameter.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonArrayRequest jsonreq = new JsonArrayRequest(Request.Method.POST, URLs.URL_PAYMENTHISTORY, array, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject obj = response.getJSONObject(i);
                        DataSet dataSet = new DataSet();
                        dataSet.setName(obj.getString("Amount"));
                        //dataSet.setImage(obj.getString("image"));
                        dataSet.setWorth(obj.getString("NGN"));
                        dataSet.setYear(obj.getInt("DatePaid"));
                        dataSet.setSource(obj.getString("Token"));
                        list.add(dataSet);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                AlertDialog.Builder add = new AlertDialog.Builder(PaymentHistory.this);
//                add.setMessage(error.getMessage()).setCancelable(true);
//                AlertDialog alert = add.create();
//                alert.setTitle("Error!!!");
//                alert.show();

                toastIconError("An error Occured, Please try again");

                Intent i = new Intent(PaymentHistory.this, DashboardPayBill.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        Controller.getPermission().addToRequestQueue(jsonreq);
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

