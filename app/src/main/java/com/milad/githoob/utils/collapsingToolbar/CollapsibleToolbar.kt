package com.milad.githoob.utils.collapsingToolbar

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.motion.widget.MotionLayout
import com.google.android.material.appbar.AppBarLayout
import com.milad.githoob.R
import com.milad.githoob.utils.GlobalState.default_percent_2
import com.milad.githoob.utils.mixTwoColors

class CollapsibleToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MotionLayout(context, attrs, defStyleAttr), AppBarLayout.OnOffsetChangedListener {

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        progress = -verticalOffset / appBarLayout?.totalScrollRange?.toFloat()!!
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        (parent as? AppBarLayout)?.addOnOffsetChangedListener(this)

        context.mixTwoColors(
            R.attr.colorPrimary,
            R.attr.colorSurface,
            default_percent_2
        ).apply {
            setBackgroundColor(this)
        }
    }

    override fun onDetachedFromWindow() {
        (parent as? AppBarLayout)?.removeOnOffsetChangedListener(this)
        super.onDetachedFromWindow()
    }
}