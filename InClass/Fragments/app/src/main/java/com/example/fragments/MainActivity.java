package com.example.fragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction ft;
    FragmentRed redFragment;
    FragmentBlue blueFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create a new BLUE fragment - show it
        ft = getFragmentManager().beginTransaction();
        blueFragment = FragmentBlue.newInstance("first-blue");
        ft.replace(R.id.main_holder_blue, blueFragment);
        ft.commit();

        // create a new RED fragment - show it
        ft = getFragmentManager().beginTransaction();
        redFragment = FragmentRed.newInstance("first-red");
        ft.replace(R.id.main_holder_red, redFragment);
        ft.commit();

    }

    // MainCallback implementation (receiving messages coming from Fragments)
    @Override
    public void onMsgFromFragToMain(String sender, String strValue) {
        // show message arriving to MainActivity
        Toast.makeText(getApplication(),
                " MAIN GOT>> " + sender + "\n" + strValue, Toast.LENGTH_LONG)
                .show();
        if (sender.equals("RED-FRAG")) {
        // TODO: if needed, do here something on behalf of the RED fragment
        }
        if (sender.equals("BLUE-FRAG")) {
            try {
                // forward blue-data to redFragment using its callback method
                redFragment.onMsgFromMainToFragment("\nSender: " + sender
                        + "\nMsg: " + strValue);
            }
            catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
    }
}
