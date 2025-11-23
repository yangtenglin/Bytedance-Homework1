package com.bytedance.homework1.ui.recyclerview;

public class SettingBean {

    // 左侧图标
    private int iconResId;

    // 中间标题
    private String title;

    // 右侧箭头显示
    private Boolean isShowArrow;


    public SettingBean(String title, int iconResId, Boolean isShowArrow) {
        this.title = title;
        this.iconResId = iconResId;
        this.isShowArrow = isShowArrow;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getShowArrow() {
        return isShowArrow;
    }

    public void setShowArrow(Boolean showArrow) {
        isShowArrow = showArrow;
    }
}
