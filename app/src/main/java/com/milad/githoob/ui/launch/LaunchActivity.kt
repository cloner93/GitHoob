package com.milad.githoob.ui.launch

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.milad.githoob.R
import com.milad.githoob.utils.AppConstants.AUTH_URL
import com.milad.githoob.utils.AppConstants.CLIENT_ID
import com.milad.githoob.utils.AppConstants.REDIRECT_URI
import com.milad.githoob.utils.AppConstants.SCOPE
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class LaunchActivity : AppCompatActivity() {

//    private val mainViewModel: LaunchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        val state = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())

        val githubAuthURLFull = AUTH_URL +
                "?client_id=" + CLIENT_ID +
                "&scope=" + SCOPE +
                "&state=" + state +
                "&redirect_uri=" + REDIRECT_URI

//        github_login_btn.setOnClickListener {
//            openWebIntent(githubAuthURLFull)
//        }
    }

    private fun openWebIntent(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            addCategory(Intent.CATEGORY_BROWSABLE)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
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

//        mainViewModel.fetchToken(CLIENT_ID, CLIENT_SECRET, code).observe(this, {
//            when (it.status) {
//                Status.SUCCESS -> {
//                    Log.d(TAG, "requestForAccessToken: ${it.data?.access_token}")
//                    Toast.makeText(this@LaunchActivity, it.data?.access_token, Toast.LENGTH_LONG)
//                        .show()
//                }
//                Status.LOADING -> {
//                    Log.d(TAG, "requestForAccessToken: ${it.status}")
//                }
//                Status.ERROR -> {
//                    Log.d(TAG, "requestForAccessToken: ${it.message}")
//                }
//            }
//        })
    }

    companion object {
        private const val TAG = "MainActivity@@"
    }
}