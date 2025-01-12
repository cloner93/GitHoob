package com.milad.githoob.utils.collapsingToolbar

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.motion.widget.MotionLayout
import com.google.android.material.appbar.AppBarLayout
import com.milad.githoob.R
import com.milad.common.GlobalState.default_percent_4
import com.milad.common.mixTwoColors

class CollapsibleToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MotionLayout(context, attrs, defStyleAttr), AppBarLayout.OnOffsetChangedListener {

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        progress = -verticalOffset / appBarLayout?.totalScrollRange?.toFloat()!!
    }

    @SuppressLint("ResourceType")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as? AppBarLayout)?.addOnOffsetChangedListener(this)

        // color should be dynamic
        context.mixTwoColors(
            com.google.android.material.R.color.design_default_color_primary,
            com.google.android.material.R.color.design_default_color_surface,
            default_percent_4
        ).apply {
            setBackgroundColor(this)
        }
    }

    override fun onDetachedFromWindow() {
        (parent as? AppBarLayout)?.removeOnOffsetChangedListener(this)
        super.onDetachedFromWindow()
    }
}