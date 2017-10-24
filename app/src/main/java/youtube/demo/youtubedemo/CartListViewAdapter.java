package youtube.demo.youtubedemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abidroid on 4/22/2017.
 */

public class CartListViewAdapter extends ArrayAdapter<Product> {

    List<Product> products = new ArrayList<Product>();

    public CartListViewAdapter(Context context, int resource,
                           List<Product> objects) {
        super(context, resource, objects);
        products = objects;
    }

    View v = null;

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        v = convertView;

        if(null == v) {
            LayoutInflater inflater =
                    (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.cart_list_item, null);
        }
        final Product product = getItem(position);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        TextView txtTitle = (TextView) v.findViewById(R.id.txtTitle);
        TextView txtValue = (TextView) v.findViewById(R.id.txtValue);
        //Button b = (Button) v.findViewById(R.id.btn1);
        img.setImageResource(product.getImageId());
        txtTitle.setText(product.getTitle());
        // txtValue.setText(product.getValue());
        txtValue.setText("$"+Double.toString(product.getValue()));


        return v;
    }


    public void RemoveAll() {
        if(products == null)
            return;

        products.clear();
    }
}
