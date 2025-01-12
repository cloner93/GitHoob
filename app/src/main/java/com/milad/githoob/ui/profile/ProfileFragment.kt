package com.milad.githoob.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.milad.githoob.R
import com.milad.githoob.databinding.FragmentProfileBinding
import com.milad.common.InternalDeepLink
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var token: String
    private lateinit var userId: String
    private lateinit var binding: FragmentProfileBinding
    private val safeArgs: ProfileFragmentArgs by navArgs()
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        binding = FragmentProfileBinding.bind(view).apply {
            viewmodel = viewModel
            lifecycleOwner = this@ProfileFragment
        }

        token = safeArgs.token
        userId = safeArgs.userId

        viewModel.setUser(token, userId)
        viewModel.user.observe(viewLifecycleOwner) {
            it.let {
                if (it != null) {
                    userId = it.login
                }
            }
        }

        val navigate = NavHostFragment.findNavController(this)

        initNavigation(navigate)

        binding.backBtnOnProjectCollapsed.setOnClickListener(this);
        binding.backBtnOnProjectExtended.setOnClickListener(this);

        return binding.root
    }

    private fun initNavigation(navigate: NavController) {

        binding.repoBtn.setOnClickListener {
            val destination =
                InternalDeepLink.makeRepositoryUserDeepLink(userId = userId, token = token)
            navigate.navigate(destination)
        }

        binding.starredBtn.setOnClickListener {
            val destination =
                InternalDeepLink.makeStarredUserDeepLink(userId = userId, token = token)
            navigate.navigate(destination)
        }

        binding.orgBtn.setOnClickListener {
            val destination =
                InternalDeepLink.makeOrganizationDeepLink(userId = userId, token = token)
            navigate.navigate(destination)
        }

        binding.profileBtnConnection.setOnClickListener {
            val destination =
                InternalDeepLink.makeConnectionDeepLink(userId = userId, token = token)
            navigate.navigate(destination)
        }
    }

    override fun onClick(view: View?) {
        findNavController().popBackStack()
    }

}
