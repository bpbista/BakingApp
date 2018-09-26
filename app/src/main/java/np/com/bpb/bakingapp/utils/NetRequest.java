package np.com.bpb.bakingapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import np.com.bpb.bakingapp.interfaces.NetResult;
import np.com.bpb.bakingapp.model.Recipe;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static android.content.ContentValues.TAG;

public class NetRequest {
    public static final String urlStr = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
   /*
    public static String getResponse(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/

   /*
    public static void getResponse(String url, final NetResult jsonData){
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                Log.e(TAG, "error in getting response using async okhttp call");
            }
            @Override public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                if (!response.isSuccessful()) {
                    throw new IOException("Error response " + response);
                }
                if (responseBody != null){
                    ResponseBody result = response.body();
                    Log.i(TAG, "Result: "+result.toString());
                //Passing data through interface
//                            jsonData.getJsonResponse(result.toString());
            }
            }
        });
    }
*/

    public static String getResponse(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}


