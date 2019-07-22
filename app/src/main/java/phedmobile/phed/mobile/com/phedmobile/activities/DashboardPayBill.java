package phedmobile.phed.mobile.com.phedmobile.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import phedmobile.phed.mobile.com.phedmobile.tools.Tools;

import phedmobile.phed.mobile.com.phedmobile.R;

public class DashboardPayBill extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayout lay_payelectricity;
    private LinearLayout lay_historyelectricity;
    private LinearLayout logout;

            String Usersname ;
        String AccountType;
         String AccountNo;
    String PhoneNo;
    String APIKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_pay_bill);
        initToolbar();
        lay_payelectricity = findViewById(R.id.payelectricity);

        lay_payelectricity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             Intent intent = new Intent(getApplicationContext(), CardCollections.class);
               // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);
            }
        });
        lay_historyelectricity = findViewById(R.id.paymenthistory);
          lay_historyelectricity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PaymentHistory.class);
                // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
            }
      });
        //add new accounts

        logout = findViewById(R.id.addAccountsdash);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), addAccounts.class);
                // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);
                //Dont allow the back button to go back to the application.

            }
        });

        //Meters
        logout = findViewById(R.id.dashmeters);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastIconInfo("Coming Soon");
            }
        });

        //PHEDCare
        logout = findViewById(R.id.dashcare);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastIconInfo("Coming Soon");
            }
        });

        //PHEDNews
        logout = findViewById(R.id.dashnews);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastIconInfo("Coming Soon");
            }
        });


        //PHEDHelp
        logout = findViewById(R.id.dashhelp);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastIconInfo("Coming Soon");
            }
        });

        //PHEDVerify
               logout = findViewById(R.id.dashverify);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastIconInfo("Coming Soon");

            }
        });

        //Logout of the Application
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginCardOverlap.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left,
                        R.anim.anim_slide_out_left);
                //Dont allow the back button to go back to the application.
                finish();
            }
        });
        AccountType = LoginCardOverlap.AccountType.toString();
        AccountNo  =  LoginCardOverlap.AccountNo.toString();
        PhoneNo =  LoginCardOverlap.PhoneNo.toString();
        Usersname = LoginCardOverlap.Usersname.toString();
        //08113894942

        TextView textView = (TextView) findViewById(R.id.customername);
        textView.setText(Usersname);

        TextView AccountTypeName = (TextView) findViewById(R.id.AccountTypeName);
        AccountTypeName.setText(AccountType +" | "+ AccountNo);

        TextView PhoneDetails = (TextView) findViewById(R.id.PhoneDetails);;
        PhoneDetails.setText(PhoneNo);
       // Toast.makeText(Second.this,"Data from first activity is"+data, 1).show();
    }


    private void toastIconInfo(String Text) {
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);

        //inflate view
        View custom_view = getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText(Text);
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_error_outline);
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(getResources().getColor(R.color.blue_600));

        toast.setView(custom_view);
        toast.show();
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.indigo_500), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationIcon(R.drawable.home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);
    }



}
