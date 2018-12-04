package com.agtechnosolution.entertainia.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.agtechnosolution.entertainia.R;

public class MainActivity extends AppCompatActivity {

    RecyclerView moviesRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moviesRecycler=findViewById(R.id.movies_recycler);
    }
}
