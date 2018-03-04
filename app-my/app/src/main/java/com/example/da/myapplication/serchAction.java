package com.example.da.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.Adapte.UserAdapte;
import com.com.cd.da51.model.User;
import com.gturedi.views.StatefulLayout;
import com.mylhyl.circledialog.CircleDialog;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by da on 18-2-11.
 */

public class serchAction extends AppCompatActivity {

   private CommonTitleBar bar;
   private ListView listView;

   private StatefulLayout adh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serchfr);
        bar=findViewById(R.id.searchbar);

       adh=findViewById(R.id.stateful);

        List list=new ArrayList<User>();
        User u=new User();
        u.setCard("-----");
        u.setL_id("dameng");
        u.setName("names");
        User u1=new User();
        u1.setCard("224424");
        u1.setL_id("adada");
        u1.setName("dddd");


        User u2=new User();
        u2.setCard("11111");
        u2.setL_id("1111");
        u2.setName("ddffdd");

        User u3=new User();
        u3.setCard("2222");
        u3.setL_id("ddd");
        u3.setName("f222");
        list.add(u);
        list.add(u1);
        list.add(u2);
        list.add(u3);




        listView=findViewById(R.id.listserch);

        bar.setListener((v,i,s)->{
          switch (i){
              case CommonTitleBar.ACTION_LEFT_TEXT:
                  finish();
                  break;
              case CommonTitleBar.ACTION_SEARCH_SUBMIT:
                  Toast.makeText(serchAction.this,s,Toast.LENGTH_SHORT).show();
                  adh.showLoading();
                  new Handler().postDelayed(()->{
                      adh.showContent();
                  },3000);

                  break;

          }
        });
     listView.setAdapter(new UserAdapte(this,list,R.layout.useradapet));
      listView.setOnItemClickListener((a,v,i,l)-> {
             User user = (User) a.getItemAtPosition(i);
              Toast.makeText(serchAction.this,i+""+user.getL_id(),Toast.LENGTH_SHORT).show();
            new CircleDialog.Builder(serchAction.this).setTitle("to").setText("111").setPositive("qd",null)
            .show();
      });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("aaaaaa","onDestroy");

    }



    /*
 final String[] items = {"拍照", "从相册选择", "小视频"};
                new CircleDialog.Builder(this)
                        .configDialog(new ConfigDialog() {
                            @Override
                            public void onConfig(DialogParams params) {
                                //增加弹出动画
                                params.animStyle = R.style.dialogWindowAnim;
                            }
                        })
                        .setTitle("标题")
                        .setTitleColor(Color.BLUE)
                        .setItems(items, new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        })
                        .setNegative("取消", null)
                        .configNegative(new ConfigButton() {
                            @Override
                            public void onConfig(ButtonParams params) {
                                //取消按钮字体颜色
                                params.textColor = Color.RED;
                            }
                        })
                        .show();

 */
}
