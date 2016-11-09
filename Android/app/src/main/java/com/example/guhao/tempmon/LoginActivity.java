package com.example.guhao.tempmon;

import android.content.DialogInterface;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import static com.example.guhao.tempmon.MainActivity.loginid;
import static com.example.guhao.tempmon.MainActivity.loginstatus;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    boolean error = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

//        mEmailView.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                trytime=0;
//            }
//        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
                if(!error) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Looper.prepare();
                            login();
                            Looper.loop();

                        }
                    }).start();
                }
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;
        error = false;


        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
            error = true;
        }


        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
            error = true;
        }
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
            error = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
    }


    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private void login(){

        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();

        final Person p=new Person();
        p.setName(email);
        p.setPassword(password);
        p.setGender("0");

        List<Person> list= new ArrayList<Person>();
        list=RegLog.check(p);
        if(list.size()>0){
              if(password.equals(list.get(0).getPassword())){
                   Toast.makeText(getApplicationContext(), "登录成功",Toast.LENGTH_SHORT).show();
                  loginstatus=1;
                  loginid=list.get(0).getName();
                  finish();
              }else{
                  AlertDialog.Builder builder = new AlertDialog.Builder(this);
                  builder.setMessage("密码错误\n如是新用户注册请更换用户名").setNegativeButton("确定",null);
                  builder.show();
              }
        }else  {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("该用户尚未注册请确认用户名和密码是否错误\n如是新用户注册请点击确定").setNegativeButton("取消",null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    p.setGender("1");
                    RegLog.check(p);
                    Toast.makeText(getApplicationContext(), "注册成功",Toast.LENGTH_SHORT).show();
                    loginstatus=1;
                    loginid=p.getName();
                }
            });
            builder.show();
        }
    }
}
