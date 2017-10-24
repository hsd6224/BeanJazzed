package youtube.demo.youtubedemo;

/**
 * Created by chink on 24/03/2017.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;




public class GridViewAdapter extends ArrayAdapter<Product> {
    public GridViewAdapter(Context context, int resource,
                           List<Product> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(null == v) {
            LayoutInflater inflater =
                    (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.grid_item, null);
        }
        final Product product = getItem(position);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        TextView txtTitle = (TextView) v.findViewById(R.id.txtTitle);
        TextView txtValue = (TextView)
                v.findViewById(R.id.txtValue);
        Button b = (Button) v.findViewById(R.id.button2);
        img.setImageResource(product.getImageId());
        txtTitle.setText(product.getTitle());
        //txtValue.setText(product.getValue());
        txtValue.setText("$"+Double.toString(product.getValue()));
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingCartHelper.addToCart(getContext(), product);

                //Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }



}
