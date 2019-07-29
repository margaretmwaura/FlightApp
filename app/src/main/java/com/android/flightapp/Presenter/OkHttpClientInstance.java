package com.android.flightapp.Presenter;

import android.content.Context;
import android.util.Log;

import com.android.flightapp.Model.MyServiceHolder;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import timber.log.Timber;

public class OkHttpClientInstance
{
    public static class Builder {
        private HashMap<String, String> headers = new HashMap<>();
        private Context context;
        private MyServiceHolder myServiceHolder;

        public Builder(Context context,MyServiceHolder myServiceHolder)
        {
            this.context = context;
            this.myServiceHolder = myServiceHolder;
            Timber.d("The Okhttpclient has been started");
        }

        public Builder addHeader(String key, String value)
        {
            headers.put(key, value);
            return this;
        }

        public OkHttpClient build()
        {
            TokenAuthenticator authenticator = new TokenAuthenticator(context,myServiceHolder);

            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS);


            okHttpClientBuilder.authenticator(authenticator);

            return okHttpClientBuilder.build();
        }
    }
}
