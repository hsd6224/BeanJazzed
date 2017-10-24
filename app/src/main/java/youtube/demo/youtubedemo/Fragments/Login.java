package youtube.demo.youtubedemo.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

import youtube.demo.youtubedemo.R;

/**
 * Created by Riaz UD Din on 30/03/2017.
 */

public class Login extends Fragment {

    public Login() {

    }

    EditText logemail, logpass;

    Button logindone;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        final SharedPreferences pref = Login.this.getContext().getSharedPreferences("MyPref", 0);
        String email = pref.getString("Sessionemail", "");

        logemail = (EditText) view.findViewById(R.id.etlogin_email);
        logpass = (EditText) view.findViewById(R.id.Etlogin_pass);
        logindone = (Button) view.findViewById(R.id.Etlogin_done);


        final String LOG = "Signin";

        if (true == email.isEmpty()) {
        logindone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String  logem = logemail.getText().toString();
                String logpas = logpass.getText().toString();

                // Check for both field is empty or not
                if (logem.equals("") || logem.length() == 0
                        || logpas.equals("") || logpas.length() == 0) {
                    Toast.makeText(Login.this.getContext(), "Enter both credentials." ,Toast.LENGTH_LONG).show();
                }

                HashMap postData = new HashMap();

                    postData.put("email", logemail.getText().toString());
                postData.put("pass", logpass.getText().toString());


                PostResponseAsyncTask loginTask;
                loginTask = new PostResponseAsyncTask(Login.this.getContext(), postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        Log.d(LOG, s);
                        //if (s.contains("successful sign in")) {
                        if(false == s.isEmpty()) {

                            String strFullName = "";
                            String strEmail = "";
                            String strAddress = "";
                            String strPhone = "";

                            String[] strTokens = s.split("&&");

                            strFullName = strTokens[0];
                            strFullName = strFullName.substring(2, strFullName.length());
                            strEmail = strTokens[1];
                            strAddress = strTokens[2];
                            strPhone = strTokens[3];
                            strPhone = strPhone.substring(0, strPhone.length()-2);

//                            Toast.makeText(Login.this.getContext(), s ,Toast.LENGTH_LONG).show();

                            FragmentManager fm = getFragmentManager();

                            SharedPreferences pref = Login.this.getContext().getSharedPreferences("MyPref", 0);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("Sessionemail",strEmail);
                            editor.putString("Sessionname",strFullName);
                            editor.putString("Sessionadd",strAddress);
                            editor.putString("Sessionphone",strPhone);
                            editor.commit();

                            fm.beginTransaction().replace(R.id.content_frame, new ShopOnline()).commit();

                        }
                        else if(false == s.isEmpty()){
                            FragmentManager fm = getFragmentManager();

                            Toast.makeText(Login.this.getContext(), "Incorect Information please try again." ,Toast.LENGTH_LONG).show();
                            fm.beginTransaction().replace(R.id.content_frame, new Login()).commit();
                        }
                    }

                });
                loginTask.execute("http:/10.0.2.2/beansignin.php");
            }
        });}
        else{
            Toast.makeText(Login.this.getContext(), "Your already Sign in." ,Toast.LENGTH_LONG).show();
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.content_frame, new Profile()).commit();
        }
        return view;
    }
}
