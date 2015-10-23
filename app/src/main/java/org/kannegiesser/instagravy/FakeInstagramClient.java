package org.kannegiesser.instagravy;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import cz.msebera.android.httpclient.Header;

public class FakeInstagramClient implements InstagramClient {

    private static final String INSTAGRAM_POPULAR_JSON_FILE = "instagram-popular-fake.json";
    private Context context;

    public FakeInstagramClient(Context context) {
        this.context = context;
    }

    @Override
    public void getPopularPhotos(JsonHttpResponseHandler responseHandler) {
        JSONObject fakeResponse = null;
        try {
            fakeResponse = new JSONObject(readJsonFromFile());
        }
        catch (Exception e) {
            responseHandler.onFailure(500, new Header[0], e, new JSONObject());
            return;
        }
        responseHandler.onSuccess(200, new Header[0], fakeResponse);
    }

    private String readJsonFromFile() throws IOException {
        InputStream inputStream = null;
        String json = null;
        try {
            inputStream = context.getAssets().open(INSTAGRAM_POPULAR_JSON_FILE);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return json;
    }
}
