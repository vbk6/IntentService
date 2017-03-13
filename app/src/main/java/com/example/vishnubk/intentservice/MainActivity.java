package com.example.vishnubk.intentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText textFrom;
    EditText textTo;
    TextView textres;
    Button button;
    TextView textView;
    ProgressBar progressBar;
    private MyBroadcastReceiver myBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        editText=(EditText)findViewById(R.id.editamount);
        textFrom=(EditText)findViewById(R.id.from);
        textTo=(EditText)findViewById(R.id.to);
        textres=(TextView)findViewById(R.id.result);
        button=(Button)findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.textView);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ServiceClass.class);
                startService(intent);
            }
        });

        progressBar.setVisibility(ProgressBar.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean stat=isNetworkOnline();
                if(stat==true) {
                    textView.setText("");
                    final String value1=textFrom.getText().toString();
                    final String value2=textTo.getText().toString();
                   // obj1.execute(value1, value2);
                    Intent intent = new Intent(getApplicationContext(), IntentServiceClass.class);
                    intent.putExtra("fromText",value1);
                    intent.putExtra("toText",value2);
                    startService(intent);
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                }
                else if(stat==false){
                    textView.setText("No Network");


                }


            }


        });


        //register BroadcastReceiver
          /*  IntentFilter intentFilter = new IntentFilter();
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(myBroadcastReceiver, intentFilter);*/

    progressBar.setVisibility(View.GONE);
        String result = getIntent().getStringExtra("value");
       // Log.d("res",result);
        textres.setText(result);

    }



    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("result");
            Log.d("res",result);
            textres.setText(result);

        }
    }



    public boolean isNetworkOnline() {
        boolean status=false;
        try{
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
                status= true;
            }else {
                netInfo = cm.getNetworkInfo(1);
                if(netInfo!=null && netInfo.getState()==NetworkInfo.State.CONNECTED)
                    status= true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return status;

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
