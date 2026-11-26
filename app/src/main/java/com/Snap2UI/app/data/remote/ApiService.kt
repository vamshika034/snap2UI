
    package com.Snap2UI.app.data.remote

    import retrofit2.Call
    import retrofit2.http.GET

    interface ApiService {

        @GET("health")
        fun checkHealth(): Call<HealthResponse>

    }

