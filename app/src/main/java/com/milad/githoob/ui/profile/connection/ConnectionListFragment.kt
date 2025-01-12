package com.milad.githoob.ui.profile.connection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.milad.githoob.R
import com.milad.githoob.databinding.FragmentConnectionListBinding
import com.milad.common.InternalDeepLink
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConnectionListFragment(
    val userId: String = "",
    val token: String = "",
    val type: ConnectionType
) : Fragment() {

    private val viewModel by viewModels<ConnectionViewModel>()

    private lateinit var binding: FragmentConnectionListBinding
    private lateinit var adapter: ConnectionsRecyclerviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_connection_list, container, false)

        binding = FragmentConnectionListBinding.bind(view).apply {
            viewmodel = viewModel
            lifecycleOwner = this@ConnectionListFragment
        }

        setupRecyclerView()

        viewModel.setUser(token = token, userId = userId, type = type.name)

        return view
    }

    private fun setupRecyclerView() {
        val viewmodel = binding.viewmodel
        if (viewmodel != null) {
            adapter = ConnectionsRecyclerviewAdapter(viewmodel) {
                it.let {
                    val destination =
                        InternalDeepLink.makeProfileDeepLink(userId = it.login)
                    findNavController().navigate(destination)
                }
            }
            binding.connectionList.adapter = adapter
        }
    }
}