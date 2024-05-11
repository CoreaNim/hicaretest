package net.hicare.hicaretest.domain.repository

import net.hicare.hicaretest.common.ResultWrapper
import net.hicare.hicaretest.data.network.HicareService
import net.hicare.hicaretest.domain.model.Facilities
import net.hicare.hicaretest.extensions.safeApiCall
import javax.inject.Inject

class HicareRepository @Inject constructor(
    private val hicareService: HicareService
) {
    suspend fun findAllFacility(): ResultWrapper<Facilities> {
        return safeApiCall {
            hicareService.findAllFacility()
        }
    }
}