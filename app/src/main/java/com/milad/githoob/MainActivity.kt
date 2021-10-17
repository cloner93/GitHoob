package com.milad.githoob

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.milad.githoob.GithubConstants.AUTH_URL
import com.milad.githoob.GithubConstants.CLIENT_ID
import com.milad.githoob.GithubConstants.REDIRECT_URI
import com.milad.githoob.GithubConstants.SCOPE
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val state = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())

        val githubAuthURLFull = AUTH_URL +
                "?client_id=" + CLIENT_ID +
                "&scope=" + SCOPE +
                "&state=" + state +
                "&redirect_uri=" + REDIRECT_URI

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

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val uri = intent?.data
        if (uri != null && uri.toString().startsWith(REDIRECT_URI)) {
            val code = uri.getQueryParameter("code")
            requestForAccessToken(code!!)
        }
    }

    private fun requestForAccessToken(code: String) {

        Retrofit.Builder()
            .baseUrl("https://github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(GithubClient::class.java).getAccessToken(
                clientId = GithubConstants.CLIENT_ID,
                clientSecret = GithubConstants.CLIENT_SECRET,
                code = code
            ).enqueue(object : Callback<AccessToken> {
                override fun onResponse(call: Call<AccessToken>, response: Response<AccessToken>) {
                    Log.d(TAG, "onResponse: ${response.body()?.access_token}")
                }

                override fun onFailure(call: Call<AccessToken>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}t")
                }
            })

    }

    companion object {
        private const val TAG = "MainActivity@@"
    }
}