package com.galleria.loopbackdataclip.Repos;

import android.content.Context;
import android.content.SharedPreferences;

import com.galleria.loopbackdataclip.model.Basemap;
import com.strongloop.android.loopback.AccessToken;
import com.strongloop.android.loopback.ModelRepository;
import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.remoting.adapters.RestContract;
import com.strongloop.android.remoting.adapters.RestContractItem;

import java.util.HashMap;

/**
 * Created by zJJ on 7/22/2016.
 */
public class BasemapRepository<U extends Basemap> extends ModelRepository<U> {
    public static final String SHARED_PREFERENCES_NAME =
            RestAdapter.class.getCanonicalName();

    public BasemapRepository(String className, Class<U> userClass) {
        this(className, null, userClass);
    }

    public BasemapRepository(String className, String nameForRestUrl, Class<U> userClass) {
        super(className, nameForRestUrl, userClass);
    }

    private SharedPreferences getSharedPreferences() {
        return getApplicationContext().getSharedPreferences(
                SHARED_PREFERENCES_NAME,
                Context.MODE_PRIVATE);
    }
    public RestContract createContract() {
        RestContract contract = super.createContract();
        String basePath = "/basemaps/:id";
        contract.addItem(new RestContractItem("/" + getNameForRestUrl() + "/greet", "POST"),
                getClassName() + ".greet");

        return contract;
    }

    public interface BasemapCallback<U> {
        void onSuccess(AccessToken token, U currentUser);

        void onError(Throwable t);
    }

    public void getBasemapById(final String basemap_id, BasemapCallback cb) {


    }
}
