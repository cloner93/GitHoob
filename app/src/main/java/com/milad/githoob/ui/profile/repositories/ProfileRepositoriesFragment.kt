package com.milad.githoob.ui.profile.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.milad.githoob.R
import com.milad.githoob.data.model.User
import com.milad.githoob.databinding.ProfileRepositoriesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileRepositoriesFragment : Fragment() {

    private var token: String? = null
    private var userId: String? = null
    private lateinit var adapter: ProfileRepositoryAdapter

    private val viewModel by viewModels<ProfileRepositoriesViewModel>()
    private val safeArgs: ProfileRepositoriesFragmentArgs by navArgs()
    private lateinit var binding: ProfileRepositoriesFragmentBinding

    fun newInstance(bundle: Bundle): ProfileRepositoriesFragment {
        val instance = ProfileRepositoriesFragment()
        instance.arguments = bundle

        return instance
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_repositories_fragment, container, false)
        binding = ProfileRepositoriesFragmentBinding.bind(view).apply {
            viewmodel = viewModel
            lifecycleOwner = this@ProfileRepositoriesFragment
        }

        token = safeArgs.token
        userId = safeArgs.userId

        setupRecyclerView()

        viewModel.setUser(token = token, userId = userId)

        return binding.root
    }

    private fun setupRecyclerView() {
        val viewmodel = binding.viewmodel
        if (viewmodel != null) {
            adapter = ProfileRepositoryAdapter(viewmodel)
            binding.profileRepositoriesList.adapter = adapter
        }
        if (token != null && token!="")
            binding.floatingActionButton.show()
        else
            binding.floatingActionButton.hide()

        binding.profileRepositoriesList.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0)
                    binding.floatingActionButton.shrink()
                else
                    binding.floatingActionButton.extend()

            }
        })
    }

}