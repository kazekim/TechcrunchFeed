package com.kazekim.techcrunch.callback;

/**
 * Created by Jirawat Kim Harnsiriwatanakit on 4/8/2017 AD.
 * Contact : jirawat.h@kazekim.com
 */

public enum HTTPStatus {
    ok("ok"),
    fail("fail");

    private final String text;

    HTTPStatus(String text)
    {
        this.text = text;
    }

    public String toString()
    {
        return text;
    }

    public static HTTPStatus fromString(String text) {
        if (text != null) {
            for (HTTPStatus b : HTTPStatus.values()) {
                if (text.equalsIgnoreCase(b.text)) {
                    return b;
                }
            }
        }
        return null;
    }
}
