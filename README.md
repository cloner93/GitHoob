![cover_small](https://user-images.githubusercontent.com/14924296/189586322-7a521867-3567-4ab4-99e2-073ef1384bdf.png)

## GitHoob

Githoob is alternative android client of [GitHub](https://www.github.com).

GitHoob works by [GitHub Api](https://docs.github.com/en/rest) and login with [GitHub OAuth](https://docs.github.com/en/developers/apps/building-oauth-apps/authorizing-oauth-apps).

## Architecture

The architecture is built around [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/) and follows the recommendations laid out in the [Guide to App Architecture](https://developer.android.com/jetpack/docs/guide). Logic is kept away from Activities and Fragments and moved to [ViewModels](https://developer.android.com/topic/libraries/architecture/viewmodel). Data is observed using [Kotlin Flows](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) and the [Data Binding Library](https://developer.android.com/topic/libraries/data-binding/) binds UI components in layouts to the app's data sources.
The [Navigation component](https://developer.android.com/guide/navigation) is used to implement navigation in the app, handling Fragment transactions and providing a consistent user experience.

<!-- 1. i most create Architect graph with image and desc -->

## Features

- [x] login with github [OAuth](https://docs.github.com/en/developers/apps/building-oauth-apps/authorizing-oauth-apps)
- [x] handle github link with [deeplink](https://developer.android.com/training/app-links)
- [x] show all details of user profile, repository, connections and etc.
- [ ] add unit, integration and E2E test. 
- [ ] edit profile
- [ ] add, edit and delete repository
- [ ] set app online-first in v3.0.
- [ ] replce xml with [jetpack compose](https://developer.android.com/jetpack/compose)

<!-- ### MAD Score -->

## Contributions

If you've found an error in the project, please file an issue.

Patches are encouraged and may be submitted by forking this project and submitting a pull request. Since this project is still in its very early stages, if your change is substantial, please raise an issue first to discuss it.


### License

    MIT License

    Copyright (c) 2022 cloner93

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
