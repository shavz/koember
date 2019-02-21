import org.http4k.client.JavaHttpClient
import org.http4k.core.Body
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.format.Jackson.auto

fun getOembedData(link: String): String {
    val request = Request(Method.GET, "https://api.instagram.com/oembed/?url=$link")

    val client: HttpHandler = JavaHttpClient()

    val igLens = Body.auto<IgOembedResponse>().toLens()

    return igLens.extract(client(request)).html
}