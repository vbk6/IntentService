package com.example.vishnubk.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by vishnubk on 31/8/16.
 */

public class IntentServiceClass extends IntentService {
    double res;
    String value = "";


    public IntentServiceClass() {
        super("IntentClass");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent,flags,startId);
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        String fromText=intent.getStringExtra("fromText");
        String toText=intent.getStringExtra("toText");
        Log.d("bk",fromText);
        Log.d("bk1",toText);
        try {
            value=run("http://api.fixer.io/latest?base="+fromText+"&symbols="+toText);
            Log.d("vbk",value);
            JSONObject obj = null;
            obj = new JSONObject(value);
            res = obj.getJSONObject("rates").getDouble(toText);
               Log.d("result", String.valueOf(res));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent dialogIntent = new Intent(getBaseContext(), MainActivity.class);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        dialogIntent.putExtra("value",String.valueOf(res));
        Log.d("result", String.valueOf(res));
        getApplication().startActivity(dialogIntent);

        // toast();
        //return result
       /*Intent intentResponse = new Intent(intent.ACTION_SEND);

        intentResponse.addCategory(Intent.CATEGORY_DEFAULT);
        intentResponse.putExtra("result",res);
        sendBroadcast(intentResponse);
*/
    }


    String run(String url) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }


    private void toast() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Intent service", Toast.LENGTH_LONG).show();
            }
        });
    }
}