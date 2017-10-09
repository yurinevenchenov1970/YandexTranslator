package com.github.yurinevenchenov1970.yandextranslator;

import com.github.yurinevenchenov1970.yandextranslator.bean.TranslationBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author Yuri Nevenchenov on 10/9/2017.
 */

public interface TranslationService {

    @FormUrlEncoded
    @POST("@api/v1.5/tr.json/translate")
    Call<TranslationBean> getTranslation(@Field("key") String key,
                                         @Field("text") String text,
                                         @Field("lang") String lang,
                                         @Field("format") String format,
                                         @Field("options") String options);
}