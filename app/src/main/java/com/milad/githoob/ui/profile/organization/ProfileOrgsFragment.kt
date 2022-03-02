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
import com.milad.githoob.databinding.ProfileOrgsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileOrgsFragment : Fragment() {

    private lateinit var binding: ProfileOrgsFragmentBinding
    private lateinit var adapter: ProfileOrgsAdapter

    private val safeArgs: ProfileOrgsFragmentArgs by navArgs()
    private val viewModel by viewModels<ProfileOrgsViewModel>()

    private lateinit var token: String
    private lateinit var userId: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_orgs_fragment, container, false)
        binding = ProfileOrgsFragmentBinding.bind(view).apply {
            viewmodel = viewModel
            lifecycleOwner = this@ProfileOrgsFragment
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
            adapter = ProfileOrgsAdapter(viewmodel)
            binding.profileOrgsList.adapter = adapter
        }
    }
}