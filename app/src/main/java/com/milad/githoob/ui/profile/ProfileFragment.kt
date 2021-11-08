package com.milad.githoob.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
                    binding.profilePager.adapter = viewPagerAdapter

                    TabLayoutMediator(
                        binding.profileTabLayout,
                        binding.profilePager
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

        return binding.root
    }
}
