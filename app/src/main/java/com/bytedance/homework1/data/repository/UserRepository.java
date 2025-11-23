package com.bytedance.homework1.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bytedance.homework1.data.database.AppDatabase;
import com.bytedance.homework1.data.database.dao.UserDao;
import com.bytedance.homework1.data.database.entity.User;
import com.bytedance.homework1.data.result.Result;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {

    private final UserDao userDao;
    private final ExecutorService executor;
    private static final String TAG = "UserRepository"; // 添加此行

    public UserRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        this.userDao = database.userDao();
        this.executor = Executors.newSingleThreadExecutor();
    }

    /**
     * 初始化默认用户。
     */
    public void initDefaultUser() {
        executor.execute(() -> {
            try {
                if (userDao.findUserByEmail("ytl@bytedance.com") == null) {
                    User defaultUser = new User();
                    defaultUser.email = "ytl@bytedance.com";
                    defaultUser.password = "123456"; // 在实际应用中，密码应该被加密
                    userDao.insertUser(defaultUser);
                    Log.d(TAG, "默认用户 'ytl@bytedance.com' 创建成功。");
                } else {
                    Log.d(TAG, "默认用户 'ytl@bytedance.com' 已存在，无需创建。");
                }
            } catch (Exception e) {
                Log.e(TAG, "初始化默认用户失败", e);
            }
        });
    }

    public LiveData<Result<Boolean>> login(String email, String password) {
        MutableLiveData<Result<Boolean>> resultLiveData = new MutableLiveData<>();

        executor.execute(() -> {
            try {
                User user = userDao.findUserByEmailAndPassword(email, password);
                // 使用 postValue 在后台线程更新 LiveData
                resultLiveData.postValue(new Result.Success<>(user != null));
            } catch (Exception e) {
                resultLiveData.postValue(new Result.Error<>(e));
            }
        });

        return resultLiveData;
    }

    public LiveData<Result<Boolean>> registerUser(String email, String password) {
        MutableLiveData<Result<Boolean>> resultLiveData = new MutableLiveData<>();

        executor.execute(() -> {
            try {
                if (userDao.findUserByEmail(email) != null) {
                    // 用户已存在
                    resultLiveData.postValue(new Result.Success<>(false));
                    return;
                }
                User newUser = new User();
                newUser.email = email;
                newUser.password = password; // 实际项目中应加密
                userDao.insertUser(newUser);
                // 注册成功
                resultLiveData.postValue(new Result.Success<>(true));
            } catch (Exception e) {
                resultLiveData.postValue(new Result.Error<>(e));
            }
        });

        return resultLiveData;
    }

}
