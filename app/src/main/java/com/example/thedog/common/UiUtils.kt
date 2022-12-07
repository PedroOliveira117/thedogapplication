package com.example.thedog.common

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

/**
 * Created by pedrooliveira on 07/12/2022
 * All rights reserved GoodBarber
 */
object UiUtils {

    /**
     * Method used to generate an outlineProvider with a given radius to provide a shape to a component
     * With this method we can apply radius on all corners of a view or just to some by changing onLeft/onTop/onRight/onBottom properties
     * */
    fun createShapeOutlineProvider(radius: Float, onLeft: Boolean = true, onTop: Boolean = true, onRight: Boolean = true, onBottom: Boolean = true) : ViewOutlineProvider {
        return object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                view?.let {
                    var left = 0
                    var top = 0
                    var right = it.width
                    var bottom = it.height

                    if(!onLeft) {
                        left -= radius.toInt()
                    }

                    if(!onTop) {
                        top -= radius.toInt()
                    }

                    if(!onRight) {
                        right += radius.toInt()
                    }

                    if(!onBottom) {
                        bottom += radius.toInt()
                    }

                    outline?.setRoundRect(left, top, right, bottom, radius)
                }
            }
        }
    }
}