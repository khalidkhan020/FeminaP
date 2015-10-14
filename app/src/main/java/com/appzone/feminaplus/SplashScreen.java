package com.appzone.feminaplus;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class SplashScreen extends AppCompatActivity
{

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(1000);
        AnimationSet animation = new AnimationSet(false); //change to false
        animation.addAnimation(fadeIn);
        animation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation)
            {
            }

            @Override public void onAnimationEnd(Animation animation)
            {
                moveViewToScreenCenter(findViewById(R.id.logo));
            }

            @Override public void onAnimationRepeat(Animation animation)
            {
            }
        });
        // animation.addAnimation(fadeOut);
        findViewById(R.id.background).setAnimation(animation);
    }

    private void moveViewToScreenCenter(final View view)
    {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int originalPos[] = new int[2];
        view.getLocationOnScreen(originalPos);
        int          xDelta  = originalPos[0] - view.getMeasuredWidth() / 2;
        int          yDelta  = originalPos[1] - view.getMeasuredHeight() / 2;
        AnimationSet animSet = new AnimationSet(true);
        animSet.setFillAfter(true);
        animSet.setDuration(2000);
        animSet.setInterpolator(new BounceInterpolator());
        TranslateAnimation translate = new TranslateAnimation(dm.widthPixels, xDelta, dm.heightPixels, yDelta);
        animSet.addAnimation(translate);
        ScaleAnimation scale = new ScaleAnimation(0f, 1f, 0f, 1f, ScaleAnimation.ZORDER_TOP, .5f, ScaleAnimation.RELATIVE_TO_PARENT, .5f);
        animSet.addAnimation(scale);
        animSet.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation)
            {
            }

            @Override public void onAnimationEnd(Animation animation)
            {
                Intent intent = new Intent(SplashScreen.this, Dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                SplashScreen.this.finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }

            @Override public void onAnimationRepeat(Animation animation)
            {
            }
        });
        view.startAnimation(animSet);
        view.setVisibility(View.VISIBLE);
    }
}
