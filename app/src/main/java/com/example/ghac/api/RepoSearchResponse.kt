package com.example.ghac.api

import com.google.gson.annotations.SerializedName

data class UserSearchResponse(
    @SerializedName("total_count") val total_count: Int = 0,
    @SerializedName("incomplete_results") val incomplete_results: Boolean = false,
    @SerializedName("items") val items: List<UserItem> = emptyList(),
    val nextPage: Int? = null
)

data class UserItem(
    @field:SerializedName("login") val login: String,
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("avatar_url") val avatar_url: String
)

data class User(
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("login") val login: String,
    @field:SerializedName("node_id") val node_id: String,
    @field:SerializedName("avatar_url") val avatar_url: String,
    @field:SerializedName("gravatar_id") val gravatar_id: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("html_url") val html_url: String,
    @field:SerializedName("followers_url") val followers_url: String,
    @field:SerializedName("following_url") val following_url: String,
    @field:SerializedName("gists_url") val gists_url: String,
    @field:SerializedName("starred_url") val starred_url: String,
    @field:SerializedName("subscriptions_url") val subscriptions_url: String,
    @field:SerializedName("organizations_url") val organizations_url: String,
    @field:SerializedName("repos_url") val repos_url: String,
    @field:SerializedName("events_url") val events_url: String,
    @field:SerializedName("received_events_url") val received_events_url: String,
    @field:SerializedName("type") val type: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("company") val company: String,
    @field:SerializedName("email") val email: String,
    @field:SerializedName("followers") val followers: Long,
    @field:SerializedName("following") val following: Long,
)

data class Repo(
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("full_name") val fullName: String,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("html_url") val url: String,
    @field:SerializedName("stargazers_count") val stars: Int,
    @field:SerializedName("forks_count") val forks: Int,
    @field:SerializedName("language") val language: String?,
    @field:SerializedName("owner") val owner: Owner
)

data class Owner(
    @field:SerializedName("login") val login: String,
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("avatar_url") val avatar_url: String
)
