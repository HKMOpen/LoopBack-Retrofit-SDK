package com.galleria.loopbackdataclip.tools.exception;

/**
 * Created by jjyu on 3/7/15.
 */
public class BadGateWayException extends ApiException {
    public BadGateWayException() {
    }

    public BadGateWayException(String detailMessage) {
        super(detailMessage);
    }

    public BadGateWayException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public BadGateWayException(Throwable throwable) {
        super(throwable);
    }
}
