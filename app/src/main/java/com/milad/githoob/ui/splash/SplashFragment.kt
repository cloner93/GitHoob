package com.milad.githoob.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.milad.githoob.R
import com.milad.githoob.databinding.FragmentSplashBinding
import com.milad.githoob.utils.InternalDeepLink
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        binding = FragmentSplashBinding.bind(view)

        lifecycleScope.launch {
            viewModel.checkUserAuth().collectLatest {
                withContext(Dispatchers.Main) {
                    if (it.equals("")) {
                        delay(3000)
                        binding.progressBar.visibility = View.GONE

                        NavHostFragment.findNavController(this@SplashFragment)
                            .navigate(R.id.action_splashFragment_to_loginFragment)
                        this.cancel()

                    } else {
                        val destination =
                            InternalDeepLink.makeProfileDeepLink(token = it)
                        NavHostFragment.findNavController(this@SplashFragment)
                            .navigate(destination)
                        this.cancel()
                    }
                }
            }
        }

        return binding.root
    }
}