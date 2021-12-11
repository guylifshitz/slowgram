package com.guylifshitz.slowgram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    int countDownTime = 5;
    Runnable m_handlerTask ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler m_handler = new Handler();
        m_handlerTask = new Runnable()
        {
            @Override
            public void run() {
                TextView tv = (TextView)findViewById(R.id.countdownTime);
                tv.setText(""+countDownTime);

                countDownTime = countDownTime - 1;
                m_handler.postDelayed(m_handlerTask, 1000);

                if (countDownTime < 0){
                    launchInstagram(getApplicationContext());
                    countDownTime = 0;
                    finishAffinity();
                    System.exit(0);
                }

            }
        };
        m_handlerTask.run();


    }

   void launchInstagram(Context context){
        Uri uri = Uri.parse("http://instagram.com/");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
        likeIng.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        likeIng.setPackage("com.instagram.android");

        try
        {
            context.startActivity(likeIng);
        }
        catch (ActivityNotFoundException e)
        {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/")));
        }
    }
}