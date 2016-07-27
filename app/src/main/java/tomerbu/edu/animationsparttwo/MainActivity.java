package tomerbu.edu.animationsparttwo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private ImageView ivCloud1;
    private ImageView ivCloud2;
    private ImageView ivCloud3;
    private ImageView ivCloud4;
    private RelativeLayout layout;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;


    //1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    private void findViews() {
        ivCloud1 = (ImageView) findViewById(R.id.ivCloud1);
        ivCloud2 = (ImageView) findViewById(R.id.ivCloud2);
        ivCloud3 = (ImageView) findViewById(R.id.ivCloud3);
        ivCloud4 = (ImageView) findViewById(R.id.ivCloud4);
        layout = (RelativeLayout) findViewById(R.id.layout);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    private void animateClouds(View... cloudViews) {
        Random rand = new Random();
        for (View cloudView : cloudViews) {

            int layoutWidth = layout.getWidth();
            int cloudWidth = cloudView.getWidth();

            float startCloudPosition = 0 - cloudWidth;
            float finalCloudPosition = layoutWidth + cloudWidth;


            ObjectAnimator cloudAnimator = ObjectAnimator.ofFloat(cloudView, "X", startCloudPosition, finalCloudPosition);
            cloudAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            cloudAnimator.setRepeatMode(ValueAnimator.RESTART);
            cloudAnimator.setDuration(6000 + rand.nextInt(3000));
            cloudAnimator.setStartDelay(rand.nextInt(2000));
            cloudAnimator.start();

        }
    }


    //3)
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        moveViewOut(ivCloud1, ivCloud2, ivCloud3, ivCloud4);
        animateClouds(ivCloud1, ivCloud2, ivCloud3, ivCloud4);

        moveViewOut(etEmail, etPassword, btnLogin);
        animateLogin();

    }

    private void animateLogin() {
        float xMid = layout.getWidth() / 2;
        float xet = xMid - etEmail.getWidth() / 2;
        float x2 = xet + etEmail.getWidth();
        float xBtn = x2 - btnLogin.getWidth();

        ObjectAnimator etEmailAnimator = ObjectAnimator.ofFloat(etEmail, "X", xet);
        etEmailAnimator.setInterpolator(new BounceInterpolator());
        etEmailAnimator.setDuration(1000);
        etEmailAnimator.start();


        ObjectAnimator etPasswordAnimator = ObjectAnimator.ofFloat(etPassword, "X", xet);
        etPasswordAnimator.setInterpolator(new BounceInterpolator());
        etPasswordAnimator.setDuration(1000);
        etPasswordAnimator.setStartDelay(700);
        etPasswordAnimator.start();


        ObjectAnimator btnAnimator = ObjectAnimator.ofFloat(btnLogin, "X", xBtn);
        btnAnimator.setInterpolator(new BounceInterpolator());
        btnAnimator.setDuration(1000);
        btnAnimator.setStartDelay(1500);
        btnAnimator.start();


    }



    void moveViewOut(View... views) {
        for (View v : views) {
            v.setX(0 - v.getWidth());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
