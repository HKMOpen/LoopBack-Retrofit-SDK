package com.galleria.loopbackdataclip.model;

import com.google.gson.annotations.SerializedName;
import com.strongloop.android.loopback.Model;
import com.strongloop.android.remoting.Repository;

import java.util.Map;

/**
 * Created by zJJ on 7/21/2016.
 */
public class DTile extends Model {
    @SerializedName("y")
    private int y;
    @SerializedName("x")
    private int x;
    @SerializedName("tile")
    private STile[] tile;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public STile[] getTile() {
        return tile;
    }

    public void setTile(STile[] tile) {
        this.tile = tile;
    }

    public DTile(Repository repository, Map<String, ? extends Object> creationParameters) {
        super(repository, creationParameters);
    }

    public DTile() {
        this(null, null);
    }


}
