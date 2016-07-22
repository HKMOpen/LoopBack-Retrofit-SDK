package com.galleria.loopbackdataclip;

import android.app.Application;

import com.galleria.loopbackdataclip.Repos.BasemapRepository;
import com.galleria.loopbackdataclip.tools.gson.GsonFactory;
import com.galleria.loopbackdataclip.tools.gson.WordpressConversion;
import com.galleria.loopbackdataclip.tools.loopback.LoopBackExclusion;
import com.galleria.loopbackdataclip.tools.realm.RealmExclusion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.strongloop.android.loopback.AccessToken;
import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.User;
import com.strongloop.android.loopback.UserRepository;
import com.strongloop.android.loopback.callbacks.VoidCallback;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import static com.galleria.loopbackdataclip.ConstantFields.APP_PASS_SAVED;
import static com.galleria.loopbackdataclip.ConstantFields.APP_USER_NAME;

/**
 * Created by zJJ on 7/20/2016.
 */
public class GalleriaLB extends ApplicationBase {
    public static String BASE_URL;
    public static GalleriaLB instance;
    private RestAdapter restAdapter;
    private UserRepository userRepo;
    private BasemapRepository basemapRepo;
    protected Gson gsonsetup;
    private User logined_user;
    protected Retrofit apicreator;
    protected OkHttpClient client2 = new OkHttpClient();
    protected httpOverhead mInterceptor;

    public static GalleriaLB getInstance(Application app, String base_domain) {
        GalleriaLB localInstnace = instance;
        if (localInstnace == null) {
            synchronized (GalleriaLB.class) {
                localInstnace = instance;
                if (localInstnace == null) {
                    instance = localInstnace = new GalleriaLB(app);
                }
            }
        }
        localInstnace.init();
        return localInstnace;
    }


    private GalleriaLB(Application c) {
        super(c);
    }

    private void init() {
        restAdapter = new RestAdapter(app.getBaseContext(), BASE_URL);
        userRepo = restAdapter.createRepository(UserRepository.class);
        basemapRepo = restAdapter.createRepository(BasemapRepository.class);
        gsonsetup = new GsonBuilder()
                .setDateFormat(ConstantFields.DATE_FORMAT)
                .registerTypeAdapterFactory(new GsonFactory.NullStringToEmptyAdapterFactory())
                .registerTypeAdapter(String.class, new WordpressConversion())
                .setExclusionStrategies(new RealmExclusion())
                .setExclusionStrategies(new LoopBackExclusion())
                .create();
    }


    public void registration_new_user() {
        User user = userRepo.createUser("name@example.com", "password");
    }

    private static final UserRepository.LoginCallback BACK_LOGIN = new UserRepository.LoginCallback<User>() {
        @Override
        public void onSuccess(AccessToken token, User currentUser) {
            // Intent goToMain = new Intent(getApplicationContext(), Main.class);
            ///  startActivity(goToMain);
            //  finish();
            // System.out.println(token.getUserId() + ":" + currentUser.getId());
        }

        @Override
        public void onError(Throwable t) {
            //Log.e("Chatome", "Login E", t);
        }
    };


    public final boolean userGetisLogin() {
        User currentUser = userRepo.getCachedCurrentUser();
        if (currentUser != null) {
            // logged in
            logined_user = currentUser;
        } else {
            // anonymous user or findCurrentUser was not called yet
        }
        return currentUser != null;
    }

    public final void login_user(String username, String password, UserRepository.LoginCallback callback) {
        userRepo.loginUser(username, password, callback);
    }

    public final void logout(VoidCallback cb) {
        userRepo.logout(cb);
    }

    public final void save_authen(String username, String password) {
        saveInfo(APP_USER_NAME, username);
        saveInfo(APP_PASS_SAVED, password);
    }


    @Override
    protected void removeAllData() {

    }
}
