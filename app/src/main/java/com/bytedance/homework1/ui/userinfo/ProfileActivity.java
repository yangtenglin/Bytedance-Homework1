package com.bytedance.homework1.ui.userinfo;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bytedance.homework1.R;

public class ProfileActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String[] titles = getResources().getStringArray(R.array.setting_titles);
        String[] iconStrings = getResources().getStringArray(R.array.setting_icons);

        TypedArray iconArray = getResources().obtainTypedArray(R.array.setting_icons);
        int[] leftIcons = new int[iconStrings.length];
        for(int i = 0; i < iconStrings.length; i++) {
            leftIcons[i] = iconArray.getResourceId(i, 0);
        }
        iconArray.recycle();
    }
}
