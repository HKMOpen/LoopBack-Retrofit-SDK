package com.galleria.loopbackdataclip.model;

import com.google.gson.annotations.SerializedName;
import com.strongloop.android.loopback.Model;
import com.strongloop.android.remoting.Repository;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by zJJ on 7/20/2016.
 */
public class Basemap extends Model {
    @SerializedName("folder_base_name")
    private String folder_base_name;
    @SerializedName("secret_base_map_file")
    private String secret_base_map_file;
    @SerializedName("rename_file")
    private String rename_file;
    @SerializedName("currency")
    private String currency;
    @SerializedName("owner")
    private String owner;
    @SerializedName("image_type")
    private String image_type;
    @SerializedName("price")
    private BigDecimal price;
    @SerializedName("total_zoom_levels")
    private Scale[] total_zoom_levels;


    public String getFolder_base_name() {
        return folder_base_name;
    }

    public void setFolder_base_name(String folder_base_name) {
        this.folder_base_name = folder_base_name;
    }

    public String getSecret_base_map_file() {
        return secret_base_map_file;
    }

    public void setSecret_base_map_file(String secret_base_map_file) {
        this.secret_base_map_file = secret_base_map_file;
    }

    public String getRename_file() {
        return rename_file;
    }

    public void setRename_file(String rename_file) {
        this.rename_file = rename_file;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Scale[] getTotal_zoom_levels() {
        return total_zoom_levels;
    }

    public void setTotal_zoom_levels(Scale[] total_zoom_levels) {
        this.total_zoom_levels = total_zoom_levels;
    }

    public Basemap(Repository repository, Map<String, ? extends Object> creationParameters) {
        super(repository, creationParameters);
    }

    public Basemap() {
        this(null, null);
    }

}
