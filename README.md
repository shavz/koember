# KOEMBER

A pure kotlin service that generates an embeddable sharable html given a link. The links are defined in the [oembed specification](https://oembed.com/)

The service uses the lightweight [http4k](https://www.http4k.org/) library running the server and keep the implementation light.

Note: This is a toy application for a blogpost

## How to run locally

To run the library locally, add the following code in `io.shiveenp.Application.kt` file:

```kotlin
fun main(){
    // launch lambda locally
    fun runLambdaLocally() {
        val app: HttpHandler = oembedDataApp(mapOf())
        app.asServer(Netty(9000)).start()
    }

    runLambdaLocally()
}
```

This runs the server as it would run on AWS lambda.

The service handles the parsing of the link to identify the oembed resource type at the time of invocation. Usage is as follows:

#### Usage

The service is deployed as an aws lamda and can be invoked as:

```http request
GET https://sx2psa0bab.execute-api.ap-southeast-2.amazonaws.com/Testing?=<link_to_a_supported_service>
```

```http request
GET http://localhost:9000/?link=<link_we_want_to_get_oembed_for>
```

the resulting response would contain the oembed html will be ready to be embedded in your page.

### Currently supported:
- Instagram
- Twitter
- Youtube

This service is in active development and will soon have an aws lamda based api call site and some more non oembed standard supporting services added, such as github.

