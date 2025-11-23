package com.bytedance.homework1.ui.userinfo;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.homework1.ui.recyclerview.SettingAdapter;
import com.bytedance.homework1.R;
import com.bytedance.homework1.ui.recyclerview.SettingBean;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        RecyclerView recyclerView = findViewById(R.id.recycler_settings);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        String[] titles = getResources().getStringArray(R.array.setting_titles);
        String[] iconStrings = getResources().getStringArray(R.array.setting_icons);

        TypedArray iconArray = getResources().obtainTypedArray(R.array.setting_icons);
        List<SettingBean> items = new ArrayList<>();
        int length = Math.min(titles.length, iconArray.length());
        for (int i = 0; i < length; i++) {
            int iconResId = iconArray.getResourceId(i, 0);
            items.add(new SettingBean(titles[i], iconResId, true));
        }
        iconArray.recycle();

        SettingAdapter adapter = new SettingAdapter(items);
        recyclerView.setAdapter(adapter);
    }
}
