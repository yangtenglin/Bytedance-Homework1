package com.bytedance.homework1.ui.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.homework1.R;
import com.bytedance.homework1.ui.userinfo.ProfileActivity;

import java.util.List;

public class SettingAdapter extends RecyclerView.Adapter<SettingViewHolder>{

    private List<SettingBean> mSettingList;

    public SettingAdapter(List<SettingBean> settingList) {
        this.mSettingList = settingList;
    }

    /**
     * 第⼀步：Item创建ViewHolder（加载item布局）
     *
     * @return
     */
    @NonNull
    @Override
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting,
                        parent, false);
        return new SettingViewHolder(itemRootView);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingViewHolder holder, int position)
    {

        SettingBean settingBean = mSettingList.get(position);
        // 调⽤ViewHolder的bindData⽅法绑定数据
        holder.bindData(settingBean);

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(holder.itemView.getContext(), "点击了第" + ( position + 1 ) + "行: "
                    + settingBean.getTitle(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return mSettingList != null ? mSettingList.size() : 0;
    }
}
