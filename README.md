# PostsApp

This is a simple demo Android app with the following features:

- View a list of posts 
- Link to post details
- View a list of users
- View a list of comments linked from a post

Built with the latest Android Kotlin development using:

- AndroidX
- Koin (Dependency Injection)
- MVVM / View Models
- Retrofit2 (API HTTP calls)
- Moshi (JSON serialisation)
- RxJava2
- Live Data (Architecture Component)
- Navigation (Architecture Component)
- Picasso (for loading images)
- Simple transition animations

Shows unit testing using:

- JUnit
- Mockk
- Live-data testing helpers
- Core testing / instant executor rule

And UI testing using:

- Espresso
- Mockk-Android

Future improvements / ToDos

- Improve UI styling
- Better error handling and status notifications to users
- Add loading notifications
- Seperate out api models from domain models
- In memory mock http server for UI Tests 


![](postsAppDemo.gif)