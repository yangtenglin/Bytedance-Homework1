package com.bytedance.homework1;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bytedance.homework1.data.repository.UserRepository;
import com.bytedance.homework1.data.result.Result;
import com.bytedance.homework1.ui.userinfo.ProfileActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化添加默认账号
        UserRepository userRepository = new UserRepository(getApplication());
        userRepository.initDefaultUser();


        //
        EditText emailEditText = findViewById(R.id.email_edittext);
        EditText passwordEditText = findViewById(R.id.password_edittext);
        ImageButton togglePasswordVisibilityButton = findViewById(R.id.toggle_password_visibility_btn);

        // 密码眼睛按钮功能实现
        togglePasswordVisibilityButton.setOnClickListener(v -> {
            // 检查当前密码是否可见
            if (passwordEditText.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                // 如果密码是隐藏的，则显示它
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                togglePasswordVisibilityButton.setImageResource(R.drawable.ic_visibility_on); // 切换到“睁眼”图标
            } else {
                // 如果密码是可见的，则隐藏它
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                togglePasswordVisibilityButton.setImageResource(R.drawable.ic_visibility_off); // 切换到“闭眼”图标
            }
            passwordEditText.setSelection(passwordEditText.getText().length());
        });

        findViewById(R.id.login_btn).setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(MainActivity.this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
                return;
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(MainActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }

            userRepository.login(email, password).observe(this, result -> {
                if (result instanceof Result.Success ) {
                    // 登录成功
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);
                } else {
                    // 登录失败
                    Toast.makeText(MainActivity.this, "邮箱或密码错误", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

}