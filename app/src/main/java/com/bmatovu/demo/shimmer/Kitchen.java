package com.bmatovu.demo.shimmer;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Kitchen extends Application {

   private static final String TAG = Kitchen.class.getSimpleName();
   private static final String API_BASE_URL = "http://api.bmatovu.com/";

   private Retrofit retrofit = null;

   @Override
   public void onCreate() {
      super.onCreate();
   }

   public Retrofit getRetrofit() {

      if (retrofit == null) {

         OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
         httpClient.connectTimeout(30, TimeUnit.SECONDS);
         httpClient.readTimeout(30, TimeUnit.SECONDS);
         httpClient.writeTimeout(30, TimeUnit.SECONDS);

         if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            httpClient.addInterceptor(logging);
         }

         retrofit = new Retrofit.Builder()
               .baseUrl(API_BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .client(httpClient.build())
               .build();

      }

      return retrofit;
   }

}
