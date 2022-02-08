package com.milad.githoob.ui.profile.stared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.milad.githoob.R
import com.milad.githoob.databinding.ProfileStaredFragmentBinding
import com.milad.githoob.ui.profile.ProfileFragmentArgs

class ProfileStaredFragment : Fragment() {

    private lateinit var binding: ProfileStaredFragmentBinding

    private val safeArgs: ProfileFragmentArgs by navArgs()
    private val viewModel by viewModels<ProfileStaredViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.profile_stared_fragment, container, false)
        binding = ProfileStaredFragmentBinding.bind(view).apply {
//            viewmodel = viewModel
            lifecycleOwner = this@ProfileStaredFragment
        }


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}