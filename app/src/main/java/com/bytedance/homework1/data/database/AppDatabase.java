package com.bytedance.homework1.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.bytedance.homework1.data.database.dao.UserDao;
import com.bytedance.homework1.data.database.entity.User;

// TODO: 数据库版本变动应该添加迁移策略
@Database(entities = {User.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
