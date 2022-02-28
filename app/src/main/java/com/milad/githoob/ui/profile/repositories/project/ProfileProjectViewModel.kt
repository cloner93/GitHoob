package com.milad.githoob.ui.profile.repositories.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.google.gson.Gson
import com.milad.githoob.data.model.event.Contributor
import com.milad.githoob.data.model.event.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileProjectViewModel @Inject constructor(
) : ViewModel() {
    private val _repo = MutableLiveData<Repo>()
    val repo: LiveData<Repo> = _repo
    private val _contributors = MutableLiveData<List<Contributor>>()
    val contributors: LiveData<List<Contributor>> = _contributors

    val s =
        "{\"id\":1725199,\"node_id\":\"MDEwOlJlcG9zaXRvcnkxNzI1MTk5\",\"name\":\"linguist\",\"full_name\":\"github/linguist\",\"private\":false,\"owner\":{\"login\":\"github\",\"id\":9919,\"node_id\":\"MDEyOk9yZ2FuaXphdGlvbjk5MTk=\",\"avatar_url\":\"https://avatars.githubusercontent.com/u/9919?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/github\",\"html_url\":\"https://github.com/github\",\"followers_url\":\"https://api.github.com/users/github/followers\",\"following_url\":\"https://api.github.com/users/github/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/github/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/github/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/github/subscriptions\",\"organizations_url\":\"https://api.github.com/users/github/orgs\",\"repos_url\":\"https://api.github.com/users/github/repos\",\"events_url\":\"https://api.github.com/users/github/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/github/received_events\",\"type\":\"Organization\",\"site_admin\":false},\"html_url\":\"https://github.com/github/linguist\",\"description\":\"Language Savant. If your repository's language is being reported incorrectly, send us a pull request!\",\"fork\":false,\"url\":\"https://api.github.com/repos/github/linguist\",\"forks_url\":\"https://api.github.com/repos/github/linguist/forks\",\"keys_url\":\"https://api.github.com/repos/github/linguist/keys{/key_id}\",\"collaborators_url\":\"https://api.github.com/repos/github/linguist/collaborators{/collaborator}\",\"teams_url\":\"https://api.github.com/repos/github/linguist/teams\",\"hooks_url\":\"https://api.github.com/repos/github/linguist/hooks\",\"issue_events_url\":\"https://api.github.com/repos/github/linguist/issues/events{/number}\",\"events_url\":\"https://api.github.com/repos/github/linguist/events\",\"assignees_url\":\"https://api.github.com/repos/github/linguist/assignees{/user}\",\"branches_url\":\"https://api.github.com/repos/github/linguist/branches{/branch}\",\"tags_url\":\"https://api.github.com/repos/github/linguist/tags\",\"blobs_url\":\"https://api.github.com/repos/github/linguist/git/blobs{/sha}\",\"git_tags_url\":\"https://api.github.com/repos/github/linguist/git/tags{/sha}\",\"git_refs_url\":\"https://api.github.com/repos/github/linguist/git/refs{/sha}\",\"trees_url\":\"https://api.github.com/repos/github/linguist/git/trees{/sha}\",\"statuses_url\":\"https://api.github.com/repos/github/linguist/statuses/{sha}\",\"languages_url\":\"https://api.github.com/repos/github/linguist/languages\",\"stargazers_url\":\"https://api.github.com/repos/github/linguist/stargazers\",\"contributors_url\":\"https://api.github.com/repos/github/linguist/contributors\",\"subscribers_url\":\"https://api.github.com/repos/github/linguist/subscribers\",\"subscription_url\":\"https://api.github.com/repos/github/linguist/subscription\",\"commits_url\":\"https://api.github.com/repos/github/linguist/commits{/sha}\",\"git_commits_url\":\"https://api.github.com/repos/github/linguist/git/commits{/sha}\",\"comments_url\":\"https://api.github.com/repos/github/linguist/comments{/number}\",\"issue_comment_url\":\"https://api.github.com/repos/github/linguist/issues/comments{/number}\",\"contents_url\":\"https://api.github.com/repos/github/linguist/contents/{+path}\",\"compare_url\":\"https://api.github.com/repos/github/linguist/compare/{base}...{head}\",\"merges_url\":\"https://api.github.com/repos/github/linguist/merges\",\"archive_url\":\"https://api.github.com/repos/github/linguist/{archive_format}{/ref}\",\"downloads_url\":\"https://api.github.com/repos/github/linguist/downloads\",\"issues_url\":\"https://api.github.com/repos/github/linguist/issues{/number}\",\"pulls_url\":\"https://api.github.com/repos/github/linguist/pulls{/number}\",\"milestones_url\":\"https://api.github.com/repos/github/linguist/milestones{/number}\",\"notifications_url\":\"https://api.github.com/repos/github/linguist/notifications{?since,all,participating}\",\"labels_url\":\"https://api.github.com/repos/github/linguist/labels{/name}\",\"releases_url\":\"https://api.github.com/repos/github/linguist/releases{/id}\",\"deployments_url\":\"https://api.github.com/repos/github/linguist/deployments\",\"created_at\":\"2011-05-09T22:53:13Z\",\"updated_at\":\"2022-02-27T07:10:02Z\",\"pushed_at\":\"2022-02-25T19:38:30Z\",\"git_url\":\"git://github.com/github/linguist.git\",\"ssh_url\":\"git@github.com:github/linguist.git\",\"clone_url\":\"https://github.com/github/linguist.git\",\"svn_url\":\"https://github.com/github/linguist\",\"homepage\":\"\",\"size\":38187,\"stargazers_count\":9565,\"watchers_count\":9565,\"language\":\"Ruby\",\"has_issues\":true,\"has_projects\":false,\"has_downloads\":true,\"has_wiki\":false,\"has_pages\":false,\"forks_count\":3457,\"mirror_url\":null,\"archived\":false,\"disabled\":false,\"open_issues_count\":101,\"license\":{\"key\":\"mit\",\"name\":\"MIT License\",\"spdx_id\":\"MIT\",\"url\":\"https://api.github.com/licenses/mit\",\"node_id\":\"MDc6TGljZW5zZTEz\"},\"allow_forking\":true,\"is_template\":false,\"topics\":[\"language-grammars\",\"language-statistics\",\"linguistic\",\"syntax-highlighting\"],\"visibility\":\"public\",\"forks\":3457,\"open_issues\":101,\"watchers\":9565,\"default_branch\":\"master\",\"permissions\":{\"admin\":false,\"maintain\":false,\"push\":false,\"triage\":false,\"pull\":true},\"temp_clone_token\":\"\",\"organization\":{\"login\":\"github\",\"id\":9919,\"node_id\":\"MDEyOk9yZ2FuaXphdGlvbjk5MTk=\",\"avatar_url\":\"https://avatars.githubusercontent.com/u/9919?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/github\",\"html_url\":\"https://github.com/github\",\"followers_url\":\"https://api.github.com/users/github/followers\",\"following_url\":\"https://api.github.com/users/github/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/github/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/github/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/github/subscriptions\",\"organizations_url\":\"https://api.github.com/users/github/orgs\",\"repos_url\":\"https://api.github.com/users/github/repos\",\"events_url\":\"https://api.github.com/users/github/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/github/received_events\",\"type\":\"Organization\",\"site_admin\":false},\"network_count\":3457,\"subscribers_count\":423}"
    val ss =
        "{\"login\":\"arfon\",\"id\":4483,\"node_id\":\"MDQ6VXNlcjQ0ODM=\",\"avatar_url\":\"https://avatars.githubusercontent.com/u/4483?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/arfon\",\"html_url\":\"https://github.com/arfon\",\"followers_url\":\"https://api.github.com/users/arfon/followers\",\"following_url\":\"https://api.github.com/users/arfon/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/arfon/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/arfon/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/arfon/subscriptions\",\"organizations_url\":\"https://api.github.com/users/arfon/orgs\",\"repos_url\":\"https://api.github.com/users/arfon/repos\",\"events_url\":\"https://api.github.com/users/arfon/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/arfon/received_events\",\"type\":\"User\",\"site_admin\":true,\"contributions\":1424}"

    init {
        val gg = Gson()
        _repo.value = gg.fromJson(s, Repo::class.java)

        val element = gg.fromJson(ss, Contributor::class.java)
        _contributors.value = listOf(
            element,
            element,
            element,
            element,
            element,
            element,
            element,
            element,
            element,
            element,
            element,
            element,
            element,
            element,
            element
        )
    }
}