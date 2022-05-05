package com.rotationmap.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import rotationmap.RotationMap;

public class MainActivity extends AppCompatActivity {

    private final int[] mImages = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5};
    private List<Integer> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = new ArrayList<>();
        for (int i : mImages) {
            mList.add(i);
        }
        new RotationMap(this,findViewById(R.id.RotationMapViewPager),findViewById(R.id.w_points_container),mList);
    }
}