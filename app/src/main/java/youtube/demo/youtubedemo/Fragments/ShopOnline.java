package youtube.demo.youtubedemo.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import youtube.demo.youtubedemo.GridViewAdapter;
import youtube.demo.youtubedemo.ListViewAdapter;
import youtube.demo.youtubedemo.Product;
import youtube.demo.youtubedemo.R;

import static android.content.Context.MODE_PRIVATE;

public class ShopOnline extends Fragment {
    private ViewStub stubGrid;
    private ViewStub stubList;
    private ListView listView;
    private GridView gridView;
    private ListViewAdapter listViewAdapter;
    private GridViewAdapter gridViewAdapter;
    private List<Product> productList;
    private int currentViewMode = 0;

    static final int VIEW_MODE_LISTVIEW = 0;
    static final int VIEW_MODE_GRIDVIEW = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.activity_list_view, container, false);


// return inflater.inflate(R.layout.activity_list_view,container,false);
        stubList = (ViewStub) rootView.findViewById(R.id.stub_list);
        stubGrid = (ViewStub) rootView.findViewById(R.id.stub_grid);

//Inflate ViewStub before get view

        stubList.inflate();
        stubGrid.inflate();

        listView = (ListView) rootView.findViewById(R.id.mylistview);
        gridView = (GridView) rootView.findViewById(R.id.mygridview);


//get list of product
        getProductList();

//Get current view mode in share reference
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("ViewMode", MODE_PRIVATE);
        currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_LISTVIEW);//Default is view listview
//Register item lick

        listView.setOnItemClickListener(onItemClick);
        gridView.setOnItemClickListener(onItemClick);

        switchView();
        return rootView;
    }

    private void switchView() {

        if (VIEW_MODE_LISTVIEW == currentViewMode) {
//Display listview
            stubList.setVisibility(View.VISIBLE);
//Hide gridview
            stubGrid.setVisibility(View.GONE);
        } else {
//Hide listview
            stubList.setVisibility(View.GONE);
//Display gridview
            stubGrid.setVisibility(View.VISIBLE);
        }
        setAdapters();
    }

    private void setAdapters() {
        if (VIEW_MODE_LISTVIEW == currentViewMode) {
            listViewAdapter = new ListViewAdapter(getActivity(), R.layout.list_item, productList);
            listView.setAdapter(listViewAdapter);
        } else {
            gridViewAdapter = new GridViewAdapter(getActivity(), R.layout.grid_item, productList);
            gridView.setAdapter(gridViewAdapter);
        }
    }

    public List<Product> getProductList() {
//pseudo code to get product, replace your code to get real product here
        productList = new ArrayList<>();
        productList.add(new Product(R.mipmap.icon_android, "Fresh roasted whole beans   1 kg", 36.00));
        productList.add(new Product(R.mipmap.icon_android, "Fresh roasted whole beans 0.5 kg", 19.00));
        productList.add(new Product(R.mipmap.icon_android, "Fresh roasted whole beans 0.2 kg", 9.50));
        productList.add(new Product(R.mipmap.icon_android, "Fresh roasted ground coffee 0.2 kg", 8.50));


        return productList;
    }

    AdapterView.OnItemClickListener onItemClick = new
            AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int
                        position, long id) {
//Do any thing when user click to item
              //      Toast.makeText(getActivity(), productList.get(position).getTitle() + " - " + " $" + productList.get(position).getValue(), Toast.LENGTH_SHORT).show();
                }
            };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.main, menu);
    super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_menu_1:
                if (VIEW_MODE_LISTVIEW == currentViewMode) {
                    currentViewMode = VIEW_MODE_GRIDVIEW;
                } else {
                    currentViewMode = VIEW_MODE_LISTVIEW;
                }
//Switch view
                switchView();
//Save view mode in share reference
                SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("ViewMode", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("currentViewMode", currentViewMode);
                editor.commit();
                break;
            case R.id.item_viewcart:
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.content_frame, new ViewCart()).commit();

        }
        return true;
    }


}


