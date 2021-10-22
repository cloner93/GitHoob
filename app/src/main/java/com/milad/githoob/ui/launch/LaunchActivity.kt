package com.milad.githoob.ui.launch

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.milad.githoob.R
import com.milad.githoob.utils.AppConstants.CLIENT_ID
import com.milad.githoob.utils.AppConstants.CLIENT_SECRET
import com.milad.githoob.utils.AppConstants.REDIRECT_URI
import com.milad.githoob.utils.Status
import dagger.hilt.android.AndroidEntryPoint

import androidx.navigation.NavController
import androidx.navigation.Navigation


@AndroidEntryPoint
class LaunchActivity : AppCompatActivity() {

    private val mainViewModel: LaunchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val uri = intent?.data
        if (uri != null && uri.toString().startsWith(REDIRECT_URI)) {
            val code = uri.getQueryParameter("code")
            requestForAccessToken(code!!)
        }
    }

    private fun requestForAccessToken(code: String) {
        mainViewModel.fetchToken(CLIENT_ID, CLIENT_SECRET, code).observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d(TAG, "requestForAccessToken: ${it.data?.access_token}")

                    mainViewModel.saveToken(it.data!!.access_token)

//                    NavHostFragment.findNavController().navigate(R.id.profileFragment)

                    val navController: NavController =
                        Navigation.findNavController(
                            this@LaunchActivity,
                            R.id.fragmentContainerView
                        )
                    navController.navigate(R.id.profileFragment)
                }
                Status.LOADING -> {
                    Log.d(TAG, "requestForAccessToken: ${it.status}")
                }
                Status.ERROR -> {
                    Log.d(TAG, "requestForAccessToken: ${it.message}")
                }
            }
        })
    }

    companion object {
        private const val TAG = "MainActivity@@"
    }
}