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
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UserRepository {

    private final UserDao userDao;
    private final ExecutorService executor;
    private static final String TAG = "UserRepository";

    private static final int CORE_POOL_SIZE = 2;
    private static final int MAXIMUM_POOL_SIZE = 4;
    private static final long KEEP_ALIVE_TIME = 60L;

    public UserRepository(Application application) {
        AppDatabase database = AppDatabase.getDatabase(application);
        this.userDao = database.userDao();
        this.executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>()
        );
    }

    /**
     * 初始化默认用户。
     */
    public void initDefaultUser() {
        executor.execute(() -> {
            try {
                if (userDao.findUserByEmail("ytl@qq.com") == null) {
                    User defaultUser = new User();
                    defaultUser.email = "ytl@qq.com";
                    defaultUser.password = "123456";
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
            User user = userDao.findUserByEmailAndPassword(email, password);
            if (user != null) {
                // 找到了用户，登录成功
                resultLiveData.postValue(new Result.Success<>(true));
            } else {
                // 未找到用户，登录失败
                resultLiveData.postValue(new Result.Error(new Exception("邮箱或密码错误")));
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
                newUser.password = password;
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
