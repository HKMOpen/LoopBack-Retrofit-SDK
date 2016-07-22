package com.galleria.loopbackdataclip.tools.loopback;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.strongloop.android.loopback.Model;

/**
 * Created by zJJ on 7/22/2016.
 */
public class LoopBackExclusion implements ExclusionStrategy {

    /**
     * @param f the field object that is under test
     * @return true if the field should be ignored; otherwise false
     */
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getDeclaringClass().equals(Model.class);
    }

    /**
     * @param clazz the class object that is under test
     * @return true if the class should be ignored; otherwise false
     */
    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
