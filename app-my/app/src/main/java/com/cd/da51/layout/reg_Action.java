package com.cd.da51.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.com.cd.da51.model.User;
import com.example.da.myapplication.MainActivity;
import com.example.da.myapplication.R;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.callback.ConfigItems;
import com.mylhyl.circledialog.params.ItemsParams;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;

import java.io.File;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Created by da on 18-2-12.
 */

public class reg_Action extends AppCompatActivity {
    Button button;
    private EditText re_id,re_p,re_name,re_pass,re_pass2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_layout);
        re_id=findViewById(R.id.reg_id);
        re_name=findViewById(R.id.reg_name);
        re_p=findViewById(R.id.reg_p);
        re_pass=findViewById(R.id.reg_pass);
        re_pass2=findViewById(R.id.reg_pass2);
        button=findViewById(R.id.reg_c);

        button.setOnClickListener((v)->{
            boolean b=true;
            if(re_id.getText().toString().isEmpty()) {
                Toast.makeText(reg_Action.this,"用户号不能为空",Toast.LENGTH_SHORT).show();
                return;
            }
            if(re_name.getText().toString().isEmpty()){
                Toast.makeText(reg_Action.this,"名字不能为空",Toast.LENGTH_SHORT).show();
                return;
            }
            if(re_p.getText().toString().isEmpty()||re_p.getText().toString().trim().length()!=11){
                Toast.makeText(reg_Action.this,"手机号normal",Toast.LENGTH_SHORT).show();
                return;
            }
            if(re_pass.getText().toString().isEmpty()||re_pass.getText().toString().trim().length()<6){
                Toast.makeText(reg_Action.this,"密码不能为空或不小6位",Toast.LENGTH_SHORT).show();
                return;
            }

            if(re_pass2.getText().toString().isEmpty()||!re_pass2.getText().toString().equals(re_pass.getText().toString())){
                Toast.makeText(reg_Action.this,"密码2不normal",Toast.LENGTH_SHORT).show();
                return;
            }

            User user=new User();
            user.setName(re_name.getText().toString());
            user.setL_id(re_id.getText().toString());
            user.setPsw(re_pass.getText().toString());
            user.setPhone(re_p.getText().toString());
            CircleDialog.Builder bs=new CircleDialog.Builder(reg_Action.this).setText("请等待...");
            bs.show();
            new Thread(()->{
                final String[] ms = {null};
                OkHttpClient okHttpClient=MainActivity.getOkHttpClient(reg_Action.this);

                OkHttpUtils.initClient(okHttpClient);

                String text =JSON.toJSONString(user);


                OkHttpUtils.get().url("http://10.0.2.2:8080/reg_APP").addParams("Token",text).build()
                        .execute(new StringCallback(){

                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.d("aa",response);
                               com.alibaba.fastjson.JSONObject jsonObject =JSON.parseObject(response);
                               int   statu_cond = jsonObject.getIntValue("statu_cond");
                               String statu=jsonObject.getString("statu");
                                Toast.makeText(reg_Action.this,statu,Toast.LENGTH_LONG).show();
                                if(statu_cond!=000) {
                                    bs.setCancelable(false);
                                    return;
                                }
                                reg_Action.this.startActivity(new Intent(reg_Action.this,login.class));
                                 finish();
                            }
                        });




            }).start();

        });




    }
}
