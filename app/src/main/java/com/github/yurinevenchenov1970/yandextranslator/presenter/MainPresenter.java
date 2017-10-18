package com.github.yurinevenchenov1970.yandextranslator.presenter;

/**
 * @author Yuri Nevenchenov on 10/12/2017.
 */

public interface MainPresenter {

    void showProgress();

    void hideProgress();

    void showError();

    void showConnectionError();

    void showTranslation(String translatedText);

    void showDefaultLanguageChecked(boolean show);

    void getLanguage();

    void processTranslation(String sourceText);

    void changeLanguage(boolean isEnRu);
}