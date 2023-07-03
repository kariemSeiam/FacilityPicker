package com.radiusagent.facilitypicker.data.remote.api

import com.radiusagent.facilitypicker.data.local.models.FacilityResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {
    @GET("ad-assignment/db")
    fun getData(): Single<FacilityResponse>
}
