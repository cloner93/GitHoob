package com.milad.githoob.ui.profile.repositories.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.milad.githoob.R
import com.milad.githoob.databinding.ProfileProjectFragmentBinding
import com.milad.githoob.ui.profile.repositories.ProfileProjectContributorsAdapter
import com.milad.githoob.ui.profile.repositories.ProfileRepositoriesViewModel
import com.milad.githoob.ui.profile.repositories.ProfileRepositoryAdapter
import com.milad.githoob.utils.InternalDeepLink
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileProjectFragment : Fragment() {
    private var token: String? = null
    private var userId: String? = null
    private lateinit var projectName: String
    private lateinit var adapter: ProfileProjectContributorsAdapter

    private val viewModel by viewModels<ProfileProjectViewModel>()
    private val safeArgs: ProfileProjectFragmentArgs by navArgs()
    private lateinit var binding: ProfileProjectFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.profile_project_fragment, container, false)
        binding = ProfileProjectFragmentBinding.bind(view).apply {
            viewmodel = viewModel
            lifecycleOwner = this@ProfileProjectFragment
        }

        token = safeArgs.token
        userId = safeArgs.userId
        projectName = safeArgs.projectName

        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        val viewmodel = binding.viewmodel
        if (viewmodel != null) {
            adapter = ProfileProjectContributorsAdapter(viewmodel)
            binding.profileContributorList.adapter = adapter
        }
    }

}