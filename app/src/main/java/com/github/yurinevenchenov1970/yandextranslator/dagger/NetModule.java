package com.github.yurinevenchenov1970.yandextranslator.dagger;

import android.content.Context;
import android.net.ConnectivityManager;

import com.github.yurinevenchenov1970.yandextranslator.TranslationService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Yuri Nevenchenov on 10/13/2017.
 */
@Module
public class NetModule {

    private final String mBaseUrl;

    public NetModule(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Converter.Factory provideJacksonConverterFactory() {
        return JacksonConverterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory factory) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(factory)
                .build();
    }

    @Provides
    @Singleton
    TranslationService provideTranslationService(Retrofit retrofit) {
        return retrofit.create(TranslationService.class);
    }

    @Provides
    ConnectivityManager provideConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
}