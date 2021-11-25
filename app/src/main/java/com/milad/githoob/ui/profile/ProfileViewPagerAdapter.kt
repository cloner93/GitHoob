package com.milad.githoob.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.milad.githoob.ui.profile.feed.ProfileFeedsFragment
import com.milad.githoob.ui.profile.overview.ProfileOverviewFragment
import com.milad.githoob.ui.profile.repositories.ProfileRepositoriesFragment

class ProfileViewPagerAdapter(val fragment: Fragment, val bundle: Bundle) :
    FragmentStateAdapter(fragment) {

    val list: List<Fragment> = listOf(
        ProfileOverviewFragment().newInstance(bundle),
        ProfileFeedsFragment().newInstance(bundle),
        ProfileRepositoriesFragment().newInstance(bundle)
    )

    override fun getItemCount() = list.size

    override fun createFragment(position: Int) = when (position) {
        0 -> list[position]
        1 -> list[position]
        2 -> list[position]
        else -> throw IllegalArgumentException()
    }
}