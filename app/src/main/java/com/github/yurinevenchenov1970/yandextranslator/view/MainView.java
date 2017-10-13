package com.github.yurinevenchenov1970.yandextranslator.view;

/**
 * @author Yuri Nevenchenov on 10/12/2017.
 */

public interface MainView {

    void showProgress();

    void hideProgress();

    void showError();

    void showConnectionError();

    void showTranslation(String translatedText);
}