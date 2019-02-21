import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Netty
import org.http4k.server.asServer

fun main() {

    val app = routes(
        "/alive" bind GET to { Response(OK).body("The crew is more kahless now than vogon. biological and tightly dead.") },
        "/api" bind routes(
            "/embedLink" bind GET to { request ->  Response(OK).body(getOembedData(request.query("link")!!))}
        )
    )

    val nettyServer = app.asServer(Netty(9000)).start()
}