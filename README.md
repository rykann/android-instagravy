# Instagravy

**Instagravy** is an Android app for browsing popular Instagram photos.

*(CodePath Android bootcamp week 1 project)*

Submitted by: **Ryan Kannegiesser**

Time spent: **18** hours

## User Stories

The following **required** functionality is completed:

* [x] User can scroll through current popular photos from Instagram
* [x] For each photo displayed, user can see the image, username, and caption

The following **optional** features are implemented:

* [x] For each photo displayed, user can see like count and user profile image (relative timestamp not implemented)
* [x] **Advanced**: Add pull-to-refresh for popular stream with SwipeRefreshLayout
* [ ] **Advanced**: Show latest comment for each photo (bonus: show last 2 comments)
* [x] **Advanced**: Display each user profile image using a [CircleImageView](https://github.com/hdodenhof/CircleImageView)
* [ ] **Advanced**: Display a nice default placeholder graphic for each image during loading (read more about Picasso)
* [x] **Advanced**: Improve the user interface through styling and coloring
* [ ] **Bonus**: Allow user to view all comments for an image within a separate activity or dialog fragment
* [ ] **Bonus**: Allow video posts to be played in full-screen using the VideoView

## Notes

When implementing pull-to-refresh, I took care to improve the user experience by removing already
shown photos from the set of newly fetched photos. To help with this, I overrode `equals` and
`hashCode` in the `Photo` class to be based on the photo's Instagram id. The app also reverse sorts
each set of fetched photos by created time and inserts it at the beginning of the stream.

To enable development without a connection, I created an `InstagramClient` interface with two implementations,
`NetworkInstagramClient` and `FakeInstagramClient`. The fake one reads the API response from a local JSON
asset file. The image URLs in the local JSON file also point to local images. In a real app, I'd need to
figure out how to exclude those assets from the APK. The fake client could also be useful in unit tests.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/WNxHEWy.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## License

    Copyright 2015 Ryan Kannegiesser

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
