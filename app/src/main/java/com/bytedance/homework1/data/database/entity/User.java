package com.bytedance.homework1.data.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "users")
public class User {
    public int id;

    @NonNull
    @ColumnInfo(name = "username")
    public String username;

    @NonNull
    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "person_name")
    public String personName;
}
