package com.milad.githoob.ui.profile.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.milad.githoob.R
import com.milad.githoob.data.model.User
import com.milad.githoob.databinding.ProfileFeedsFragmentBinding
import com.milad.githoob.ui.profile.repositories.ProfileRepositoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFeedsFragment : Fragment() {

    private lateinit var user: User
    private lateinit var token: String
    private lateinit var adapter: ProfileFeedsAdapter

    private val viewModel by viewModels<ProfileFeedsViewModel>()
    private lateinit var binding: ProfileFeedsFragmentBinding

    fun newInstance(bundle: Bundle): ProfileFeedsFragment {
        val instance = ProfileFeedsFragment()
        instance.arguments = bundle

        return instance
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_feeds_fragment, container, false)
        binding = ProfileFeedsFragmentBinding.bind(view).apply {
            viewmodel = viewModel
            lifecycleOwner = this@ProfileFeedsFragment
        }

        if (arguments != null) {
            user = requireArguments().getSerializable("user") as User
            token = requireArguments().getString("token") as String
        }
        setupRecyclerView()

        viewModel.setUser(token, user)

        return binding.root
    }

    private fun setupRecyclerView() {
        val viewmodel = binding.viewmodel
        if (viewmodel != null) {
            adapter = ProfileFeedsAdapter(viewmodel)
            binding.profileRepositoriesList.adapter = adapter
        }
    }

}