package com.milad.githoob.ui.profile.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
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
        binding.profileRepositoriesNestedList.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
                run {
                    if (scrollY > oldScrollY) {
                        binding.floatingActionButton.shrink()
                    }
                    if (scrollY < oldScrollY) {
                        binding.floatingActionButton.extend()
                    }

//                    if (scrollY == 0) {
//                        Timber.d("TOP")
//                    }

//                    if (scrollY == -(v.measuredHeight - v.getChildAt(0).measuredHeight)) {
//                        Timber.d("Bottom $scrollY == ${v.measuredHeight - v.getChildAt(0).measuredHeight}")
//                    }
                }
            })

        viewModel.setUser(token = token, userId = userId)

        return binding.root
    }

    private fun setupRecyclerView() {
        val viewmodel = binding.viewmodel
        if (viewmodel != null) {
            adapter = ProfileRepositoryAdapter(viewmodel)
            binding.profileRepositoriesList.adapter = adapter
        }
        if (token != null && token != "")
            binding.floatingActionButton.show()
        else
            binding.floatingActionButton.hide()
    }

}