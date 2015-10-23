package org.kannegiesser.instagravy;

import org.json.JSONException;
import org.json.JSONObject;

public class Photo {

    public String caption;
    public String url;
    public String userName;

    public static Photo fromJson(JSONObject json) throws JSONException {
        Photo photo = new Photo();
        JSONObject captionObj = json.optJSONObject("caption");
        if (captionObj != null) {
            photo.caption = captionObj.getString("text");
        }
        photo.url = json.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
        photo.userName = json.getJSONObject("user").getString("username");
        return photo;
    }
}
