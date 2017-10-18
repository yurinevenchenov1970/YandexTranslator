package com.github.yurinevenchenov1970.yandextranslator.model;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.github.yurinevenchenov1970.yandextranslator.TranslationService;
import com.github.yurinevenchenov1970.yandextranslator.TranslatorApp;
import com.github.yurinevenchenov1970.yandextranslator.bean.TranslationBean;
import com.github.yurinevenchenov1970.yandextranslator.presenter.MainPresenter;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Yuri Nevenchenov on 10/12/2017.
 */

public class MainModel {

    private static final String KEY = "trnsl.1.1.20171009T090004Z.de131bc29593f731.2c1fb6516b3b53d4265182fc09e0f96e97c2a612";
    private static final String LANG_DEFAULT = "en-ru";
    private static final String LANG_RU_EN = "ru-en";
    private static final String LANG = "lang";
    private static final String FORMAT = "plain";
    private static final String OPTIONS = "1";

    private final MainPresenter mMainPresenter;

    @Inject
    TranslationService mService;

    @Inject
    SharedPreferences mSharedPreferences;

    @Inject
    ConnectivityManager mConnectivityManager;

    public MainModel(MainPresenter mainPresenter) {
        TranslatorApp.getNetComponent().inject(this);
        mMainPresenter = mainPresenter;
    }

    public void setLang(boolean isEnRu) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(LANG, isEnRu);
        editor.apply();
        mMainPresenter.showDefaultLanguageChecked(isEnRu);
    }

    public void processTranslation(String sourceText) {
        if (hasConnection()) {
            mMainPresenter.showProgress();
            String lang = isDefaultLanguageChecked() ? LANG_DEFAULT : LANG_RU_EN;
            Call<TranslationBean> beanCall = mService.getTranslation(KEY, sourceText, lang, FORMAT, OPTIONS);
            beanCall.enqueue(new Callback<TranslationBean>() {
                @Override
                public void onResponse(@NonNull Call<TranslationBean> call, @NonNull Response<TranslationBean> response) {
                    if (response.isSuccessful()) {
                        TranslationBean bean = response.body();
                        if (bean != null) {
                            List<String> translations = bean.getTranslationList();
                            if (!translations.isEmpty()) {
                                String translation = translations.get(0);
                                mMainPresenter.hideProgress();
                                mMainPresenter.showTranslation(translation);
                            }
                        } else {
                            mMainPresenter.showError();
                        }
                    }
                }

                @Override
                public void onFailure(Call<TranslationBean> call, Throwable t) {
                    mMainPresenter.hideProgress();
                    mMainPresenter.showError();
                }
            });
        } else {
            mMainPresenter.showConnectionError();
        }
    }

    public boolean isDefaultLanguageChecked() {
        boolean isDefaultChecked =  mSharedPreferences.getBoolean(LANG, true);
        mMainPresenter.changeLanguage(isDefaultChecked);
        return isDefaultChecked;
    }

    private boolean hasConnection() {
        boolean connected = false;
        if (mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        return connected;
    }
}