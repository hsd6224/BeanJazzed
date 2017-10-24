package youtube.demo.youtubedemo.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class SignUp extends Fragment {

    public SignUp() {
    }

    View view;
    EditText fulln, add, ph, em, pa;
    Button Rigs;
    final String beanlog = "Registration";


    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_sign_up, parent, false);

        final SharedPreferences pref = SignUp.this.getContext().getSharedPreferences("MyPref", 0);
        String email = pref.getString("Sessionemail", "");

        fulln = (EditText) view.findViewById(R.id.et_Fullname);
        add = (EditText) view.findViewById(R.id.et_Address);
        ph = (EditText) view.findViewById(R.id.et_phone);
        em = (EditText) view.findViewById(R.id.et_Email);
        pa = (EditText) view.findViewById(R.id.et_Pass);
        Rigs = (Button) view.findViewById(R.id.btnSign_up);

        if (true == email.isEmpty()) {

            Rigs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HashMap postData = new HashMap();

                    String getFuname = fulln.getText().toString();
                    String getad = add.getText().toString();
                    String getphno = ph.getText().toString();
                    String getema = em.getText().toString();
                    String getpasw = pa.getText().toString();


                    postData.put("fullname", getFuname);
                    postData.put("email", getema);
                    postData.put("addr", getad);
                    postData.put("Ph_no", getphno);
                    postData.put("pass", getpasw);

                    postData.put("mobile", "android");

                    PostResponseAsyncTask taskInsert = new PostResponseAsyncTask(SignUp.this.getContext(), postData, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            Log.d(beanlog, s);
                            if (s.contains("success")) {
                                FragmentManager fm = getFragmentManager();
                                Toast.makeText(SignUp.this.getContext(), "Thanks For Sign Up\n Please sign in with same information to continue" ,Toast.LENGTH_LONG).show();
                                fm.beginTransaction().replace(R.id.content_frame, new Login()).commit();
                            }
                        }

                    });
                    taskInsert.execute("http:/10.0.2.2/beansignup.php");
                }
            });
        }

        else{
            Toast.makeText(SignUp.this.getContext(), "Your already Sign in.\n Please Log out then come back for Sign up" ,Toast.LENGTH_LONG).show();
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.content_frame, new Profile()).commit();}

            return view;
    }
}

