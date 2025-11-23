package com.bytedance.homework1.ui.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.homework1.R;

public class SettingViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivIcon;
    public TextView tvTitle;
    public ImageView ivArrow;

    public SettingViewHolder(@NonNull View itemView) {
        super(itemView);
        ivIcon = itemView.findViewById(R.id.iv_icon);
        tvTitle = itemView.findViewById(R.id.tv_title);
        ivArrow = itemView.findViewById(R.id.iv_arrow);
    }

    public void bindData(SettingBean bean) {
        ivIcon.setImageResource(bean.getIconResId());
        tvTitle.setText(bean.getTitle());
        ivArrow.setVisibility(bean.getShowArrow() ? View.VISIBLE : View.GONE);
    }
}
