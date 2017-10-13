package com.github.yurinevenchenov1970.yandextranslator.dagger;

import com.github.yurinevenchenov1970.yandextranslator.model.MainModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Yuri Nevenchenov on 10/13/2017.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    void inject(MainModel model);
}