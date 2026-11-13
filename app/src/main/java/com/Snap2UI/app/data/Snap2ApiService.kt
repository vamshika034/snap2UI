package com.Snap2UI.app.data

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Snap2ApiService {

    @GET("/")
    suspend fun healthCheck(): Map<String, String>

    @Multipart
    @POST("/api/detect")
    suspend fun detectComponents(
        @Part file: MultipartBody.Part
    ): DetectResponse

    @Multipart
    @POST("/api/generate/xml")
    suspend fun generateXml(
        @Part file: MultipartBody.Part
    ): GenerateXmlResponse

    @Multipart
    @POST("/api/generate/compose")
    suspend fun generateCompose(
        @Part file: MultipartBody.Part
    ): GenerateComposeResponse

    @Multipart
    @POST("/api/similarity")
    suspend fun calculateSimilarity(
        @Part file: MultipartBody.Part
    ): SimilarityResponse

    @POST("/api/generate-from-prompt")
    suspend fun generateFromPrompt(
        @Body request: PromptRequest
    ): PromptResponse

    companion object {
        var BASE_URL: String = "http://10.0.2.2:8000/"

        fun create(): Snap2ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(Snap2ApiService::class.java)
        }
    }
}
