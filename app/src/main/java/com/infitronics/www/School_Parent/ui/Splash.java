package com.infitronics.www.School_Parent.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.infitronics.www.School_Parent.MainActivity;
import com.infitronics.www.School_Parent.R;


public class Splash extends AppCompatActivity implements Runnable {
    public static final String PREFS_NAME = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         final String PREFS_NAME= "MyPrefsFile";
        
        
        
        setContentView(R.layout.activity_splash);
        Thread t=new Thread()
        {
            @Override
            public void run() {
                try
                {
                    Thread.sleep(3000);
                    SharedPreferences settings = getSharedPreferences(Splash.PREFS_NAME, 0);
//Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
                    boolean hasLoggedIn = settings.getBoolean("hasLoggedIn", false);

                    if(hasLoggedIn)
                    {
                        //Go directly to main activity.

                        Intent i = new Intent(Splash.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else
                        {
                            Intent i = new Intent(Splash.this, Login.class);
                            startActivity(i);
                            finish();
                        }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };

t.start();
//            t.sleep(5000);
//            t.run();

        //t.start();
    }



    @Override
    public void run()
    {

    }
}
