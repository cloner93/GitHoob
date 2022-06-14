package com.milad.githoob.ui.profile.connection

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ConnectionViewPagerAdapter(
    fragment: Fragment,
    val token: String,
    val userId: String
) :
    FragmentStateAdapter(fragment) {

    val list: List<Fragment> = listOf(
        ConnectionListFragment(token = token, userId = userId, type = ConnectionType.followers),
        ConnectionListFragment(token = token, userId = userId, type = ConnectionType.following)
    )

    override fun getItemCount() = list.size

    override fun createFragment(position: Int) =
        when (position) {
            0 -> list[position]
            1 -> list[position]
            else -> throw IllegalArgumentException()
        }

}
