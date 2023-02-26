## To get the working application: 
- you need to register on Reddit as a developer (https://github.com/reddit-archive/reddit/wiki/OAuth2), 
- get your own application data 
and put it into the AuthConst.kt:
- const val CLIENT_ID = "your-client-id"
- const val REDIRECT_URI = "your-redirect-uri"

# study project using API for Reddit
Humblr for Reddit

Task was to develop Android mobile application - analog of Reddit. 

This application allows registered Reddit users to share links on the information they like,
find interesting content and discuss it.

Full API description: https://www.reddit.com/dev/api

UI made based on given Figma prototype.

Use cases:

New app User sees Onboarding.
Onboarding is displayed immediately on the first launch of the application.

Authorization:
a) Authorization screen is always displayed after onboarding.
b) Can't use application without authorization.
Authentication: https://github.com/pratik98/Reddit-OAuth

See "Subreddits":

Search bar at the top of the screen, the "New/Popular" switch is below.
Offer possibility of subscribing in the list of subreddits.
By clicking on the subreddit, open it's description.

See Subreddit:

Open list of subreddit' posts immediately in the subreddit.
Open the post if clicked.
Fix subreddit name and "Info" button, opens the description of the subreddit, at the top of the window.


See the subreddit description:
General info: # of subscribers, short description. 
Buttons:
"Subscribe"/"unsubscribe".
"Share".


See the posts:

Author, time, post text.
Buttons:
"Save".
"Vote up/down".


See user profile:

Opens any user profile.
Short info.
"Make Friends" button.
User's posts and comments.

See "Favourites"

2 tabs: Subreddits/Posts, All/Saved.
Lists of subreddits and posts below.

For Saved: try to upload online. If no info available - show that info is unavailable or deleted.


Add to friends:

Can add any user.
List of friends is available on Profile screen.

See "Profile":

Logout button.
Profile description.
"Clear saved" button.
List of friends button, friends can be seen on separate screen.
