package com.example.androidapp.services;

import com.example.androidapp.constants.Urls;
import com.example.androidapp.network.CategoriesApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationNetwork {
    private static ApplicationNetwork instance;

    private Retrofit retrofit;

    // при посиланні запиту на сервер, буде писатися що виконано запит (куди, який статус запросу і json)
    public ApplicationNetwork() {
        HttpLoggingInterceptor interceptor =  new  HttpLoggingInterceptor ();
        interceptor.setLevel( HttpLoggingInterceptor.Level.BODY );

        OkHttpClient.Builder client =  new  OkHttpClient.Builder ()
                .addInterceptor(interceptor);

        //бібліотека куди буде посилатися запит
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE)
                //приймаються запити в json
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    //створюється сам об'єкт, перевіряє його, якщо не має то створюється (буде повертатися який створений із за статік)
    public static ApplicationNetwork getInstance() {
        if(instance==null)
            instance = new ApplicationNetwork();
        return instance;
    }

    //інтерфейс який буде посилати запити на сервер
    public CategoriesApi getCategoriesApi() {
        return retrofit.create(CategoriesApi.class);
    }
}