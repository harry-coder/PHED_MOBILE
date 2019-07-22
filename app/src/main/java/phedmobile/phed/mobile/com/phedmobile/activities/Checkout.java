package phedmobile.phed.mobile.com.phedmobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import co.paystack.android.model.Card;
import phedmobile.phed.mobile.com.phedmobile.R;

public class Checkout extends AppCompatActivity {

    EditText et_cardNumber1, et_cardNumber2, et_cardNumber3, et_cardNumber4, et_cardHolderName, et_expiryDate, et_cvv;
    StringBuilder cardNumber;
    int month, year;
    String cvv;
    Button bt_chargeMoney;

    String date;

    Card card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.checkout );

        et_cardNumber1 = findViewById ( R.id.et_cardNumber1 );
        et_cardNumber2 = findViewById ( R.id.et_cardNumber2 );
        et_cardNumber3 = findViewById ( R.id.et_cardNumber3 );
        et_cardNumber4 = findViewById ( R.id.et_cardNumber4 );

        et_cardHolderName = findViewById ( R.id.et_holderName );
        et_expiryDate = findViewById ( R.id.et_expiryDate );
        et_cvv = findViewById ( R.id.et_cvv );

        bt_chargeMoney = findViewById ( R.id.bt_chargeMoney );

        cardNumber = new StringBuilder ( );
        et_cardNumber1.addTextChangedListener ( new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length ( ) == 4) {
                    cardNumber.append ( s );
                    et_cardNumber2.requestFocus ( );
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );
        et_cardNumber2.addTextChangedListener ( new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length ( ) == 4) {
                    cardNumber.append ( s );


                    et_cardNumber3.requestFocus ( );
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );
        et_cardNumber3.addTextChangedListener ( new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length ( ) == 4) {
                    cardNumber.append ( s );

                    et_cardNumber4.requestFocus ( );
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );
        et_cardNumber4.addTextChangedListener ( new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length ( ) == 4) {
                    cardNumber.append ( s );

                    et_cardHolderName.requestFocus ( );
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );


        et_expiryDate.addTextChangedListener ( new TextWatcher ( ) {
            int prvLen = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                prvLen = s.length ( );
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                int len = s.length ( );
                if (s.length ( ) == 2 && prvLen < len) {

                    et_expiryDate.setText ( s + "/" );
                    et_expiryDate.setSelection ( et_expiryDate.getText ( ).length ( ) );
                }
                else if(s.length ()==5){

                    et_cvv.requestFocus ();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

                date=s.toString ();
            }
        } );

        bt_chargeMoney.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                cvv = et_cvv.getText ( ).toString ( );
                String[] arr = date.split ( "/" );

                month= Integer.parseInt ( arr[0] );
                year= Integer.parseInt ( arr[1] );

                System.out.println ("Month and yeat "+month+"/"+year );
                authenticateCard ( cardNumber, month, year, cvv );
            }
        } );
    }

    public boolean authenticateCard(StringBuilder cardNumber, int month, int year, String cvv) {

        card = new Card ( cardNumber.toString ( ), month, year, cvv );

        if (!card.isValid ( )) {
            System.out.println ("Inside Card False" );
            return false;
        } else if (!card.validCVC ( )) {
            et_cvv.requestFocus ( );
            et_cvv.setError ( "Check CVV" );
            return false;
        } else if (card.validNumber ( )) {

            et_cardNumber4.setError ( "Invalid Card" );
            et_cardNumber4.requestFocus ( );
            return false;
        } else {
            System.out.println ("Inside card Success" );
            return true;

        }

    }


}
