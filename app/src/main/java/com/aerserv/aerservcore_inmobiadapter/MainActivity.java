package com.aerserv.aerservcore_inmobiadapter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;

import com.aerserv.sdk.*;

import java.util.List;

public class MainActivity extends Activity {

    //Class Elements
    private AerServBanner banner;
    private AerServInterstitial interstitial;
    private AerServEventListener listener;

    //UI Elements
    private EditText plcText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        createListener();
        AerServSdk.init(MainActivity.this, "380000");
    }

    public void loadInterstitial(View view){
        AerServConfig config = new AerServConfig(this, getPlcText());
        config.setEventListener(listener);
        interstitial = new AerServInterstitial(config);
        interstitial.show();
    }

    public void loadBanner(View view){
        // The PLC 1024876 is for testing only and returns a 320x50 static HTML banner
        AerServConfig config = new AerServConfig(this,getPlcText());
        config.setEventListener(listener);
        banner.configure(config).show();
    }

    public void killBanner(View view){
        if(banner != null){
            banner.kill();
        }
    }
    private void initViews(){
        plcText = (EditText) findViewById(R.id.plc);
        banner = (AerServBanner) findViewById(R.id.banner);
    }

    private String getPlcText(){
        return plcText.getText().toString();
    }

    private void createListener(){
        listener = new AerServEventListener() {
            @Override
            public void onAerServEvent(AerServEvent event, List params) {
                switch (event) {
                    case AD_LOADED:
                        System.out.println("AD_LOADED");
                        break;
                    case AD_FAILED:
                        System.out.println("AD_FAILED");
                        break;
                    case AD_CLICKED:
                        System.out.println("AD_CLICKED");
                        break;
                    case AD_COMPLETED:
                        System.out.println("AD_COMPLETED");
                        break;
                    case AD_DISMISSED:
                        System.out.println("AD_DISMISSED");
                        break;
                    case AD_IMPRESSION:
                        System.out.println("AD_IMPRESSION");
                        break;
                    case PRELOAD_READY:
                        System.out.println("PRELOAD_READY");
                        break;
                    case VC_READY:
                        System.out.println("VC_READY");
                        break;
                    case VC_REWARDED:
                        System.out.println("VC_REWARDED");
                        break;
                    case VIDEO_COMPLETED:
                        System.out.println("VIDEO_COMPLETED");
                        break;
                    case VIDEO_START:
                        System.out.println("VIDEO_START");
                        break;
                }
            }
        };
    }
}
