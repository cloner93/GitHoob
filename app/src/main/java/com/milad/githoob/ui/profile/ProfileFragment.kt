package com.milad.githoob.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.milad.githoob.R
import com.milad.githoob.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val TAG = "ProfileFragment@@"
    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModels<ProfileViewModel>()
    private lateinit var viewPagerAdapter: ProfileViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        binding = FragmentProfileBinding.bind(view).apply {
            viewmodel = viewModel
            lifecycleOwner = this@ProfileFragment
        }
        val bundle = arguments
        if (bundle != null) {
            viewModel.setToken(bundle.getString("token", ""))
            viewModel.user.observe(viewLifecycleOwner, { user ->
                if (user != null) {
                    val data = Bundle()
                    data.putString("token", bundle.getString("token", ""))
                    data.putSerializable("user", user)

                    viewPagerAdapter = ProfileViewPagerAdapter(this, data)
                    binding.profileInclude.profilePager.adapter = viewPagerAdapter

                    TabLayoutMediator(
                        binding.profileInclude.profileTabLayout,
                        binding.profileInclude.profilePager
                    ) { tab, position ->
                        tab.text = when (position) {
                            0 -> {
                                "OverView"
                            }
                            1 -> {
                                "Repositories"
                            }
                            2 -> {
                                "Projects"
                            }
                            3 -> {
                                "Package"
                            }
                            else -> "N/A"
                        }
                    }.attach()

                }
            })
        }
        coordinateMotion()


        return binding.root
    }

    private fun coordinateMotion() {
        val appBarLayout: AppBarLayout = binding.appbarLayout
        val motionLayout: MotionLayout = binding.profileHeaderInfo

        val listener = AppBarLayout.OnOffsetChangedListener { unused, verticalOffset ->
            val seekPosition = -verticalOffset / appBarLayout.totalScrollRange.toFloat()
            motionLayout.progress = seekPosition
            Log.d(TAG, "coordinateMotion: $seekPosition")
        }

        appBarLayout.addOnOffsetChangedListener(listener)
    }
}
