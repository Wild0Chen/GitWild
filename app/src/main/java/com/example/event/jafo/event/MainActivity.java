package com.example.event.jafo.event;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.*;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    Button btnTest;
    TextView ts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //去掉窗口标题
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final PlaneView plv=new PlaneView(this);
        setContentView(plv);
        //获取窗口管理器
        WindowManager windowManager=getWindowManager();
        Display display=windowManager.getDefaultDisplay();
        DisplayMetrics me=new DisplayMetrics();
        //获得屏幕宽和高
        display.getMetrics(me);
        //飞机初始位置
        plv.currentX=me.widthPixels/2;
        plv.currentY=me.heightPixels/2+10;
//        plv.setOnDragListener(new View.OnDragListener()
//        {
//            @Override
//            public boolean onDrag(View v,DragEvent event)
//            {
//                plv.currentX=event.getX();
//                plv.currentY=event.getY();
//                plv.invalidate();
//                return  true;
//            }
//        });
//        ts=(TextView)findViewById(R.id.TextV);
//        btnTest=(Button)findViewById(R.id.btnTest);
//        btnTest.setOnClickListener(new MyListener());
//        ts.setOnLongClickListener(new View.OnLongClickListener()
//        {
//            @Override
//            public boolean onLongClick(View v)
//            {
//                ts.setText("longClick");
//                return true;
//            }
//        });
//        {
//            @Override
//            public void onClick(View v) {
//                EditText edtText=(EditText)findViewById(R.id.EdtText);
//                ts.setText(edtText.getText());
//            }
//        });
    }
    class MyListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            EditText edtText=(EditText)findViewById(R.id.EdtText);
            ts.setText(edtText.getText());
        }
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
}
