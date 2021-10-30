package com.milad.githoob.ui.splash

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.milad.githoob.R
import com.milad.githoob.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel by viewModels<SplashViewModel>()
    private val TAG = "SplashFragment@@"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        binding = FragmentSplashBinding.bind(view)

        lifecycleScope.launch {
            viewModel.checkUserAuth().collect {
                withContext(Dispatchers.Main) {
                    if (it.equals("")) {
                        Log.d(TAG, "not found!")
                        delay(3000)
                        binding.progressBar.visibility = View.GONE

                        NavHostFragment.findNavController(this@SplashFragment)
                            .navigate(R.id.action_splashFragment_to_loginFragment)
                        this.cancel()

                    } else {
                        Log.d(TAG, "Founded: $it")
                        val directions =
                            SplashFragmentDirections.actionSplashFragmentToProfileFragment(it)
                        NavHostFragment.findNavController(this@SplashFragment)
                            .navigate(directions)
                        this.cancel()
                    }
                }
            }
        }

        return binding.root
    }
}