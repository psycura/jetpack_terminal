package com.example.terminal.data.network

import com.example.terminal.data.models.GetBarsResult
import com.example.terminal.presentation.TimeFrame
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor

class ApiService {
    private companion object {
        const val BASE_URL = "https://api.polygon.io/v2"
    }

    private val client = HttpClient(OkHttp) {
        expectSuccess = true
        engine {
            config {
                followRedirects(true)
            }
            addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            })
        }
        install(Logging) {
            level = LogLevel.BODY
        }
        install(ContentNegotiation) {
            json(
                json = Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
    }


    suspend fun loadBars(timeFrame: TimeFrame): GetBarsResult {

        return client.get("${BASE_URL}/aggs/ticker/AAPL/range/${timeFrame.value}/2022-01-09/2023-02-10") {
            url {
                parameters.append("adjusted", "true")
                parameters.append("sort", "desc")
                parameters.append("limit", "50000")
                parameters.append("apiKey", "yTXdlP2xxL1dkWAz5paWvELOGyzrdTnG")
            }
        }.body()
    }
}