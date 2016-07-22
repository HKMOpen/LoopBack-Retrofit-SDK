package com.galleria.loopbackdataclip.tools.exception;

/**
 * Created by jjyu on 5/6/15.
 */
public class NotFoundException extends Exception {

    public NotFoundException() {
    }

    public NotFoundException(String detailMessage) {
        super(detailMessage);
    }

    public NotFoundException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NotFoundException(Throwable throwable) {
        super(throwable);
    }

}