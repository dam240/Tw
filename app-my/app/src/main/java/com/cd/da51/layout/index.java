package com.cd.da51.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.da.myapplication.R;

/**
 * Created by da on 18-2-12.
 */

public class index extends AppCompatActivity {


    private Button login,reg;
    public static   String tables="token";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.index_layout);
          login=findViewById(R.id.loginb);
          reg=findViewById(R.id.regb);

          reg.setOnClickListener((view)->{
              index.this.startActivity(new Intent(index.this, com.cd.da51.layout.reg_Action.class));


          });
          login.setOnClickListener((view)->{

              index.this.startActivity(new Intent(index.this, com.cd.da51.layout.login.class));

           });

    }



}
