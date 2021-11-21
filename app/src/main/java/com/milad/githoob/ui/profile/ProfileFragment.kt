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
import java.text.DecimalFormat

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
                    binding.profileInclude?.profilePager?.adapter = viewPagerAdapter

                    binding.profileInclude?.let {
                        TabLayoutMediator(
                            it.profileTabLayout,
                            binding.profileInclude!!.profilePager
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

                }
            })
        }
        coordinateMotion()


        return binding.root
    }

    private fun coordinateMotion() {
        val appBarLayout: AppBarLayout? = binding.appbarLayout
        val motionLayout: MotionLayout = binding.profileHeaderInfo as MotionLayout

        val p = DecimalFormat("0.0");

        val listener = AppBarLayout.OnOffsetChangedListener { unused, verticalOffset ->
            Log.d(TAG, "coordinateMotion: $verticalOffset / ${appBarLayout?.totalScrollRange!!.toFloat()}")
            Log.d(TAG, "coordinateMotion: ${appBarLayout.height } : ${appBarLayout.width}")

            val seekPosition = -verticalOffset / appBarLayout?.totalScrollRange!!.toFloat()
            Log.d(TAG, "coordinateMotion: $seekPosition")
                motionLayout.progress = seekPosition
        }

        appBarLayout?.addOnOffsetChangedListener(listener)
    }
}
