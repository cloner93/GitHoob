package com.milad.githoob.ui.login

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.milad.githoob.R
import com.milad.common.AppConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.login_fragment.*
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val STATE = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())

        val githubAuthURLFull = AppConstants.AUTH_URL +
                "?client_id=" + AppConstants.CLIENT_ID +
                "&scope=" + AppConstants.SCOPE +
                "&state=" + STATE +
                "&redirect_uri=" + AppConstants.REDIRECT_URI

        github_login_btn.setOnClickListener {
            openWebIntent(githubAuthURLFull)
        }
    }

    private fun openWebIntent(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            addCategory(Intent.CATEGORY_BROWSABLE)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
    }

}