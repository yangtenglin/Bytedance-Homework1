package com.bytedance.homework1.data.sp;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSharedPrefs {

    private static final String PREF_NAME = "user_prefs";
    private static final String KEY_PERSON_NAME = "key_person_name";
    private static final String KEY_SIGNATURE = "key_signature";

    private final SharedPreferences prefs;

    public UserSharedPrefs(Context context) {
        this.prefs = context.getApplicationContext()
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveUserInfo(String personName, String signature) {
        prefs.edit()
                .putString(KEY_PERSON_NAME, personName)
                .putString(KEY_SIGNATURE, signature)
                .apply();
    }

    public String getPersonName() {
        return prefs.getString(KEY_PERSON_NAME, "");
    }

    public String getSignature() {
        return prefs.getString(KEY_SIGNATURE, "");
    }

    public void setPersonName(String personName) {
        prefs.edit()
                .putString(KEY_PERSON_NAME, personName)
                .apply();
    }

    public void setSignature(String signature) {
        prefs.edit()
                .putString(KEY_SIGNATURE, signature)
                .apply();
    }

}
