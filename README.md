# Passionfruit Orange Guava
A small Android coding project that will search github users, displaying the number of repositories each has. 

## Activity-Fragment pattern

This app uses the Single Activity pattern.
Deep linking should be used to navigate to a specific page.
This app uses the MVVM pattern. 

## Technologies Used

* Dagger is used for dependency injection. 
* Databinding is used to bind data to views. Custom bindings can be found as top level decelerations in
CustomBindings.kt
* Jetpack Navigation is used to maintain fragments. (even though there is only one fragment)
* Retrofit is used as the HttpClient
* Coroutines and Flows are used to interact with the network layer and keep the UI thread unblocked
* Gson is used to parse JSON

## Testing

Testing is a WIP, the only tests included are the ones Android Studio auto-generated when I created this project.
