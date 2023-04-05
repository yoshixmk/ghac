package com.example.ghac.api

import okhttp3.Interceptor
import okhttp3.Response

class GitHubAuthorizationInterceptor : Interceptor {

    var token: String = Constants.GITHUB_PAT

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (request.header("No-Authentication") == null) {
            if (!token.isEmpty()) {
                val finalToken = "Bearer $token"
                request = request.newBuilder()
                    .addHeader("Authorization", finalToken)
                    .build()
            }

        }

        return chain.proceed(request)
    }
}
