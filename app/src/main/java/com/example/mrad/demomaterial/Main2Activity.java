package com.example.mrad.demomaterial;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.telly.mrvector.MrVector;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.app_bar)
    Toolbar toolbar;
    @BindView(R.id.iv)
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Drawable drawable=null;

        if(Build.VERSION.SDK_INT>=16) {
            drawable=MrVector.inflate(getResources(),R.drawable.animator_vector_clock);

        }
        else
        {
            drawable=MrVector.inflate(getResources(),R.drawable.vector_clock);
        }

        if(Build.VERSION.SDK_INT>=16) {
           iv.setBackground(drawable);

        }
        else
        {
            iv.setBackgroundDrawable(drawable);
        }

        if(drawable instanceof Animatable)
        {
            ((Animatable) drawable).start();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.navigate)
        {
            startActivity(new Intent(this,Main2Activity.class));
        }

        if(item.getItemId()==android.R.id.home)
        {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
