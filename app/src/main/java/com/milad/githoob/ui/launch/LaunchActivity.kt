package com.milad.githoob.ui.launch

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.milad.githoob.R
import com.milad.githoob.utils.AppConstants.CLIENT_ID
import com.milad.githoob.utils.AppConstants.CLIENT_SECRET
import com.milad.githoob.utils.AppConstants.REDIRECT_URI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchActivity : AppCompatActivity() {

    private val mainViewModel: LaunchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        onNewIntent(intent);
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
        mainViewModel.fetchToken(CLIENT_ID, CLIENT_SECRET, code)

        mainViewModel.token.observe(this) {
            val navController: NavController =
                Navigation.findNavController(
                    this@LaunchActivity,
                    R.id.fragmentContainerView
                )
            val args = Bundle()
            args.putString("token", it)
            navController.navigate(R.id.profileFragment, args)
        }
    }
}