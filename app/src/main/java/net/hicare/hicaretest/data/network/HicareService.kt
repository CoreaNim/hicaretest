package net.hicare.hicaretest.data.network

import net.hicare.hicaretest.domain.model.Facilities
import retrofit2.http.GET

interface HicareService {
//    @GET("/all/facility.do")
    @GET("/v1/b17f9527-383f-4142-bdaa-6e297b878213")
    suspend fun findAllFacility(): Facilities
}