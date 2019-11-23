# Weatherly
Weatherly is a sample project to try out mvvm pattern, livedata, room and work manager.

Weatherly is an android app which would pull weather forecast from http://openweathermap.org/API.

<img src="screenshots/Screenshot_1574275840.png" alt="drawing" width="200"/>  <img src="screenshots/Screenshot_1574275813.png" alt="drawing" width="200"/>


Architecture followed is as follows:

  * Application has a LoadWeatherWorker (https://developer.android.com/reference/androidx/work/ListenableWorker) which runs as Per between 2 hours - 2.30 hours 

  * Run a worker LoadWeatherWorker every 2 hours and check for the latest location using FusedLocationProviderClient. 
  * Upon getting latest location it would save the latest location into location table in room entity (Location.java). 
  * MainActivity would be observing the changes in WeatherViewModel which holds the Livedata for location and forecast. 
  * On change in location in Viewmodel, Transformation sends forecast request to repository. 
  * We use NetworkBoundResource implementation to handle persistance better. 
  * We would check shouldFetch and decide whether to go for remoteDatasource or local datasource call. 
  * Once we get response from the remoteDataSource we will save the data in database and will return as Livedata. 
  * We are displaying the response using databinding in recyclerview and other UI controls.
  * Dependency is provided by Dagger2.
  
<img src="/screenshots/work_model.png" width =800/>

TODO in plan:

  * JUnit tests 
  * Better Error handling 
  * UI Fixes: Group forecast data according to date rather than simple 3 hour data list. Each item can be clickable to view details in a horizontal list or so. 
  
