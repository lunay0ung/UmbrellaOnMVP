package com.raywenderlich.android.rwandroidtutorial


/*
*
* Similar to BasePresenter,
* this is the interface that all views in your app should implement.
* Since all views interact with a presenter,
* the view is given a generic type T for the presenter,
* and they must all contain a setPresenter() method.
*
* **제네릭
* 클래스에서 사용할 타입을 클래스 외부에서 설정하는 것
* */
interface BaseView<T> { //interfaces help with decoupling the parts of the app

    fun setPresenter(presenter : T)
   /* * Since all views interact with a presenter,
    * the view is given a generic type T for the presenter,
    * and they must all contain a setPresenter() method.*/
}