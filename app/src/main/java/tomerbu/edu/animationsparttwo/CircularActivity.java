package tomerbu.edu.animationsparttwo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.RelativeLayout;

public class CircularActivity extends AppCompatActivity {
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular);
        layout = (RelativeLayout) findViewById(R.id.layout);


        getWindow().getEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                 reveal(null);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void reveal(View view) {
        final boolean shouldHide = layout.getVisibility() == View.VISIBLE;
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.LOLLIPOP) {
            if (shouldHide)
                layout.setVisibility(View.INVISIBLE);
            else
                layout.setVisibility(View.VISIBLE);
            return;
        }


        int cx = layout.getWidth();
        int cy = layout.getHeight();

        float startRadius = 0;
        float endRadius = 0;

        if (shouldHide) {
            startRadius = Math.max(layout.getWidth(), layout.getHeight());
            endRadius = 0;
        } else {
            startRadius = 0;
            endRadius = Math.max(layout.getWidth(), layout.getHeight());
        }

        Animator circularReveal = ViewAnimationUtils.createCircularReveal(layout, cx, cy, startRadius, endRadius);


        if (!shouldHide) {
            layout.setVisibility(View.VISIBLE);
        }

        circularReveal.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (shouldHide)
                    layout.setVisibility(View.INVISIBLE);
            }
        });
        circularReveal.start();

    }

    public void gotoMain(View view) {

    }
}
