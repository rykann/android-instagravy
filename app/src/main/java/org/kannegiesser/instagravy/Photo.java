package org.kannegiesser.instagravy;

import org.json.JSONException;
import org.json.JSONObject;

public class Photo {

    public String id;
    public String caption;
    public String url;
    public String userName;
    public String userProfileImageUrl;
    public int likesCount;
    public int createdAt;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Photo photo = (Photo)obj;
        return photo.id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public static Photo fromJson(JSONObject json) throws JSONException {
        Photo photo = new Photo();
        photo.id = json.getString("id");
        JSONObject captionObj = json.optJSONObject("caption");
        if (captionObj != null) {
            photo.caption = captionObj.getString("text");
        }
        photo.url = json.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
        JSONObject userObj = json.getJSONObject("user");
        photo.userName = userObj.getString("username");
        photo.userProfileImageUrl = userObj.getString("profile_picture");
        photo.likesCount = json.getJSONObject("likes").getInt("count");
        photo.createdAt = json.getInt("created_time");
        return photo;
    }
}
