package com.example.postsapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.postsapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startFragment();
    }

    private void startFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment newFragment = fm.findFragmentById(R.id.main_frame_layout);
        if (newFragment == null) {
            newFragment = new PostFragment();
            fm.beginTransaction().add(R.id.main_frame_layout, newFragment)
                    .commit();
        }
    }
}