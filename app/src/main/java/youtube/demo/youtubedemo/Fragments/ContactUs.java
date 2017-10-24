package youtube.demo.youtubedemo.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import youtube.demo.youtubedemo.R;

public class ContactUs extends Fragment {

    private static final int RESULT_OK = 1;
    private static final int RESULT_CANCELED = 0;
    EditText name,phn,sub,mass;
    Button snd;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_us,container,false);

        name = (EditText) view.findViewById(R.id.Con_name);
        phn = (EditText) view.findViewById(R.id.Con_Phn);
        sub = (EditText) view.findViewById(R.id.Con_sub);
        mass = (EditText) view.findViewById(R.id.Con_massg);

        snd = (Button)view.findViewById(R.id.Con_send);

        snd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Name = name.getText().toString();
                String No = phn.getText().toString();
                String SUB = sub.getText().toString();
                String masg = mass.getText().toString();



                Intent mail = new Intent(Intent.ACTION_SEND);
                mail.setType("text/html");
                mail.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"tiz00004c8@aspire2student.ac.nz"});
                mail.putExtra(android.content.Intent.EXTRA_SUBJECT, SUB);
                mail.putExtra(android.content.Intent.EXTRA_TEXT, "Name: "+Name + "\nPhone NO: "+ No + "\nMassage: "+ masg);
                mail.setType("message/rfc822");

                try{
                    startActivityForResult(Intent.createChooser(mail, "Send email via:"), 1);



                } catch (android.content.ActivityNotFoundException exception){
                    Toast.makeText(ContactUs.this.getContext(),"there are no email installed",Toast.LENGTH_SHORT).show();
                }
            }
        });






        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // System.out.println("inactivity");
        // Toast.makeText(AllVideos.this, "Mail Send", 10).show();

        System.out.println("inside activity result");

        if (requestCode == 1)
        {
            if (resultCode == RESULT_OK)
            {
                Toast.makeText(ContactUs.this.getContext(), "Please Try Again", Toast.LENGTH_SHORT).show();


            }
            else if (resultCode == RESULT_CANCELED)
            {
                Toast.makeText(ContactUs.this.getContext(), "Mail sent.", Toast.LENGTH_SHORT)
                        .show();
            }
            else
            {
                Toast.makeText(ContactUs.this.getContext(), "RESULT_CANCELED", Toast.LENGTH_SHORT)
                        .show();
                name.setText("");
                phn.setText("");
                sub.setText("");
                mass.setText("");
            }
        }
    }
}