package tomerbu.edu.animationsparttwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.AutoTransition;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;

public class ExplodeActivity extends AppCompatActivity {

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               animateExplode();
            }
        });
    }

    private void animateExplode() {
        Intent intent = new Intent(this, CircularActivity.class);

        //set the transition
        getWindow().setExitTransition(new Explode());
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this, fab, "fab").toBundle();

        startActivity(intent, bundle);
    }

}
