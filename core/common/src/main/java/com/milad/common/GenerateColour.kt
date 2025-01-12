package com.milad.common

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange


fun Context.mixTwoColors(
    @AttrRes color1: Int,
    @AttrRes color2: Int,
    @FloatRange(from = 0.0, to = 1.0) amount: Float
): Int {
    val firstColor = this.getColorFromAttr(color1)
    val secondColor = this.getColorFromAttr(color2)

    val ALPHA_CHANNEL: Byte = 24
    val RED_CHANNEL: Byte = 16
    val GREEN_CHANNEL: Byte = 8
    val BLUE_CHANNEL: Byte = 0
    val inverseAmount = 1.0f - amount

    val a =
        ((firstColor shr ALPHA_CHANNEL.toInt() and 0xff).toFloat() * amount + (secondColor shr ALPHA_CHANNEL.toInt() and 0xff).toFloat() * inverseAmount).toInt() and 0xff
    val r =
        ((firstColor shr RED_CHANNEL.toInt() and 0xff).toFloat() * amount + (secondColor shr RED_CHANNEL.toInt() and 0xff).toFloat() * inverseAmount).toInt() and 0xff
    val g =
        ((firstColor shr GREEN_CHANNEL.toInt() and 0xff).toFloat() * amount + (secondColor shr GREEN_CHANNEL.toInt() and 0xff).toFloat() * inverseAmount).toInt() and 0xff
    val b =
        ((firstColor and 0xff).toFloat() * amount + (secondColor and 0xff).toFloat() * inverseAmount).toInt() and 0xff

//        return "#" + Integer.toHexString(intColor).substring(2)
    return a shl ALPHA_CHANNEL.toInt() or (r shl RED_CHANNEL.toInt()) or (g shl GREEN_CHANNEL.toInt()) or (b shl BLUE_CHANNEL.toInt())
}

@ColorInt
fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}