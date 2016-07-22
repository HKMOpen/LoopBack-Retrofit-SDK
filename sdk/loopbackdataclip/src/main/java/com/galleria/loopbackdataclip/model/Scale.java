package com.galleria.loopbackdataclip.model;

import com.google.gson.annotations.SerializedName;
import com.strongloop.android.loopback.Model;
import com.strongloop.android.remoting.Repository;

import java.util.Map;

/**
 * Created by zJJ on 7/21/2016.
 */
public class Scale extends Model {
    @SerializedName("scale")
    private float scale;
    @SerializedName("width")
    private int width;
    @SerializedName("height")
    private int height;
    @SerializedName("x")
    private int x;
    @SerializedName("y")
    private int y;
    @SerializedName("size")
    private int size;
    @SerializedName("level")
    private int level;
    @SerializedName("tiles")
    private DTile[] tiles;

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public DTile[] getTiles() {
        return tiles;
    }

    public void setTiles(DTile[] tiles) {
        this.tiles = tiles;
    }


    public Scale(Repository repository, Map<String, ? extends Object> creationParameters) {
        super(repository, creationParameters);
    }

    public Scale() {
        this(null, null);
    }

}
