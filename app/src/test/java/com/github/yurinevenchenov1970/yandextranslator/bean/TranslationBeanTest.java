package com.github.yurinevenchenov1970.yandextranslator.bean;

import org.junit.Test;

import java.util.Collections;

/**
 * Test for @link TranslationBean
 *
 * @author Yuri Nevenchenov on 10/9/2017.
 */
public class TranslationBeanTest extends BaseJsonParserTest{

public static final String TEST_FILE = "translation.json";

    @Test
    public void testParseObject(){
        testParse(TEST_FILE, getTranslationBean(), TranslationBean.class);
    }

    private TranslationBean getTranslationBean(){
        return new TranslationBean(200, "en-ru", Collections.singletonList("Здравствуй, Мир!"));
    }
}