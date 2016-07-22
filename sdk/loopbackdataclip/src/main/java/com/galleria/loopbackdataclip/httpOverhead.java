package com.galleria.loopbackdataclip;

import android.annotation.TargetApi;
import android.os.Build;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zJJ on 7/22/2016.
 */
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class httpOverhead implements Interceptor {

    private static final String USER_AGENT = "Android " + Build.VERSION.SDK_INT + "_" + Build.DEVICE + "_" + Build.MANUFACTURER;
    // private CookieHanger mCookieHanger;
    private int cache_min = 1;
    private String api_version = "", countryCode = "", token = "";

    public void setAPIVersion(String text) {
        api_version = text;
    }

    public void setCountryCode(String code) {
        countryCode = code;
    }

    public void setCacheMinutes(int min) {
        cache_min = min;
    }

    public void setLoginToken(String token) {
        this.token = token;
    }

    // tolerate 4-weeks stale
    public static int timeByWeeks(int d) {
        int maxStale = 60 * 60 * 24 * 7 * d;
        return maxStale;
    }

    // read from cache for 1 minute
    public static int timeByMins(int m) {
        int maxStale = 60 * m;
        return maxStale;
    }

    public void removeAllParams() {
        api_version = "";
        countryCode = "";
        cache_min = 0;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
     /*
        request.addHeader("User-Agent", USER_AGENT);
        request.addHeader("Accept", "application/json");

        if (!api_version.isEmpty()) {
            request.addQueryParam("version", api_version);
        }

        if (!countryCode.isEmpty()) {
            request.addQueryParam("catalog_country", countryCode);
        }*/

        Request originalRequest = chain.request();
        long t1 = System.nanoTime();
        Request.Builder reqbu = originalRequest.newBuilder();

        //   logger.info(String.format("Sending request %s on %s%n%s",
        //    request.url(), chain.connection(), request.headers()));
        if (!token.isEmpty()) {
            reqbu.header("access_token", token);
        }
        if (cache_min > 0) {
            reqbu.addHeader("Cache-Control", "public, max-age=" + timeByMins(cache_min));
        }
        if (USER_AGENT != null) {
            reqbu.addHeader("User-Agent", USER_AGENT);
        }
     /*   if (mCookieHanger != null) {
            if (!mCookieHanger.getRaw().isEmpty()) {
                reqbu.addHeader("Cookie", mCookieHanger.getRaw());
            }
        }*/
        Request finalreq = reqbu.build();
        Response response = chain.proceed(finalreq);
        long t2 = System.nanoTime();
        //   logger.info(String.format("Received response for %s in %.1fms%n%s",
        //         response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }

}