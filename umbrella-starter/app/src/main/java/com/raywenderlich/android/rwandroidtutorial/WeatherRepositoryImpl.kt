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

import com.squareup.moshi.Moshi

class WeatherRepositoryImpl : WeatherRepository {
  val jsonRain:String = "{\"id\":804,\"main\":\"clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\", \"rain\":{\"amount\":20},\"clouds\":{\"all\":92}, \"wind\":{\"speed\":7.31,\"deg\":187.002}, \"temp\":289.5,\"humidity\":89,\"pressure\":1013,\"temp_min\":287.04,\"temp_max\":292.04}"
  val jsonSun:String = "{\"id\":804,\"main\":\"sunny\",\"description\":\"sunny\",\"icon\":\"04n\", \"rain\":{\"amount\":0},\"clouds\":{\"all\":92}, \"wind\":{\"speed\":7.31,\"deg\":187.002}, \"temp\":289.5,\"humidity\":89,\"pressure\":1013,\"temp_min\":287.04,\"temp_max\":292.04}"
  val rain = 1
  val sun = 2

  override fun loadWeather(): Weather {
    val moshi = Moshi.Builder().build()

    val json = when((1..2).shuffled().last()) {
      1 -> jsonRain
      else -> jsonSun
    }

    val jsonAdapter = moshi.adapter<Weather>(Weather::class.java)

    val weather: Weather = jsonAdapter.fromJson(json)

    return weather
  }

}
