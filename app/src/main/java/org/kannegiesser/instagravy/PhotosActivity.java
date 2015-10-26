package org.kannegiesser.instagravy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cz.msebera.android.httpclient.Header;

public class PhotosActivity extends AppCompatActivity {

    private static final String TAG = "PhotosActivity";

    private InstagramClient instagramClient = new NetworkInstagramClient();
//    private InstagramClient instagramClient = new FakeInstagramClient(this); // use this to fake out Instagram requests
    private ArrayList<Photo> photos = new ArrayList<Photo>();
    private PhotosAdapter photosAdapter;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        photosAdapter = new PhotosAdapter(this, photos);
        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(photosAdapter);
        setUpRefreshListener();
        fetchPopularPhotos();
    }

    private void setUpRefreshListener() {
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchPopularPhotos();
            }
        });
    }

    private void fetchPopularPhotos() {
        instagramClient.getPopularPhotos(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                ArrayList<Photo> fetchedPhotos = photosFromInstagramResponse(response);
                addFetchedPhotosToStream(fetchedPhotos);
                photosAdapter.notifyDataSetChanged();
                swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject response) {
                Toast.makeText(PhotosActivity.this, getText(R.string.instagram_fetch_failure), Toast.LENGTH_LONG).show();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @NonNull
    private ArrayList<Photo> photosFromInstagramResponse(JSONObject response) {
        ArrayList<Photo> fetchedPhotos = new ArrayList<Photo>();
        try {
            JSONArray photosJson = response.getJSONArray("data");
            for (int i = 0; i < photosJson.length(); i++) {
                JSONObject photoJson = photosJson.getJSONObject(i);
                if (photoJson.getString("type").equals("image")) {
                    fetchedPhotos.add(Photo.fromJson(photoJson));
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "Error extracting photo data from Instagram response", e);
        }
        return fetchedPhotos;
    }

    private void addFetchedPhotosToStream(ArrayList<Photo> fetchedPhotos) {
        // remove photos from fetchedPhotos that are already present in the stream
        for (Photo existingPhoto : photos) {
            fetchedPhotos.remove(existingPhoto);
        }

        // reverse sort fetched photos by createdAt
        Collections.sort(fetchedPhotos, Collections.reverseOrder(new Comparator<Photo>() {
            @Override
            public int compare(Photo lhs, Photo rhs) {
                return lhs.createdAt - rhs.createdAt;
            }
        }));

        // add new photos to start of list
        photos.addAll(0, fetchedPhotos);
    }
}