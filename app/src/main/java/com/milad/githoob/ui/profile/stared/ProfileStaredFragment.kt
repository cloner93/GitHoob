package com.milad.githoob.ui.profile.stared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.milad.githoob.R
import com.milad.githoob.databinding.ProfileStaredFragmentBinding
import com.milad.githoob.ui.profile.repositories.ProfileRepositoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileStaredFragment : Fragment() {

    private lateinit var binding: ProfileStaredFragmentBinding
    private lateinit var adapter: ProfileStaredAdapter

    private val safeArgs: ProfileStaredFragmentArgs by navArgs()
    private val viewModel by viewModels<ProfileStaredViewModel>()

    private var token: String? = null
    private var userId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.profile_stared_fragment, container, false)
        binding = ProfileStaredFragmentBinding.bind(view).apply {
            viewmodel = viewModel
            lifecycleOwner = this@ProfileStaredFragment
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
            adapter = ProfileStaredAdapter(viewmodel)
            binding.profileStaredList.adapter = adapter
        }
    }

}