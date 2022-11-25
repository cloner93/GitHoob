package com.milad.githoob.ui.profile.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.milad.githoob.R
import com.milad.model.User
import com.milad.githoob.databinding.FragmentProfileOverviewBinding
import com.milad.githoob.ui.profile.ProfileOverviewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileOverviewFragment : Fragment() {
    private lateinit var user: User
    private lateinit var token: String
    private lateinit var binding: FragmentProfileOverviewBinding

    private val viewModel by viewModels<ProfileOverviewViewModel>()
    private lateinit var adapter: ProfileOverviewAdapter

    fun newInstance(bundle: Bundle): ProfileOverviewFragment {
        val instance = ProfileOverviewFragment()
        instance.arguments = bundle

        return instance
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_overview, container, false)
        binding = FragmentProfileOverviewBinding.bind(view).apply {
            viewmodel = viewModel
            lifecycleOwner= this@ProfileOverviewFragment
        }

        if (arguments != null) {
            user = requireArguments().getSerializable("user") as User
            token = requireArguments().getString("token") as String
        }
//        setupRecyclerView()

        viewModel.setUser(token, user)

        return binding.root
    }


    private fun setupRecyclerView() {
        val viewmodel = binding.viewmodel
        if (viewmodel!= null) {
            adapter = ProfileOverviewAdapter(viewmodel)
            binding.profileAllActivitesRecyclerview?.adapter = adapter
        }
    }


}