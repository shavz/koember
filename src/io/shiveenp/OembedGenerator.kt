package io.shiveenp

import org.http4k.client.JavaHttpClient
import org.http4k.core.Body
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.format.Jackson.auto

val client: HttpHandler = JavaHttpClient()

fun processOembedRequest(link: String): OembedGeneratorResponse {
    val type = extractOembedTypeFromLink(link)
    return when (type) {
        OembedType.INSTAGRAM -> OembedGeneratorResponse(getIgOembedData(link), OembedType.INSTAGRAM)
        OembedType.TWITTER -> OembedGeneratorResponse(getTwitterOembedData(link), OembedType.TWITTER)
        OembedType.YOUTUBE -> OembedGeneratorResponse(getYoutubeOembedData(link), OembedType.YOUTUBE)
        OembedType.UNSUPPORTED -> OembedGeneratorResponse("Not Supported Yet...", OembedType.UNSUPPORTED)
        else -> OembedGeneratorResponse("Not Supported Yet...", OembedType.UNSUPPORTED)
    }
}

fun getIgOembedData(link: String): String {
    val request = Request(Method.GET, "https://api.instagram.com/oembed/?url=$link")

    return try {
        val igLens = Body.auto<IgOembedResponse>().toLens()
        igLens.extract(client(request)).html
    } catch (e: Exception) {
        throw DataRetrievalException("Unable to extract oembed data for the given instagram link: $link")
    }
}

fun getTwitterOembedData(link: String): String {
    val request = Request(Method.GET, "https://publish.twitter.com/oembed?url=$link")

    return try {
        val twitterLens = Body.auto<TwitterOembedResponse>().toLens()
        twitterLens.extract(client(request)).html
    } catch (e: Exception) {
        throw DataRetrievalException("Unable to extract oembed data for the given twitter link: $link")
    }
}

fun getYoutubeOembedData(link: String): String{
    val request = Request(Method.GET, "https://www.youtube.com/oembed?url=$link")

    return try {
        val youtubeLens = Body.auto<YouTubeOembedResponse>().toLens()
        youtubeLens.extract(client(request)).html
    } catch (e: Exception) {
        throw DataRetrievalException("Unable to extract oembed data for the given twitter link: $link")
    }
}

fun extractOembedTypeFromLink(link: String): OembedType {
    return when {
        link.contains("twitter", ignoreCase = true) -> OembedType.TWITTER
        link.contains("instagram", ignoreCase = true) -> OembedType.INSTAGRAM
        link.contains("youtube", ignoreCase = true) -> OembedType.YOUTUBE
        else -> OembedType.UNSUPPORTED
    }
}

enum class OembedType {
    INSTAGRAM,
    TWITTER,
    YOUTUBE,
    GITHUB,
    DAILYMOTION,
    VIMEO,
    UNSUPPORTED
}

data class OembedGeneratorResponse(
    val oembedLink: String,
    val type: OembedType
)