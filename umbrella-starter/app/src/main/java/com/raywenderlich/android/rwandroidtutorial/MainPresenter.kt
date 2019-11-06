package com.raywenderlich.android.rwandroidtutorial

import android.util.Log


/*
* ******IMPORTANT******
*
* the presenter has no code that uses the Android APIs
* */
class MainPresenter (view: MainContract.View, //the presenter takes in an instance of the view
                    dependencyInjector: DependencyInjector)
    : MainContract.Presenter {

    private val TAG = "MainPresenter"
    /*
    * the presenter holds on to an instance of the weather repository,
    * which is the model in this app
    * */
    private val weatherRepository: WeatherRepository
                = dependencyInjector.weatherRepository()

    private var view: MainContract.View? = view //this interacts only with the interface, as defined in MainContract


    private fun loadWeather() {
        Log.i(TAG,"loadWeather")
        val weather = weatherRepository.loadWeather()
        val weatherState = weatherStateForWeather(weather)
        view?.displayWeatherState(weatherState)
    }

    private fun weatherStateForWeather(weather: Weather) : WeatherState {
        Log.i(TAG,"weatherStateForWeather")
        if (weather.rain!!.amount!! > 0) {
            return WeatherState.RAIN
        }
        return WeatherState.SUN
    }

    //implement the rest of the presenter contract by adding the following methods
    override fun onViewCreated() {
        loadWeather()
    }

    override fun onLoadWeatherTapped() {
        Log.i(TAG,"onLoadWeatherTapped")
        loadWeather()
    }



    //do some clean up in onDestroy
    //and invoke fetching the weather data in both onCreateView and onLoadWeatherTapped
    override fun onDestroy() {
        this.view = null
    }
}