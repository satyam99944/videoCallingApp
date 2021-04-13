package com.example.guftgo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DashboardActivity extends AppCompatActivity {
 EditText secretCode;
 Button join,share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        secretCode=findViewById(R.id.secret_code);
        join=findViewById(R.id.join);
        share=findViewById(R.id.share);
        URL serverUrl;
        try {
            serverUrl=new URL("https://meet.jit.si");
            try {
                try {
                    JitsiMeetConferenceOptions defaultOption=new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverUrl)
                            .setWelcomePageEnabled(false).build();
                    JitsiMeet.setDefaultConferenceOptions(defaultOption);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                JitsiMeetConferenceOptions options=new JitsiMeetConferenceOptions
//                        .Builder().setRoom(secretCode.getText().toString()).setWelcomePageEnabled(false)
//                        .build();
                JitsiMeetConferenceOptions options = null;
                try {
                    options = new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(new URL("https://meet.jit.si"))
                            .setRoom(secretCode.getText().toString())
                            .setAudioMuted(false)
                            .setVideoMuted(false)
                            .setAudioOnly(false)
                            .setWelcomePageEnabled(false)
                            .build();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                JitsiMeetActivity.launch(DashboardActivity.this,options);
            }
        });
    }
}