package io.shiveenp

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Instagram Oembed response
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class IgOembedResponse(
    val version: String?,
    val title: String?,
    @JsonAlias("author_name")
    val authorName: String?,
    @JsonAlias("author_url")
    val authorUrl: String?,
    @JsonAlias("author_id")
    val authorId: Long?,
    @JsonAlias("media_id")
    val mediaId: String?,
    @JsonAlias("provider_name")
    val providerName: String?,
    @JsonAlias("provider_url")
    val providerUrl: String?,
    val type: String?,
    val width: Int?,
    val height: Int?,
    val html: String,
    @JsonAlias("thumbnail_url")
    val thumbnailUrl: String?,
    @JsonAlias("thumbnail_width")
    val thumbnailWidth: String?,
    @JsonAlias("thumbnail_height")
    val thumbnailHeight: String?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class TwitterOembedResponse(
    val url: String?,
    @JsonAlias("author_name")
    val authorName: String?,
    @JsonAlias("author_url")
    val authorUrl: String?,
    @JsonAlias("provider_name")
    val providerName: String?,
    val type: String?,
    val width: Int?,
    val height: Int?,
    val html: String,
    val version: String?
)

data class YouTubeOembedResponse(
    @JsonAlias("author_name")
    val authorName: String?,
    @JsonAlias("author_url")
    val authorUrl: String?,
    @JsonAlias("provider_name")
    val providerName: String?,
    @JsonAlias("provider_url")
    val providerUrl: String?,
    val type: String?,
    val width: Int?,
    val height: Int?,
    val html: String,
    @JsonAlias("thumbnail_url")
    val thumbnailUrl: String?,
    @JsonAlias("thumbnail_width")
    val thumbnailWidth: String?,
    @JsonAlias("thumbnail_height")
    val version: String?
)