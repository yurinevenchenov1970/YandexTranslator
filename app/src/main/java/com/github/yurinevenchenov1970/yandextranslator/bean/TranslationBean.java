package com.github.yurinevenchenov1970.yandextranslator.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import java.util.List;

/**
 * Translation response
 *
 * @author Yuri Nevenchenov on 10/9/2017.
 */

public class TranslationBean {

    @JsonProperty("code")
    private int mCode;

    @JsonProperty("lang")
    private String mLang;

    @JsonProperty("text")
    private List<String> mTranslationList;

    public TranslationBean() {
        // empty constructor needed by Jackson
    }

    public TranslationBean(int code,
                           String lang,
                           List<String> translationList) {
        mCode = code;
        mLang = lang;
        mTranslationList = translationList;
    }

    public int getCode() {
        return mCode;
    }

    public String getLang() {
        return mLang;
    }

    public List<String> getTranslationList() {
        return mTranslationList;
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TranslationBean that = (TranslationBean) o;
        return mCode == that.mCode &&
                Objects.equal(mLang, that.mLang) &&
                Objects.equal(mTranslationList, that.mTranslationList);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(
                mCode,
                mLang,
                mTranslationList);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mCode", mCode)
                .add("mLang", mLang)
                .add("mTranslationList", mTranslationList)
                .toString();
    }
}