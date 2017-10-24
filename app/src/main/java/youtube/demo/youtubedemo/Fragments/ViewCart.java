package youtube.demo.youtubedemo.Fragments;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;
import java.util.ArrayList;

import youtube.demo.youtubedemo.CartListViewAdapter;
import youtube.demo.youtubedemo.Product;
import youtube.demo.youtubedemo.R;
import youtube.demo.youtubedemo.ShoppingCartHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewCart extends Fragment {

    ListView lvCart;
    TextView tvTotal;
    CartListViewAdapter cartListViewAdapter;
    ArrayList<Product> productArrayList;
    PayPalConfiguration m_configuration;
    // the id is the link to the paypal account, we have to create an app and get its id
    String m_paypalClientId = "Aa-6cCEUqzRvpgRg9XIjeiHgQcKPhZK0_wsZbTR1MjJIhXSf-9kNOdi0jRSb7BocmfuS1vWO8jOocdWa";
    Intent m_service;
    static ShoppingCartHelper m_cart = new ShoppingCartHelper();



    long lTotal = 0;
    int m_paypalRequestCode = 999;
    TextView m_response;

    public ViewCart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_view_cart, container, false);

        Button btnReset =  (Button) view.findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset(view);
            }
        });

        Button btnPay =  (Button) view.findViewById(R.id.btnPay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay(view);
            }
        });


        m_configuration = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX) // sandbox for test, production for real
                .clientId(m_paypalClientId);

        m_service = new Intent(this.getActivity(), PayPalService.class);
        m_service.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, m_configuration); // configuration above
        this.getActivity().startService(m_service); // paypal service, listening to calls to paypal app


        lvCart = (ListView) view.findViewById(R.id.listview_cart);
        tvTotal = (TextView) view.findViewById(R.id.tvTotalProducts);
        productArrayList = ShoppingCartHelper.getProductInCart();
        productArrayList = ShoppingCartHelper.getProductInCart();

        cartListViewAdapter = new CartListViewAdapter(getActivity(), R.layout.cart_list_item, productArrayList);
        lvCart.setAdapter(cartListViewAdapter);
        resetTotalProducts();

        return view;
    }

    public void resetTotalProducts()
    {
        tvTotal.setText("Total Products : " + productArrayList.size());
    }

    public long getTotal()
    {
        return productArrayList.size();
    }

    void reset(View view)
    {
        m_cart.empty();
        cartListViewAdapter.RemoveAll();
        cartListViewAdapter.notifyDataSetChanged();
        resetTotalProducts();

        Toast.makeText(this.getActivity(), "Cart Is Empty ", Toast.LENGTH_SHORT).show();
    }

    void pay(View view)
    {
        if(m_cart.getValue() <= 0) {
            Toast.makeText(this.getActivity(), "Please Add Product First ", Toast.LENGTH_SHORT).show();
        } else {
            final SharedPreferences pref = ViewCart.this.getContext().getSharedPreferences("MyPref", 0);
            String email = pref.getString("Sessionemail","");

            if(false == email.isEmpty() ){

           /* PayPalPayment cart = new PayPalPayment(new BigDecimal(m_cart.getValue()), "USD", "Cart",
                    PayPalPayment.PAYMENT_INTENT_SALE);*/
            PayPalPayment cart = new PayPalPayment(new BigDecimal(m_cart.getValue()), "NZD", "Total Amount",
                    PayPalPayment.PAYMENT_INTENT_SALE);

            Intent intent = new Intent(this.getActivity(), PaymentActivity.class); // it's not paypalpayment, it's paymentactivity !
            intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, m_configuration);
            intent.putExtra(PaymentActivity.EXTRA_PAYMENT, cart);
            startActivityForResult(intent, m_paypalRequestCode);}
            else{
                Toast.makeText(ViewCart.this.getContext(), "Please Log IN" ,Toast.LENGTH_LONG).show();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.content_frame, new Login()).commit();
            }





        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == m_paypalRequestCode)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                // we have to confirm that the payment worked to avoid fraud
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);


                if(confirmation != null)
                {
                    String state = confirmation.getProofOfPayment().getState();

                    if(state.equals("approved")) // if the payment worked, the state equals approved
                        // m_response.setText("payment approved");
                        //  Toast.makeText(this, productList.get(position).getTitle() + " - " + " $" + productList.get(position).getValue(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(this.getActivity(), "payment approved", Toast.LENGTH_SHORT).show();

                        // fm.beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();


                    else
                        Toast.makeText(this.getActivity(), " Error in the payment ", Toast.LENGTH_SHORT).show();
                    //m_response.setText("error in the payment");
                }
                else
                    //m_response.setText("confirmation is null");
                    Toast.makeText(this.getActivity(), "confirmation is null", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
