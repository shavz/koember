package io.shiveenp

import org.http4k.core.HttpHandler
import org.http4k.core.Response
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.OK
import org.http4k.serverless.AppLoader

object oembedDataApp : AppLoader {
    override fun invoke(env: Map<String, String>): HttpHandler = {
        val link = it.query(LINK_QUERY_PARAM)
        if (link == null) {
            Response(BAD_REQUEST).body("append the oembedLink to get oembed data as a query param with key <link>")
        } else {
            val oembedResponse = processOembedRequest(link)
            if (oembedResponse.type == OembedType.UNSUPPORTED) {
                Response(BAD_REQUEST).body("Link type not yet supported")
            } else {
                Response(OK).body(oembedResponse.oembedLink)
            }
        }
    }

    private const val LINK_QUERY_PARAM = "link"
}
