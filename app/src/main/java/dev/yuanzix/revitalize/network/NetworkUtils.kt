package dev.yuanzix.revitalize.network

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

object NetworkUtils {
    private val client = OkHttpClient()

    init {
        client
    }

    fun get(url: String): String {
        val request: Request = Request.Builder().url(url).build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            return response.body!!.string()
        }
    }

    private fun postHelper(url: String, data: Map<String, Any>): Response {
        val formBody = FormBody.Builder().apply {
            data.forEach { (key, value) ->
                add(key, value.toString())
            }
        }.build()

        val request = Request.Builder().url(url).post(formBody).build()

        return client.newCall(request).execute().apply {
            if (!isSuccessful) throw IOException("Unexpected code $this")
        }
    }

    fun post(url: String, data: Map<String, Any>): String {
        postHelper(url, data).use { response ->
            return response.body!!.string()
        }
    }

    fun postStatusCode(url: String, data: Map<String, Any>): Int {
        postHelper(url, data).use { response ->
            return response.code
        }
    }
}
