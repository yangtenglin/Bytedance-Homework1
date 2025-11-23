package com.bytedance.homework1.ui.userinfo;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.homework1.data.sp.UserSharedPrefs;
import com.bytedance.homework1.ui.recyclerview.SettingAdapter;
import com.bytedance.homework1.R;
import com.bytedance.homework1.ui.recyclerview.SettingBean;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private UserSharedPrefs userPrefs;

    private static final String DEFAULT_PERSON_NAME = "杨腾林";
    private static final String DEFAULT_SIGNATURE = "欢迎来到信息App";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //初始化添加默认用户名和签名
        userPrefs = new UserSharedPrefs(this);
        if (userPrefs.getPersonName() == null || userPrefs.getPersonName().isEmpty()) {
            userPrefs.setPersonName(DEFAULT_PERSON_NAME);
        }
        if (userPrefs.getSignature() == null || userPrefs.getSignature().isEmpty()) {
            userPrefs.setSignature(DEFAULT_SIGNATURE);
        }

        TextView tvPersonName = findViewById(R.id.person_name);
        TextView tvSignature = findViewById(R.id.signature);

        tvPersonName.setText(userPrefs.getPersonName());
        tvSignature.setText(userPrefs.getSignature());

        RecyclerView recyclerView = findViewById(R.id.recycler_settings);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);

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
