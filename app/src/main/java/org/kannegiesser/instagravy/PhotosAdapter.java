package org.kannegiesser.instagravy;

import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;

public class PhotosAdapter extends ArrayAdapter<Photo> {

    public PhotosAdapter(Context context, List<Photo> photos) {
        super(context, 0, photos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Photo photo = getItem(position);

        // if a recycled view wasn't given, inflate a new one
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }

        ImageView profileImage = (ImageView) convertView.findViewById(R.id.profileImage);
        profileImage.setImageResource(android.R.color.transparent);
        Picasso.with(getContext()).load(photo.userProfileImageUrl).noFade().into(profileImage);

        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        tvUserName.setText(photo.userName);

        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        ivPhoto.setImageResource(android.R.color.transparent);
        Picasso.with(getContext()).load(photo.url).into(ivPhoto);

        TextView tvLikes = (TextView) convertView.findViewById(R.id.tvLikes);
        Resources res = getContext().getResources();
        String formattedLikesCount = NumberFormat.getIntegerInstance().format(photo.likesCount);
        tvLikes.setText(String.format(res.getString(R.string.likes_count), formattedLikesCount));

        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        tvCaption.setText(Html.fromHtml("<b>" + photo.userName + "</b> -- " + photo.caption));

        return convertView;
    }
}