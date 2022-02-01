package com.milad.githoob.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.milad.githoob.R
import com.milad.githoob.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var safeArgs: ProfileFragmentArgs
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
        val bundle = arguments
        if (bundle != null) {
            safeArgs = ProfileFragmentArgs.fromBundle(bundle)

            val token = safeArgs.token
            val userId = safeArgs.userId

            Timber.d("Token: $token \n UserId: $userId")
            viewModel.setUser(token, userId)
        }

        return binding.root
    }

}
