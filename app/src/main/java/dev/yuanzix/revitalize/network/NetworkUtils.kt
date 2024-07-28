package dev.yuanzix.revitalize.network

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
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

    fun post(url: String, data: Map<String, Any>): String {
        val formBody = FormBody.Builder()
        data.forEach { (key, value) ->
            formBody.add(key, value.toString())
        }
        val request = Request.Builder().url(url)
            .post(formBody.build())
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            return response.body!!.string()
        }
    }

}

