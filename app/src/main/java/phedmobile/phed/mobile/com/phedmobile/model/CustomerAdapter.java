package phedmobile.phed.mobile.com.phedmobile.model;

import android.content.Context;
import android.content.Intent;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import phedmobile.phed.mobile.com.phedmobile.R;
import phedmobile.phed.mobile.com.phedmobile.activities.Checkout;


public class CustomerAdapter extends RecyclerView.Adapter < CustomerAdapter.CustomerViewHolder > {

    List<UserAccounts> mCustomerList;
    public static String aAccountType;
    public static String aAccountNo;
    public static String aPhoneNo;
    public static String aAPIKey;
    Context context;

    public CustomerAdapter(Context context) {
        this.context = context;
        mCustomerList = new ArrayList<>();
    }

    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_addaccounts_singleitem, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomerViewHolder holder, int position) {

        UserAccounts customer = mCustomerList.get(position);

        holder.AccountNo.setText(customer.mCustomerAccountNo);
        holder.CustomerName.setText(customer.mCustomerAccountName);
        holder.AccountType.setText(customer.mCustomerAccountType);

    }


    @Override
    public int getItemCount() {
        return mCustomerList.size();
    }



    public void setSource(ArrayList<UserAccounts> userAccounts) {
        this.mCustomerList = userAccounts;
        notifyDataSetChanged();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {

        //find the card
        TextView AccountNo, CustomerName, AccountType;

        public CustomerViewHolder(final View itemView) {


            super(itemView);

            final CardView cdvw = (CardView) itemView.findViewById(R.id.cv_itemCard);

            cdvw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int f = getAdapterPosition();
                    UserAccounts account = mCustomerList.get(f);
                    String AccountNumber = account.mCustomerAccountNo;

                    aAccountType = account.mCustomerAccountType;
                    aAccountNo = account.mCustomerAccountNo;
                    // aAccountNo = account.mCustomerAccountName;
                    Intent intent = new Intent(itemView.getContext(), Checkout.class );
                    itemView.getContext().startActivity(intent);
                }
            });

            AccountNo = (TextView) itemView.findViewById(R.id.mAccountNumber);
            CustomerName = (TextView) itemView.findViewById(R.id.mCustomerName);
            AccountType = (TextView) itemView.findViewById(R.id.mAccountType);
        }
    }
//    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
}