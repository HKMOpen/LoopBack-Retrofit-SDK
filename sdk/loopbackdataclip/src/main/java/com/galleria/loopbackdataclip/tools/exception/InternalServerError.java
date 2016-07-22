package com.galleria.loopbackdataclip.tools.exception;

/**
 * Created by jjyu on 30/6/15.
 */
public class InternalServerError extends Exception {


    public InternalServerError() {
    }

    public InternalServerError(String detailMessage) {
        super(detailMessage);
    }

    public InternalServerError(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public InternalServerError(Throwable throwable) {
        super(throwable);
    }


}
