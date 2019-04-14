# PostsApp

This is a simple demo Android app with the following features:

- View a list of posts 
- View post details including title, body, author name, number of comments and author's avatar
- View a list of users including name, email, company and avatar
- View a list of all comments
- View a list of comments associated with a post
- Loads data from an API and caches the results (in memory)

Built with the latest Android Kotlin development using:

- AndroidX
- Koin (Dependency Injection)
- MVVM / View Models
- Retrofit2 (API HTTP calls)
- RxJava2
- Live Data (Architecture Component)
- Navigation (Architecture Component)
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
- Add local disk caching (e.g. room) and extra layer
- Add more animations
- Separate out api models from domain models
- In memory mock http server for UI Tests 
- Setup Proguard / prod build


![](postsAppDemo.gif)
