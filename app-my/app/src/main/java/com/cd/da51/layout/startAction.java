package com.cd.da51.layout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.da.myapplication.MainActivity;
import com.example.da.myapplication.R;

import static java.lang.Thread.sleep;

/**
 * Created by da on 18-2-11.
 */

public class startAction extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(()->{
            Intent intent=null;
            SharedPreferences sharedPreferences=startAction.this.getSharedPreferences(index.tables,startAction.MODE_PRIVATE);
  try {

      if (sharedPreferences == null) {
          intent = new Intent(startAction.this, index.class);
      } else {
          String token = sharedPreferences.getString("token","");
          if (token == null || token.isEmpty())
              intent = new Intent(startAction.this, index.class);
          else {
              String l_id = sharedPreferences.getString("l_id","");
              intent = new Intent(startAction.this, MainActivity.class);
              intent.putExtra("token",token);
              intent.putExtra("l_id",l_id);
          }

      }
  }catch (NullPointerException e){
      intent = new Intent(startAction.this, index.class);
  }
            startAction.this.startActivity(intent);
            finish();
        },3000);



    }
}
