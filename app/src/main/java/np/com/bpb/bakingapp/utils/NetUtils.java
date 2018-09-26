package np.com.bpb.bakingapp.utils;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;
import java.util.List;
import np.com.bpb.bakingapp.interfaces.API;
import np.com.bpb.bakingapp.interfaces.NetResult;
import np.com.bpb.bakingapp.model.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtils {
    public static void getRecipes(final Context context, final NetResult data){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);
        Call<List<Recipe>> call = api.listRecipes();

        call.enqueue(new Callback<List<Recipe>>() {
            public static final String TAG = "Retrofit" ;

            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response){
                List<Recipe> recipesResponse;
                recipesResponse = response.body();
//                assert recipesResponse != null;
                data.getRecipes(recipesResponse);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG,t.getMessage());
            }
        });
    }

    public boolean isConnected(final Context context) {
        boolean status;
        final ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if((conn.getActiveNetworkInfo() != null && conn.getActiveNetworkInfo().isAvailable() && conn.getActiveNetworkInfo().isConnected())
                &&(conn.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conn.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)){
            status = true;
        }else{
            status = false;
        }
        return status;
    }
}
