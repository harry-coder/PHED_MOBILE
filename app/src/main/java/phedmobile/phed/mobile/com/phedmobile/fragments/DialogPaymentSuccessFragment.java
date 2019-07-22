package phedmobile.phed.mobile.com.phedmobile.fragments;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import phedmobile.phed.mobile.com.phedmobile.R;
import phedmobile.phed.mobile.com.phedmobile.activities.LoginCardOverlap;
import phedmobile.phed.mobile.com.phedmobile.activities.PaymentProfile;
import phedmobile.phed.mobile.com.phedmobile.model.CustomerAdapter;

public class DialogPaymentSuccessFragment extends DialogFragment {

    private View root_view;
    static String  sAmount;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root_view = inflater.inflate(R.layout.dialog_payment_success, container, false);

        TextView tv_id = root_view.findViewById(R.id.receiptunits);
        tv_id.setText(sAmount);

//        static String PaymentLogId,
//                CustReference,
//                AlternateCustReference,
//                Amount,
//                PaymentMethod,
//                PaymentReference,
//                TerminalID,
//                ChannelName,
//                Location,
//                PaymentDate,
//                InstitutionId,
//                InstitutionName,
//                BankName,
//                BranchName,
//                CustomerName,
//                OtherCustomerInfo,
//                ReceiptNo,
//                CollectionsAccount,
//                BankCode,
//                CustomerAddress,
//                CustomerPhoneNumber,
//                DepositorName,
//                DepositSlipNumber,
//                PaymentCurrency,
//                ItemName,
//                ItemCode,
//                ItemAmount,
//                PaymentStatus,
//                IsReversal,
//                SettlementDate,
//                Teller,
//                _AccountNo,
//                _MeterNo,
//                _Arrears,
//                _CurrentAmount,
//                Token,
//                Units,
//                Tariff,
//                Details1 ;

        Date today = new Date();
        DateFormat timeFormat = SimpleDateFormat.getTimeInstance();
        DateFormat dateFormat = SimpleDateFormat.getDateInstance();
        timeFormat.format(today);
        dateFormat.format(today);
        TextView AccountNo_ = (TextView) root_view.findViewById(R.id.receiptaccountnotype);
        AccountNo_.setText(PaymentProfile._AccountNo.toString() + " | " + CustomerAdapter.aAccountType.toString());

        TextView PhoneNo = (TextView) root_view.findViewById(R.id.receiptemailphone);
        PhoneNo.setText(LoginCardOverlap.CustomerEmail.toString() + " | " +LoginCardOverlap.PhoneNo.toString());


        TextView Datereceipt = (TextView) root_view.findViewById(R.id.receiptDate);
        Datereceipt.setText(dateFormat.format(today));


        TextView Timereceipt = (TextView) root_view.findViewById(R.id.receiptTime);
        Timereceipt.setText(timeFormat.format(today));


        TextView Tokenreceipt = (TextView) root_view.findViewById(R.id.receipttoken);
        Tokenreceipt.setText(PaymentProfile.Token.toString());

        TextView Unitreceipt = (TextView) root_view.findViewById(R.id.receiptunits);
        Unitreceipt.setText(PaymentProfile.Units.toString());


        TextView Amountpaid = (TextView) root_view.findViewById(R.id.receiptamount);
        Amountpaid.setText("₦ "+PaymentProfile._CurrentAmount.toString());

        TextView ConsName = (TextView) root_view.findViewById(R.id.receiptName);
        ConsName.setText(PaymentProfile.ConsummerName.toString());


        TextView receiptNumber = (TextView) root_view.findViewById(R.id.receiptnumber);
          receiptNumber.setText("Receipt No: "+PaymentProfile.ReceiptNumber.toString());

        TextView receiptTariff = (TextView) root_view.findViewById(R.id.receipttarifff);
        receiptTariff.setText(PaymentProfile.Tariff.toString());
        return root_view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}