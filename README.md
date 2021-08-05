# NYArticle

Simple app to hit the NY Times Most Popular Articles API and show a list of articles.

https://api.nytimes.com/svc/mostpopular/v2/viewed/7.json?api-key=sample-key To use this api you need to generate SAMPLE KEY from https://developer.nytimes.com/get-started

## The app has following packages:
1.interfaces - Contains the data response fo API</br>
2.model - Contains the data model class for catching data</br>
3.network - Contains the API and retrofit Client integration</br>
4.ui - Contains the view classes along with thier view models</br>
5.utils- Contains the Utilities and app constatnts</br>

### Current Flow of the project:
* Data Loading from API - To load the data from network RETROFIT library used
* Data Handling - for data handling Livedata, GSON, viewmodel
* Architecture pattern - MVVM pattern
* Internet network handling - displaying snackbar for the network status

## Jacoco and Static Code Analysis

You can generate code coverage report for the test cases (using Jacoco) and Static code anyalysis report by running following command locally in the root of the project repository.
  ./gradlew testDebugUnitTest


