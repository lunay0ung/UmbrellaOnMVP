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

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.VectorDrawable
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class DrawableMatcher(val drawableResIds : List<Int>) : TypeSafeMatcher<View>() {

  override fun describeTo(description: Description) {
    description.appendText("with drawable from resource id: ")
    drawableResIds.forEach { drawableResId ->
      description.appendValue(drawableResId)
    }
  }

  override fun matchesSafely(item: View?): Boolean {
    return drawableResIds.any { drawableResId -> matchesDrawable(drawableResId, item) }
  }

  fun matchesDrawable(drawableResId: Int, item: View?) : Boolean {
    if (item !is ImageView) {
      return false
    }
    val target = item.drawable ?: return false
    if (drawableResId < 0) {
      return false
    }
    val expectedDrawable = ContextCompat.getDrawable(item.context, drawableResId)
        ?: return false

    if (target is BitmapDrawable) {
      val targetBitmap = (target).bitmap
      val otherBitmap = (expectedDrawable as BitmapDrawable).bitmap
      return targetBitmap.sameAs(otherBitmap)
    }
    if (target is VectorDrawable) {
      val targetBitmap = vectorToBitmap(target)
      val otherBitmap = vectorToBitmap(expectedDrawable  as VectorDrawable)
      return targetBitmap.sameAs(otherBitmap)
    }
    return false
  }

  private fun vectorToBitmap(vectorDrawable: VectorDrawable): Bitmap {
    val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
    vectorDrawable.draw(canvas)
    return bitmap
  }
}
