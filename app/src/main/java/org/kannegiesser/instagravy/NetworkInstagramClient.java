package org.kannegiesser.instagravy;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class NetworkInstagramClient implements InstagramClient {

    private static final String CLIENT_ID = "74731cbfef9445ed889b9fdbe9dc288e";
    private static final String POPULAR_URL = "https://api.instagram.com/v1/media/popular";

    @Override
    public void getPopularPhotos(JsonHttpResponseHandler responseHandler) {
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(POPULAR_URL, new RequestParams("client_id", CLIENT_ID), responseHandler);
    }
}