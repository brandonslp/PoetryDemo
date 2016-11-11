package com.lp.brandon.poetrydemo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.lp.brandon.poetrydemo.Controllers.DbController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by brand on 10/11/2016.
 */
public class RequestMovie {
    private final OkHttpClient client = new OkHttpClient();
    private Context context;
    private final String API_KEY;
    private DbController dbController;
    public RequestMovie(Context context) {
        this.context = context;
        API_KEY = BuildConfig.API_KEY;
        dbController = new DbController(context);
    }

    private void run(Request request){
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("Brandon-lp","Fallo la solicitud de request ->");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Codigo inesperado"+response);
                try {
                JSONObject jsonObject = new JSONObject(response.body().string());
                Log.v("Brandon-lp","Se descargo  ->" + jsonObject);
                    if (!jsonObject.getString("Response").equalsIgnoreCase("False"))
                    dbController.addMovie(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void getMovieFromServer(String title){
        String url = String.format("http://www.omdbapi.com/?apikey=%s&t=%s",API_KEY,title);
        Log.v("Brandon-lp","La url quedo -> "+url);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        run(request);
    }

}
