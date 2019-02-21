package io.shiveenp

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.auth.*
import io.ktor.client.features.auth.basic.*
import io.ktor.gson.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import java.net.URL
import kotlinx.coroutines.*
import io.ktor.client.features.logging.*
import kotlin.test.*
import io.ktor.server.testing.*
import io.ktor.client.engine.mock.*
import kotlinx.coroutines.io.*
import io.ktor.client.call.*

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("HELLO WORLD!", response.content)
            }
        }
    }

    @Test
    fun testClientMock() {
        runBlocking {
            val client = HttpClient(MockEngine {
                if (url.encodedPath == "/") {
                    MockHttpResponse(call, HttpStatusCode.OK, ByteReadChannel(byteArrayOf(1, 2, 3)), headersOf("X-MyHeader", "MyValue"))
                } else {
                    responseError(HttpStatusCode.NotFound, "Not Found ${url.encodedPath}")
                }
            }) {
                expectSuccess = false
            }
            assertEquals(byteArrayOf(1, 2, 3).toList(), client.get<ByteArray>("/").toList())
            assertEquals("MyValue", client.call("/").response.headers["X-MyHeader"])
            assertEquals("Not Found other/path", client.get<String>("/other/path"))
        }
    }
}
