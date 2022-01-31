package com.milad.githoob.ui.profile.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.milad.githoob.R
import com.milad.githoob.data.model.User
import com.milad.githoob.databinding.ProfileRepositoriesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileRepositoriesFragment : Fragment() {

    private lateinit var user: User
    private lateinit var token: String
    private lateinit var adapter: ProfileRepositoryAdapter

    fun newInstance(bundle: Bundle): ProfileRepositoriesFragment {
        val instance = ProfileRepositoriesFragment()
        instance.arguments = bundle

        return instance
    }

    private val viewModel by viewModels<ProfileRepositoriesViewModel>()

    private lateinit var binding: ProfileRepositoriesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_repositories_fragment, container, false)
        binding = ProfileRepositoriesFragmentBinding.bind(view).apply {
            viewmodel = viewModel
            lifecycleOwner = this@ProfileRepositoriesFragment
        }

        if (arguments != null) {
//            user = requireArguments().getSerializable("user") as User
            token = requireArguments().getString("token") as String
        }
        setupRecyclerView()

        viewModel.setUser(token = token, null)

        return binding.root
    }

    private fun setupRecyclerView() {
        val viewmodel = binding.viewmodel
        if (viewmodel != null) {
            adapter = ProfileRepositoryAdapter(viewmodel)
            binding.profileRepositoriesList.adapter = adapter
        }
    }

}