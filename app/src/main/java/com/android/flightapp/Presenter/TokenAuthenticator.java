package com.android.flightapp.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.flightapp.Model.MyServiceHolder;
import com.android.flightapp.Model.RetrofitResponse;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Log.d("Authenticator","Starting authenticator");

            api_service myService = new retrofit2.Retrofit.Builder()
                    .baseUrl("https://api.lufthansa.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(api_service.class);

            retrofit2.Response retrofitResponse = myService.refreshToken("y24hefr24xhbbbda3zy7dt4d", "5Q26e5Ged8", "client_credentials").execute();
            RetrofitResponse myResponse = (RetrofitResponse) retrofitResponse.body();
            String accessToken = myResponse.getAccessToken();

            if (accessToken != null)
            {
                Log.d("TokenAuthenticator", "The retrofit response " + accessToken);
                SharedPreferences settings = context.getSharedPreferences("PREFS", context.MODE_PRIVATE);
                SharedPreferences.Editor edit = settings.edit();
                edit.putString("token", myResponse.getAccessToken());
                edit.apply();

                Log.d("AccessToken", "End of getting the access token");
                return response.request().newBuilder()
                        .header("Authorization","Bearer "+accessToken)
                        .build();
            }

            Log.d("AuthenticationToken","Authentication token had alrady been set");
            SharedPreferences settings = context.getSharedPreferences("PREFS", context.MODE_PRIVATE);

            String token = settings.getString("token", null);
            Log.d("Token","This is the token" + token);
            return response.request().newBuilder()
                    .header("Authorization", "Bearer "+token)
                    .build();





    }
}
