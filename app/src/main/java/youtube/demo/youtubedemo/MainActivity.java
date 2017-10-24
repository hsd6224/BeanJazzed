package youtube.demo.youtubedemo;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import youtube.demo.youtubedemo.Fragments.ContactUs;
import youtube.demo.youtubedemo.Fragments.Login;
import youtube.demo.youtubedemo.Fragments.MainFragment;
import youtube.demo.youtubedemo.Fragments.Profile;
import youtube.demo.youtubedemo.Fragments.ShopOnline;
import youtube.demo.youtubedemo.Fragments.SignUp;
import youtube.demo.youtubedemo.Fragments.ViewCart;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    // any number you want

    FragmentManager fm = getFragmentManager();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main,container,false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //m_response = (TextView) findViewById(R.id.response);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.content_frame, new ViewCart()).commit();


            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getFragmentManager();
       fm.beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
       FragmentManager fm = getFragmentManager();
        int id = item.getItemId();

        if (id == R.id.nav_signup) {
         fm.beginTransaction().replace(R.id.content_frame, new SignUp()).commit();
        }else if (id == R.id.nav_login) {
            fm.beginTransaction().replace(R.id.content_frame, new Login()).commit();

        }else if (id == R.id.nav_profile) {
            fm.beginTransaction().replace(R.id.content_frame, new Profile()).commit();
        }
        else if (id == R.id.nav_shoponline) {

            fm.beginTransaction().replace(R.id.content_frame, new ShopOnline()).commit();

        }  else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

    } else if (id == R.id.nav_contactus) {
            fm.beginTransaction().replace(R.id.content_frame, new ContactUs()).commit();

        }
        else if (id == R.id.nav_viewcart) {


           fm.beginTransaction().replace(R.id.content_frame, new ViewCart()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
