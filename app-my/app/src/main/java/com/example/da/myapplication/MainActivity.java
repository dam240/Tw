package com.example.da.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.Toolbar;

import com.cd.da51.layout.index;
import com.com.cd.da51.fragment.searchFragment;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.https.HttpsUtils;


import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.File;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;



import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okio.Buffer;


public class MainActivity extends AppCompatActivity {



    private  static String CER = "-----BEGIN CERTIFICATE-----\n" +
            "MIICmjCCAgOgAwIBAgIIbyZr5/jKH6QwDQYJKoZIhvcNAQEFBQAwRzELMAkGA1UEBhMCQ04xKTAn\n" +
            "BgNVBAoTIFNpbm9yYWlsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MQ0wCwYDVQQDEwRTUkNBMB4X\n" +
            "DTA5MDUyNTA2NTYwMFoXDTI5MDUyMDA2NTYwMFowRzELMAkGA1UEBhMCQ04xKTAnBgNVBAoTIFNp\n" +
            "bm9yYWlsIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MQ0wCwYDVQQDEwRTUkNBMIGfMA0GCSqGSIb3\n" +
            "DQEBAQUAA4GNADCBiQKBgQDMpbNeb34p0GvLkZ6t72/OOba4mX2K/eZRWFfnuk8e5jKDH+9BgCb2\n" +
            "9bSotqPqTbxXWPxIOz8EjyUO3bfR5pQ8ovNTOlks2rS5BdMhoi4sUjCKi5ELiqtyww/XgY5iFqv6\n" +
            "D4Pw9QvOUcdRVSbPWo1DwMmH75It6pk/rARIFHEjWwIDAQABo4GOMIGLMB8GA1UdIwQYMBaAFHle\n" +
            "tne34lKDQ+3HUYhMY4UsAENYMAwGA1UdEwQFMAMBAf8wLgYDVR0fBCcwJTAjoCGgH4YdaHR0cDov\n" +
            "LzE5Mi4xNjguOS4xNDkvY3JsMS5jcmwwCwYDVR0PBAQDAgH+MB0GA1UdDgQWBBR5XrZ3t+JSg0Pt\n" +
            "x1GITGOFLABDWDANBgkqhkiG9w0BAQUFAAOBgQDGrAm2U/of1LbOnG2bnnQtgcVaBXiVJF8LKPaV\n" +
            "23XQ96HU8xfgSZMJS6U00WHAI7zp0q208RSUft9wDq9ee///VOhzR6Tebg9QfyPSohkBrhXQenvQ\n" +
            "og555S+C3eJAAVeNCTeMS3N/M5hzBRJAoffn3qoYdAO1Q8bTguOi+2849A==\n" +
            "-----END CERTIFICATE-----";
    CommonTitleBar bar;
    ShineButton button;
    HtmlTextView htmlTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mains);

      RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
   button=findViewById(R.id.po_image2);
   button.setChecked(true);
        htmlTextView=findViewById(R.id.ss);

       bar=findViewById(R.id.titlebar);
     bar.setListener((v,i,s)->{
         switch (i){
             case CommonTitleBar.ACTION_RIGHT_BUTTON:
                 Intent intent=new Intent(MainActivity.this,serchAction.class);
                 MainActivity.this.startActivity(intent);
                 Toast.makeText(MainActivity.this,"RIGHT_BUTTON",Toast.LENGTH_SHORT).show();
                 break;
             case  CommonTitleBar.ACTION_LEFT_TEXT:
                 Toast.makeText(MainActivity.this,"ACTION_LEFT_TEXT",Toast.LENGTH_SHORT).show();
                 break;
         }
     });
        String   token = savedInstanceState.getString("token");
        String l_id= savedInstanceState.getString("l_id");


        String  name;
        new Thread(()->{
            final String[] ms = {null};
            OkHttpClient okHttpClient=getOkHttpClient(MainActivity.this);

            OkHttpUtils.initClient(okHttpClient);

            OkHttpUtils.get().url("http://10.0.2.2:8080/index").build()
                    .execute(new StringCallback(){

                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.d("aa",response);
                            Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                           htmlTextView.setHtml(response,new HtmlHttpImageGetter(htmlTextView));
                        }
                    });




        }).start();



    }





    public static OkHttpClient getOkHttpClient (Activity activity) {

        File file = new File(activity.getCacheDir(), "cheng");
        Cache cache = new Cache(file, 15 * 1024 * 1024);
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(new InputStream[]{new Buffer()
                .writeUtf8(CER)
                .inputStream()},null,null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
        .cache(cache)
       .connectTimeout(5, TimeUnit.SECONDS)
       .readTimeout(5, TimeUnit.SECONDS)
       .writeTimeout(5, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
         .build();

        return  okHttpClient;
    }



   /* Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case 1:
                    String ms=  msg.obj.toString();
                    Toast.makeText(MainActivity.this,ms,Toast.LENGTH_LONG).show();

                    break;
            }

        }
    };

*/



}
