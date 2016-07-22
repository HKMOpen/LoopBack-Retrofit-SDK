package com.galleria.loopbackdataclip.tools.exception;

/**
 * Created by jjyu on 5/6/15.
 */
public class APIIncorrectException extends Exception {

    public APIIncorrectException() {
    }

    public APIIncorrectException(String detailMessage) {
        super(detailMessage);
    }

    public APIIncorrectException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public APIIncorrectException(Throwable throwable) {
        super(throwable);
    }

}
