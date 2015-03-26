package com.example.event.jafo.event;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.format.Time;
import android.view.Menu;
import android.view.*;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {
    TextView text=null;
    EditText edt=null;
    Button btn=null;
    public int i=0;
    String str="NULL";
    //thread
    class CalThread extends Thread
    {
        public  Handler threadHandle;
        public void run(){
            Looper.prepare();
            threadHandle = new Handler(){
                @Override
            public void handleMessage(Message msg){
                    if (0x1234==msg.what) {
                        i++;
                        str+="handle";
                    }
                }
            };
         Looper.loop();
        }
    }
Context cts=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        text=(TextView)findViewById(R.id.TextV);
        btn=(Button)findViewById(R.id.btn1);
        edt=(EditText)findViewById(R.id.etx);
        final CalThread calTh = new CalThread();
        calTh.start();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DownTask taskAsync = new DownTask(cts);
                taskAsync.handler = new Handler()
                {
                    @Override
                    public void handleMessage(Message msg) {
                        if(0x1234==msg.what)
                        {
                            text.setText(taskAsync.strShow);
                        }
                    }
                };
                taskAsync.execute("https://github.com");
                text.setText(taskAsync.strShow);
//                str="";
//                str+=edt.getText();
//                calTh.threadHandle.sendEmptyMessage(0x1234);
//                text.setText(str+i);
            }
        });

        final Handler hadler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what==0x1234)
                {
                   // text.setText(""+i);
                }
            }
        };

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                hadler.sendEmptyMessage(0x1234);
                i++;
                System.out.println(">>>>"+i);
                return;
            }
        },0,500);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class DownTask extends AsyncTask<String,Integer,String>
    {
        Context context=null;
        public DownTask(Context ctx) {
            context = ctx;
        }

        Handler handler=null;
        public String strShow;
        @Override
        protected String doInBackground(String... urls) {
            return  "this is string !";
        }

        @Override
        protected void onPostExecute(String s) {
            s+=">>>onPosExecute";
            strShow=s;
            if (null!=handler){
                handler.sendEmptyMessage(0x1234);
            }
            ProgressDialog dlg = new ProgressDialog(context);
            dlg.setTitle("try to show dlg ");
            dlg.setCancelable(true);
            dlg.show();
            //super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            strShow+="onPreExecute";
            super.onPreExecute();
        }
    }
}
