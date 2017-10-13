package com.github.yurinevenchenov1970.yandextranslator;

import android.app.Application;

import com.github.yurinevenchenov1970.yandextranslator.dagger.AppModule;
import com.github.yurinevenchenov1970.yandextranslator.dagger.DaggerNetComponent;
import com.github.yurinevenchenov1970.yandextranslator.dagger.NetComponent;
import com.github.yurinevenchenov1970.yandextranslator.dagger.NetModule;

/**
 * @author Yuri Nevenchenov on 10/13/2017.
 */

public class TranslatorApp extends Application {

    private static final String BASE_URL = "https://translate.yandex.net/";
    private static NetComponent sNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BASE_URL))
                .build();
    }

    public static NetComponent getNetComponent() {
        return sNetComponent;
    }
}