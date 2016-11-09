package com.example.guhao.tempmon;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import static com.example.guhao.tempmon.MainActivity.loginid;


public class UserData extends AppCompatActivity {

    List<Person> list= new ArrayList<Person>();
    Person p = new Person();
    String str=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView tv = (TextView)findViewById(R.id.textView14);
        final EditText et1 = (EditText)findViewById(R.id.editText6);
        final EditText et2 = (EditText)findViewById(R.id.editText7);

        p.setName(loginid);
        p.setGender("0");

        list=RegLog.check(p);
        tv.setText(list.get(0).getName());
        et1.setHint(list.get(0).getGender());
        et2.setHint(list.get(0).getEmail());



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et1.getText().toString().equals("")&&et2.getText().toString().equals("")){
                    str="未输入数据";
                }else if(et1.getText().toString().equals("男")||et1.getText().toString().equals("女")){
                    p.setGender(et1.getText().toString());
                    p.setEmail(et2.getText().toString());

                    RegLog.check(p);

                    et1.setText("");
                    et1.setHint(p.getGender());
                    et2.setText("");
                    et2.setHint(p.getEmail());
                    str = "更新数据成功";
                }
                else{
                    str="性别填写错误";
                }
                Snackbar.make(view,str, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
