package com.android.flightapp.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.flightapp.BuildConfig;
import com.android.flightapp.Model.MyServiceHolder;
import com.android.flightapp.Model.RetrofitResponse;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class TokenAuthenticator implements Authenticator
{

    private Context context;
    private MyServiceHolder myServiceHolder;
    public static int flag = 1;

    public TokenAuthenticator(Context context,MyServiceHolder myServiceHolder) {
        this.context = context;
        this.myServiceHolder = myServiceHolder;
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException
    {
        if (myServiceHolder == null) {
            return null;
        }

        Timber.d("Starting authenticator");

            api_service myService = new retrofit2.Retrofit.Builder()
                    .baseUrl("https://api.lufthansa.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(api_service.class);

            retrofit2.Response retrofitResponse = myService.refreshToken(BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, "client_credentials").execute();
            RetrofitResponse myResponse = (RetrofitResponse) retrofitResponse.body();
            String accessToken = myResponse.getAccessToken();

            if (accessToken != null)
            {
                Timber.d( "The retrofit response " + accessToken);
                SharedPreferences settings = context.getSharedPreferences("PREFS", context.MODE_PRIVATE);
                SharedPreferences.Editor edit = settings.edit();
                edit.putString("token", myResponse.getAccessToken());
                edit.apply();


                return response.request().newBuilder()
                        .header("Authorization","Bearer "+accessToken)
                        .build();
            }

            Timber.d("Authentication token had alrady been set");
            SharedPreferences settings = context.getSharedPreferences("PREFS", context.MODE_PRIVATE);

            String token = settings.getString("token", null);

            return response.request().newBuilder()
                    .header("Authorization", "Bearer "+token)
                    .build();





    }
}
