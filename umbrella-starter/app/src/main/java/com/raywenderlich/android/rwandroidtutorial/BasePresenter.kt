package com.raywenderlich.android.rwandroidtutorial


//interface to define the presenter and the view
//interfaces help with decoupling the parts of the app
//the interface forms a contract between the presenter and the view
interface BasePresenter {
    //this is a generic interface that any presenter you add to your project should implement

    fun onDestroy()


}