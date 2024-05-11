package net.hicare.hicaretest.data.network

import net.hicare.hicaretest.domain.model.Facilities
import retrofit2.http.GET

interface HicareService {
    @GET("/all/facility.do")
    suspend fun findAllFacility(): Facilities
}