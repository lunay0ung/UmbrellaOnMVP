package com.raywenderlich.android.rwandroidtutorial

//defines for the view and presenter for the main screen, and update it to look as follows
//****notice that the main contract is interfaces for the specific activity
//and that they inherit from the base interfaces we previously definedd

interface MainContract {
    interface Presenter : BasePresenter {
        fun onViewCreated() //main presenter called back by the main view when the view is created
        fun onLoadWeatherTapped()
    }

    interface View : BaseView<Presenter> {
        fun displayWeatherState(weatherState: WeatherState)
        //view can be invoked to display weather information through displayWeatherState(),
        //which is only called by the presenter
    }

}