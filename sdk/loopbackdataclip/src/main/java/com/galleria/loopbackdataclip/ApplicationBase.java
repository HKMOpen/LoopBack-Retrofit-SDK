package com.galleria.loopbackdataclip;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jjyu on 18/11/15.
 */
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public abstract class ApplicationBase {

    protected final Application app;
    protected final SharedPreferences sharedPreferences;
    public static final String INSTALLATION_VERSION = "version_sdk";
    public static final String DEFAULT_DICTIONARY = "default_dictionary";
    public static final String APPLICATION_CONFIG_FILE = "app_conf_file";
    public static final String CONFIG_FILE_SAVE_TIME = "save_time_confg";
    protected String debug_version;
    protected ArrayList<String> default_dictionary_list = new ArrayList<>();

    public ApplicationBase(Application app) {
        this.app = app;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(app);
    }

    protected String loadRef(final String tag) {
        return sharedPreferences.getString(tag, "");
    }

    protected int loadRefi(final String tag, int default_val) {
        int h = sharedPreferences.getInt(tag, default_val);
        return h;
    }

    protected int loadRefi(final String tag) {
        return loadRefi(tag, 0);
    }

    protected long loadRefL(final String tag) {
        return loadRefL(tag, 0);
    }

    protected long loadRefL(final String tag, long default_l) {
        long data = sharedPreferences.getLong(tag, default_l);
        return data;
    }


    protected void saveInfo(final String tag, final String data) {
        sharedPreferences.edit()
                .putString(tag, data)
                .apply();
    }

    protected void saveInfo(final String tag, final long data) {
        sharedPreferences.edit()
                .putLong(tag, data)
                .apply();
    }

    protected void saveInt(final String tag, final int data) {
        sharedPreferences.edit()
                .putInt(tag, data)
                .apply();
    }

    protected abstract void removeAllData();

    protected void checkDataVersioning() {
        if (sharedPreferences.getInt(INSTALLATION_VERSION, -1) == -1) {
            // there is no data set
            saveInt(INSTALLATION_VERSION, BuildConfig.VERSION_CODE);
            debug_version = "there is no data set";
        } else if (sharedPreferences.getInt(INSTALLATION_VERSION, -1) < BuildConfig.VERSION_CODE) {
            // the upgrade data is needed
            removeAllData();
            saveInt(INSTALLATION_VERSION, BuildConfig.VERSION_CODE);
            debug_version = "the upgrade data is needed";
        } else if (sharedPreferences.getInt(INSTALLATION_VERSION, -1) > BuildConfig.VERSION_CODE) {
            //the app is older and the data is for the newer version
            removeAllData();
            saveInt(INSTALLATION_VERSION, BuildConfig.VERSION_CODE);
            debug_version = "the app is older and the data is for the newer version";
        } else {
            //same normal data set
            debug_version = "normal data set";
        }
    }

    protected void remove_file(String folder_name, String file_name) {
        final String root = Environment.getExternalStorageDirectory().toString() + File.separator;
        final File myDir = new File(root + folder_name + File.separator + file_name);
        if (myDir.exists()) {
            myDir.delete();
        }
    }

    private String copyBundledRealmFile(InputStream inputStream, String outFileName) {
        try {
            File file = new File(app.getFilesDir(), outFileName);
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, bytesRead);
            }
            outputStream.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * save dictionary for a group of keywords
     *
     * @param tag  the tag name
     * @param list the list string array
     */
    protected void save_dictionary(String tag, ArrayList<String> list) {
        if (list.size() > 0) {
            String singleString = list.toString();
            saveInfo(tag, singleString);
        }
    }

    /**
     * loading dictionary keywords
     *
     * @param tag the tag name
     * @return the list of strings
     * @throws Resources.NotFoundException the not found exception
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    protected List<String> load_dictionary(String tag) throws Resources.NotFoundException {
        if (loadRef(tag).isEmpty())
            throw new Resources.NotFoundException("There is no data found from the field " + tag);
        final String block = loadRef(tag);
        List<String> res_str = Arrays.asList(block.substring(0, block.length() - 1).substring(1).split("[\\s,]+"));
        return res_str;
    }

    /**
     * automatically save the dictionary list
     */
    public void save_dictionary_auto() {
        save_dictionary(DEFAULT_DICTIONARY, default_dictionary_list);
    }

    /**
     * automatically load the list of the keywords from the dictionary
     */
    public void load_dictionary_auto() {
        try {
            load_dictionary(DEFAULT_DICTIONARY);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addKeyword(String keyword) {
        if (default_dictionary_list.indexOf(keyword) == -1) {
            default_dictionary_list.add(keyword);
        }
    }

    public void removeKeyword(String keyword) {
        if (default_dictionary_list.indexOf(keyword) > -1) {
            default_dictionary_list.remove(default_dictionary_list.indexOf(keyword));
        }
    }

    public void onSaveState() {

    }

    public void onRestoreState() {

    }


}
