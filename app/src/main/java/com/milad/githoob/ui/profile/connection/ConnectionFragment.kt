package com.milad.githoob.ui.profile.connection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.milad.githoob.R
import com.milad.githoob.databinding.FragmentConnectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConnectionFragment : Fragment() {

    private lateinit var binding: FragmentConnectionBinding

    private val safeArgs: ConnectionFragmentArgs by navArgs()
    private val viewModel by viewModels<ConnectionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_connection, container, false)
        binding = FragmentConnectionBinding.bind(view).apply {
            viewmodel = viewModel
            lifecycleOwner = this@ConnectionFragment
        }

        val adapter = ConnectionViewPagerAdapter(
            this,
            safeArgs.token,
            safeArgs.userId
        )

        binding.connectionViewpager.adapter = adapter

        TabLayoutMediator(
            binding.connectionTablayout,
            binding.connectionViewpager
        ) { tab: TabLayout.Tab, i: Int ->
            when (i) {
                0 -> {
                    tab.text = "Followers"
                }
                1 -> {
                    tab.text = "Following"
                }
            }
        }.attach()

        return binding.root
    }
}

enum class ConnectionType {
    followers, following
}