/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.rwandroidtutorial

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text
import kotlin.math.log


//VIEW
class MainActivity : AppCompatActivity(), MainContract.View {


  private val TAG = ""
  internal lateinit var imageView: ImageView
  internal lateinit var button: Button
  internal lateinit var textView: TextView
  //val weatherRepository: WeatherRepository = WeatherRepositoryImpl()

  internal lateinit var presenter: MainContract.Presenter
  //add a presenter property instead of the model WeatherRepository
  // **the view needs the presenter to invoke user initiated callbacks

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    Log.i(TAG, "onCreate")


    imageView = findViewById(R.id.imageView)
    button = findViewById(R.id.button)
    textView = findViewById(R.id.textView)
    //button.setOnClickListener { loadWeather() }
    //loadWeather()

    /*
    * store a reference to the presenter just after creating it
    * **notice that it also creates and passes an instance of DependencyInjectorImpl as part of the creation.
    * */
    setPresenter(MainPresenter(this, DependencyInjectorImpl()))
    presenter.onViewCreated()

    /*
    * offload handling of the button callback to the presenter
    * --> 버튼 클릭 이벤트를 프레젠터 쪽에 넘김
    * */
    button.setOnClickListener{
      Log.i(TAG, "setOnClickListener")
      presenter.onLoadWeatherTapped()
    }
  }


  /*
  * notify the presenter when the view is being destroyed
  * the presenter uses this opportunity to clean up any state that is no longer required beyond this point
  * */
  override fun onDestroy() {
    super.onDestroy()
  }


  /*
  * implement the method required from the BaseView interface to set the presenter
  * */
  override fun setPresenter(presenter: MainContract.Presenter) {
    Log.i(TAG, "setPresenter")
    this.presenter = presenter
  }

  /*
  * override this method as it is now part of the view interface
  *
  * **compare this with the below one
  * */
  override fun displayWeatherState(weatherState: WeatherState) {
    Log.i(TAG, "displayWeatherState")
     val drawable = resources.getDrawable(weatherDrawableResId(weatherState), applicationContext.theme)
     this.imageView.setImageDrawable(drawable)
     this.textView.setText(weatherState.name)
  }

/*
  fun displayWeatherState(weatherState: WeatherState) {
    val drawable = resources.getDrawable(weatherDrawableResId(weatherState),
            applicationContext.getTheme())
    this.imageView.setImageDrawable(drawable)
  }
*/

  fun weatherDrawableResId(weatherState: WeatherState) : Int {
    Log.i(TAG, "weatherDrawableResId")
    return when (weatherState) {
      WeatherState.SUN -> R.drawable.ic_sun
      WeatherState.RAIN -> R.drawable.ic_umbrella
    }
  }

 /*
  **move these methods into the presenter
  private fun loadWeather() {
    val weather = weatherRepository.loadWeather()
    val weatherState = weatherStateForWeather(weather)
    displayWeatherState(weatherState)
  }

  private fun weatherStateForWeather(weather: Weather) : WeatherState {
    if (weather.rain!!.amount!! > 0) {
      return WeatherState.RAIN
    }
    return WeatherState.SUN
  }*/
}

