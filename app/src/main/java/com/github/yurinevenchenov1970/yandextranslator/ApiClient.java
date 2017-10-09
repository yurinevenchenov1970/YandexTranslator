package com.github.yurinevenchenov1970.yandextranslator;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Yuri Nevenchenov on 10/9/2017.
 */

public class ApiClient {

    public static final String KEY = "trnsl.1.1.20171009T090004Z.de131bc29593f731.2c1fb6516b3b53d4265182fc09e0f96e97c2a612";
    private static final String BASE_URL = "https://translate.yandex.net/";

    private TranslationService mService;

    public TranslationService getTranslationService(){
        if (mService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
            mService = retrofit.create(TranslationService.class);
        }
        return mService;
    }
}