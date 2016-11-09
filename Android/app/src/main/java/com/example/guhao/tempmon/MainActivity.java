package com.example.guhao.tempmon;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int waittime=10000;
    public static int loginstatus = 0;
    public static String loginid;
    List<Temp> Temps =new ArrayList<Temp>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final ChartView cv =(ChartView)findViewById(R.id.chartview);
        final TextView tv = (TextView)findViewById(R.id.textView2);


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginstatus==1){
                    int lastTemp = Integer.parseInt(tv.getText().toString().substring(0,tv.getText().toString().indexOf("℃")));
                    final int tem = (int) (lastTemp-5 + Math.random() * 10);
                    tv.setText(tem + "℃");
                    Temp t1 = new Temp();
                    t1.setNumber(String.valueOf(tem));
                    //获取当前时间
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date tm = new Date(System.currentTimeMillis());
                    String str = sdf.format(tm);
                    t1.setTime(str);
                    t1.setUserID(loginid);
                    Temps.add(t1);
                }else
                    Toast.makeText(getApplicationContext(),"尚未登录无法完成该操作", Toast.LENGTH_SHORT).show();
            }
        });


         final Handler handler = new Handler(){
            public void handleMessage(Message msg) {
                if(msg.what == 0x1234){
                    Toast.makeText(getApplicationContext(),"数据上传成功", Toast.LENGTH_SHORT).show();
                }
                if(msg.what == 0x12345){
                    Toast.makeText(getApplicationContext(),"数据上传失败", Toast.LENGTH_SHORT).show();
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(waittime);
                        while (loginstatus==1&&Temps.size()>0) {
                            try {
                                TempUtil.upload(Temps.get(0));
                                handler.sendEmptyMessage(0x1234);
                                Temps.remove(0);
                            } catch (Exception e) {
                                e.printStackTrace();
                                handler.sendEmptyMessage(0x12345);
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        final EditText et1 =(EditText) findViewById(R.id.editText);
        final EditText et2 =(EditText) findViewById(R.id.editText2);
        Button btn1 =(Button) findViewById(R.id.button);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginstatus==1){
                    if (et1.getText().toString().equals("") && et2.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "未输入时间",
                            Toast.LENGTH_SHORT).show();
                     } else {
                         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                         try {
                            sdf.parse(et1.getText().toString());
                            sdf.parse(et2.getText().toString());

                             new Thread(new Runnable() {
                                 @Override
                                 public void run() {
                                     Looper.prepare();
                                     Temp_query tp = new Temp_query(loginid,et1.getText().toString(),et2.getText().toString());
                                     List<Temp> list = new ArrayList<Temp>();
                                     try {
                                         list=TempUtil.download(tp);
                                         Toast.makeText(getApplicationContext(), "查询到的数据个数：" + list.size(),
                                                         Toast.LENGTH_SHORT).show();
                                         cv.set(list,list.size());
                                     } catch (Exception e) {
                                         e.printStackTrace();
                                         Toast.makeText(getApplicationContext(), "查询失败",
                                                         Toast.LENGTH_SHORT).show();
                                     }
                                     Looper.loop();
                                 }
                             }).start();

                         } catch (ParseException e) {
                            Toast.makeText(getApplicationContext(), "时间格式:yyyy-MM-dd HH:mm:ss", Toast.LENGTH_SHORT).show();
                            }

                    }
                }else
                    Toast.makeText(getApplicationContext(),"尚未登录无法完成该操作", Toast.LENGTH_SHORT).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_manage) {
            final EditText uptime = new EditText(this);
            uptime.setInputType(InputType.TYPE_CLASS_NUMBER);
            uptime.setHint(waittime/1000+"S");
            uptime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uptime.setHint(null);
                }
            });
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("请输入上传间隔(S)").setNegativeButton("取消",null).setView(uptime).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    waittime=Integer.parseInt( uptime.getText().toString() )*1000;
                }
            });
            builder.show();
        }
        else if(id == R.id.nav_btn1){
            if(loginstatus==0) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }else
                Toast.makeText(getApplicationContext(), "已登录",Toast.LENGTH_SHORT).show();
        }else if(id == R.id.nav_btn2){
            if(loginstatus==1) {
                Intent i = new Intent(MainActivity.this, UserData.class);
                startActivity(i);
            }else
                Toast.makeText(getApplicationContext(), "请先登录",Toast.LENGTH_SHORT).show();
        }else if(id == R.id.nav_btn3){
            if(loginstatus==0){
                Toast.makeText(getApplicationContext(), "尚未登录",Toast.LENGTH_SHORT).show();
            }else {
                loginstatus = 0;
                loginid=null;
                Toast.makeText(getApplicationContext(), "已退出登录", Toast.LENGTH_SHORT).show();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
