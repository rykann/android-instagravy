# Instagravy (Android bootcamp week 1 project)

**Instagravy** is an Android app for browsing popular Instagram photos.

Submitted by: **Ryan Kannegiesser**

Time spent: **11** hours

## User Stories

The following **required** functionality is completed:

* [x] User can scroll through current popular photos from Instagram
* [x] For each photo displayed, user can see the image, username, and caption

The following **optional** features are implemented:

* [ ] For each photo displayed, user can see relative timestamp, like count, and user profile image
* [ ] **Advanced**: Add pull-to-refresh for popular stream with SwipeRefreshLayout
* [ ] **Advanced**: Show latest comment for each photo (bonus: show last 2 comments)
* [ ] **Advanced**: Display each user profile image using a RoundedImageView
* [ ] **Advanced**: Display a nice default placeholder graphic for each image during loading (read more about Picasso)
* [ ] **Advanced**: Improve the user interface through styling and coloring
* [ ] **Bonus**: Allow user to view all comments for an image within a separate activity or dialog fragment
* [ ] **Bonus**: Allow video posts to be played in full-screen using the VideoView

## Video Walkthrough

Here's a walkthrough of implemented user stories:

**TODO:** add walkthrough
<img src='' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

To enable development without a connection, I created an `InstagramClient` interface with two implementations,
`NetworkInstagramClient` and `FakeInstagramClient`. The fake one reads the API response from a local JSON
asset file. The image URLs in the local JSON file also point to local images. In a real app, I'd need to
figure out how to exclude those assets from the APK. The fake client could also be useful in unit tests.

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
