package com.milad.githoob.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.milad.githoob.ui.profile.overview.ProfileOverviewFragment

class ProfileViewPagerAdapter(
    val fragment: Fragment,
    val bundle: Bundle
) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        return ProfileOverviewFragment().newInstance(bundle)
    }
}