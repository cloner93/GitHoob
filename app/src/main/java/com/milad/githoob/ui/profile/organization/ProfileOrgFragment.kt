package com.milad.githoob.ui.profile.organization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.milad.githoob.R
import com.milad.githoob.databinding.ProfileOrgFragmentBinding
import com.milad.githoob.ui.profile.stared.ProfileStaredAdapter

class ProfileOrgFragment : Fragment() {

    private lateinit var binding: ProfileOrgFragmentBinding
    private lateinit var adapter: ProfileOrgAdapter

    private val safeArgs: ProfileOrgFragmentArgs by navArgs()
    private val viewModel by viewModels<ProfileOrgViewModel>()

    private var token: String? = null
    private var userId: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_org_fragment, container, false)
        binding = ProfileOrgFragmentBinding.bind(view).apply {
            viewmodel = viewModel
            lifecycleOwner = this@ProfileOrgFragment
        }

        token = safeArgs.token
        userId = safeArgs.userId

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        setupRecyclerView()

        viewModel.setUser(token = token, userId = userId)

        return binding.root
    }


    private fun setupRecyclerView() {
        val viewmodel = binding.viewmodel
        if (viewmodel != null) {
            adapter = ProfileOrgAdapter(viewmodel)
            binding.profileStaredList.adapter = adapter
        }
    }
}