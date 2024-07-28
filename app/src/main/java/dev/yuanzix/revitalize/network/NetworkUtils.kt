package dev.yuanzix.revitalize.network

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException

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
        val formBody = FormBody.Builder()
        data.forEach { (key, value) ->
            formBody.add(key, value.toString())
        }
        val request = Request.Builder().url(url)
            .post(formBody.build())
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            return response
        }
    }

    fun post(url: String, data: Map<String, Any>): String {
        return postHelper(url, data).body!!.string()
    }

    fun postStatusCode(url: String, data: Map<String, Any>): Int {
        return postHelper(url, data).code
    }

}

