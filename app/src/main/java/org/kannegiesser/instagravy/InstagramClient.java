package org.kannegiesser.instagravy;

import com.loopj.android.http.JsonHttpResponseHandler;

public interface InstagramClient {

    public void getPopularPhotos(JsonHttpResponseHandler responseHandler);
}
