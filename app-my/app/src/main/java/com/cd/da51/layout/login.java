package com.cd.da51.layout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.com.cd.da51.model.User;
import com.example.da.myapplication.MainActivity;
import com.example.da.myapplication.R;
import com.mylhyl.circledialog.CircleDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Created by da on 18-2-12.
 */

public class login extends AppCompatActivity {

    private EditText login_id,psw;
    private Button ok;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
       ok=findViewById(R.id.login_c);
       login_id=findViewById(R.id.login_id);
       psw=findViewById(R.id.login_pass);
       ok.setOnClickListener((v)->{
           if(login_id.getText().toString().trim().equals("")){
               Toast.makeText(login.this,"账号不能为空!",Toast.LENGTH_SHORT).show();
               return;
           }

           if (psw.getText().toString().trim().equals("")||
                   psw.getText().toString().trim().length()<6){
               Toast.makeText(login.this,"账号不能为空或小于6!",Toast.LENGTH_SHORT).show();
               return;
           }
           User user=new User();
           user.setL_id(login_id.getText().toString());
           user.setPsw(psw.getText().toString());
           CircleDialog.Builder bs=new CircleDialog.Builder(login.this).setText("请等待...");
           bs.show();

           new Thread(()->{
               final String[] ms = {null};
               OkHttpClient okHttpClient= MainActivity.getOkHttpClient(login.this);
               OkHttpUtils.initClient(okHttpClient);
               String text = JSON.toJSONString(user);
               OkHttpUtils.get().url("http://10.0.2.2:8080/login_APP").addParams("Token",text).build()
                       .execute(new StringCallback(){

                           @Override
                           public void onError(Call call, Exception e, int id) {

                           }

                           @Override
                           public void onResponse(String response, int id) {
                               Log.d("aa",response);
                          JSONObject jsonObject =JSON.parseObject(response);
                          String  token   =jsonObject.getString("token");
                          JSONObject array =  jsonObject.getJSONObject("ps");
                             String statu =array.getString("statu");
                          Toast.makeText(login.this,statu,Toast.LENGTH_LONG).show();
                          if(token==null||token.equals(""))
                            return;
                          SharedPreferences  sharedPreferences = login.this.getSharedPreferences(index.tables,login.MODE_PRIVATE);
                          sharedPreferences.edit().putString("token",token).putString("l_id",user.getL_id()).commit();
                          login.this.startActivity(new Intent(login.this,MainActivity.class));
                          finish();
                           }
                       });




           }).start();
       });



    }
}
