package com.github.yurinevenchenov1970.yandextranslator.model;

import android.support.annotation.NonNull;

import com.github.yurinevenchenov1970.yandextranslator.ApiClient;
import com.github.yurinevenchenov1970.yandextranslator.bean.TranslationBean;
import com.github.yurinevenchenov1970.yandextranslator.presenter.MainPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Yuri Nevenchenov on 10/12/2017.
 */

public class MainModel {

    private static final String KEY = "trnsl.1.1.20171009T090004Z.de131bc29593f731.2c1fb6516b3b53d4265182fc09e0f96e97c2a612";
    private static final String LANG = "en-ru";
    private static final String FORMAT = "plain";
    private static final String OPTIONS = "1";

    private final MainPresenter mMainPresenter;
    private final ApiClient mApiClient;

    public MainModel(MainPresenter mainPresenter, ApiClient apiClient) {
        mMainPresenter = mainPresenter;
        mApiClient = apiClient;
    }

    public void processTranslation(String sourceText) {
            mMainPresenter.showProgress();
            Call<TranslationBean> beanCall = mApiClient.getTranslationService().getTranslation(KEY, sourceText, LANG, FORMAT, OPTIONS);
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
    }
}