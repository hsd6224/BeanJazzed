package youtube.demo.youtubedemo.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import youtube.demo.youtubedemo.R;


public class Profile extends Fragment {

    TextView fn,em,ad,n;
    Button logout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        final SharedPreferences pref = Profile.this.getContext().getSharedPreferences("MyPref", 0);
        String email = pref.getString("Sessionemail","");
        String fnam = pref.getString("Sessionname","");
        String add = pref.getString("Sessionadd","");
        String phoneno = pref.getString("Sessionphone","");

        fn = (TextView)view.findViewById(R.id.pr_name);
        em = (TextView)view.findViewById(R.id.pr_email);
        ad =(TextView)view.findViewById(R.id.Pr_add);
        n = (TextView)view.findViewById(R.id.Pr_PhoneNo);
        logout =(Button)view.findViewById(R.id.Logout);

        if(false == email.isEmpty() ){
            fn.setText(fnam);
            em.setText(email);
            ad.setText(add);
            n.setText(phoneno);
            logout.setText("Log out");
        }
        else{
            Toast.makeText(Profile.this.getContext(), "Please Log IN" ,Toast.LENGTH_LONG).show();
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.content_frame, new Login()).commit();
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pref.edit().clear().commit();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.content_frame, new Login()).commit();

            }
        });
        return view;
    }
}