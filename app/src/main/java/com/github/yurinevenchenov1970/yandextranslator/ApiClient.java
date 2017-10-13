package com.github.yurinevenchenov1970.yandextranslator;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Yuri Nevenchenov on 10/9/2017.
 */

public class ApiClient {

    private static final String BASE_URL = "https://translate.yandex.net/";

    private TranslationService mService;

    public TranslationService getTranslationService() {
        if (mService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
            mService = retrofit.create(TranslationService.class);
        }
        return mService;
    }
}