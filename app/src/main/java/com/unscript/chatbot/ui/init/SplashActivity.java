package com.unscript.chatbot.ui.init;

import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.unscript.chatbot.R;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class SplashActivity extends AwesomeSplash {


    @Override
    public void initSplash(ConfigSplash configSplash) {
        configSplash.setTitleSplash("Asar Agent");
//        configSplash.setLogoSplash(R.drawable.splash);
        configSplash.setBackgroundColor(R.color.colorPrimary);
        configSplash.setOriginalHeight(200); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(200); //in relation to your svg (path) resource
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)

    }

    @Override
    public void animationsFinished() {

//        Intent i = new Intent(this, LaunchActivity.class);
//        startActivity(i);
        finish();
    }
}