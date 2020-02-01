# GetYourGuide

## The challenge
We want to learn more about how you approach a challenge as an Android Engineer. To that end, we have three user stories we would like you to implement in a new Android app:


“As a potential traveler, I want to see a list of short previews of reviews for one of our  most popular Berlin tours.”
“As a potential traveler, I want to just scroll down to read all the reviews for one of our  most popular Berlin tours.”
“As a potential traveler, I want to click to a list item and see the full review with details on another screen.”

The following web service delivers n (count) reviews which include the review author, title, message, date, rating, language code:
https://travelers-api.getyourguide.com/activities/23776/reviews?limit=5&offset=0

URL params
Required
limit=[integer]

offset=[integer]

Optional
sort=[string <sort_by>:<direction>] 

where sort_by=[string rating/date], direction=[string asc/desc]

Ex: sort=rating:asc


### Guidelines
 

The app UI does not need to handle orientation changes
No need to worry about legacy or tablet support
The app does not need to manage user registration and/or authentication
You’re allowed to use third-party libraries if you want
Please use the last public stable version of Android SDK and Android Studio
The final deliverable will be a package or a link to an accessible Git repository containing all of the code necessary to build and install the app along with a README.md with relevant information
Note: For this endpoint to give a valid response you need to change the user agent in the request header to "GetYourGuide"

## The solution

To solve this problem I'm using:
- MVVM
- Kotlin as Programming Language
- Koin for Dependency Injection
- Retrofit for REST Api requests
- LiveData and ViewModel
- Android Navigation Component
- Android Paging
- Glide
