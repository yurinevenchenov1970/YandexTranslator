package com.github.yurinevenchenov1970.yandextranslator;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Yuri Nevenchenov on 10/9/2017.
 */

public class ApiClient {

    private static final String BASE_URL = "https://translate.yandex.net/";

    private static Retrofit sRetrofit = null;

    public static TranslationService getTranslationService(){
        TranslationService service = null;
        if (sRetrofit == null){
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
            service = sRetrofit.create(TranslationService.class);
        }
        return service;
    }
}