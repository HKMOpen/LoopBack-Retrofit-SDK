package tileview.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.qozix.tileview.TileView;
import com.qozix.tileview.graphics.BitmapProvider;
import com.qozix.tileview.tiles.Tile;

import java.util.concurrent.ExecutionException;

/**
 * Created by zJJ on 7/20/2016.
 */
public class LoadSystemMap extends TileViewActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // multiple references
        TileView tileView = getTileView();
        // size of original image at 100% mScale
        tileView.setSize(4015, 4057);
        // detail levels
        tileView.addDetailLevel(1.000f, "tiles/fantasy/1000/%d_%d.jpg");
        tileView.addDetailLevel(0.500f, "tiles/fantasy/500/%d_%d.jpg");
        tileView.addDetailLevel(0.250f, "tiles/fantasy/250/%d_%d.jpg");
        tileView.addDetailLevel(0.125f, "tiles/fantasy/125/%d_%d.jpg");
        // allow scaling past original size
        tileView.setScaleLimits(0, 2);
        // lets center all markers both horizontally and vertically
        tileView.setMarkerAnchorPoints(-0.5f, -0.5f);
        // individual markers
        placeMarker(R.drawable.fantasy_elves, 1616, 1353);
        placeMarker(R.drawable.fantasy_humans, 2311, 2637);
        placeMarker(R.drawable.fantasy_dwarves, 2104, 701);
        placeMarker(R.drawable.fantasy_rohan, 2108, 1832);
        placeMarker(R.drawable.fantasy_troll, 3267, 1896);
        tileView.setTransitionsEnabled(true);
        tileView.setShouldRecycleBitmaps(false);
        tileView.setShouldRenderWhilePanning(true);
        tileView.setBitmapProvider(new BitmapProvider() {
            @Override
            public Bitmap getBitmap(Tile tile, Context context) {
                Object data = tile.getData();
                if (data instanceof String) {
                    Bitmap databitmap = null;
                    String unformattedFileName = (String) tile.getData();
                    String formattedFileName = String.format(unformattedFileName, tile.getColumn(), tile.getRow());
                    try {
                        databitmap = Glide.with(getApplicationContext())
                                .load("file:///android_asset/" + formattedFileName)
                                .asBitmap()
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .into(-1, -1)
                                .get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    return databitmap;
                }
                return null;
            }
        });
        // frame the troll
        frameTo(3267, 1896);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void placeMarker(int resId, double x, double y) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(resId);
        getTileView().addMarker(imageView, x, y, null, null);
    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "LoadSystemMap Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://tileview.demo/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "LoadSystemMap Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://tileview.demo/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
