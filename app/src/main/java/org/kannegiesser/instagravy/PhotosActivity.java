package org.kannegiesser.instagravy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PhotosActivity extends AppCompatActivity {

    private InstagramClient instagramClient = new NetworkInstagramClient();
//    private InstagramClient instagramClient = new FakeInstagramClient(this); // use this to fake out Instagram requests
    private ArrayList<Photo> photos = new ArrayList<Photo>();
    private PhotosAdapter photosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        photosAdapter = new PhotosAdapter(this, photos);
        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(photosAdapter);
        fetchPopularPhotos();
    }

    private void fetchPopularPhotos() {
        instagramClient.getPopularPhotos(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray photosJson = response.getJSONArray("data");
                    for (int i = 0; i < photosJson.length(); i++) {
                        JSONObject photoJson = photosJson.getJSONObject(i);
                        if (photoJson.getString("type").equals("image")) {
                            photos.add(Photo.fromJson(photoJson));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                photosAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                Toast.makeText(PhotosActivity.this, getText(R.string.instagram_fetch_failure), Toast.LENGTH_LONG).show();
            }
        });
    }
}
