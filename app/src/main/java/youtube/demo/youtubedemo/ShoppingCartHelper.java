package youtube.demo.youtubedemo;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by abidroid on 4/22/2017.
 */

public class ShoppingCartHelper extends Application{
    static double m_value = 0;

    private static ArrayList<Product> cart;



    public static void addToCart(Context c, Product p)
    {

        if( cart == null )
            cart = new ArrayList<>();

        boolean ALREADY_ADDED = false;
        if( cart.size() == 0 )
        {
            cart.add(p);
            Toast.makeText(c, "Product added", Toast.LENGTH_SHORT).show();
            m_value += p.getValue();
        }
        else
        {
            for (int i = 0; i < cart.size(); i++)
            {
                if( cart.get(i) == p)
                {
                    ALREADY_ADDED = true;
                    break;
                }
            }

            if( ALREADY_ADDED)
            {
                //Toast.makeText(c, "Already Added", Toast.LENGTH_SHORT).show();
                Toast.makeText(c, "Product added", Toast.LENGTH_SHORT).show();
                cart.add(p);
                m_value += p.getValue();
            }
            else
            {
                Toast.makeText(c, "Product added", Toast.LENGTH_SHORT).show();
                cart.add(p);
                m_value += p.getValue();
            }
        }

        ALREADY_ADDED = false;
    }
    public double getValue()
    {
        return m_value;
    }
    public static ArrayList<Product> getProductInCart()
    {
        if(cart == null )
            return new ArrayList<Product>();

        return cart;
    }


   public static  void empty()
    {
        cart = null;
        m_value = 0;
    }

    public static int getItemsInCart()
    {
        return cart.size();
    }

}
