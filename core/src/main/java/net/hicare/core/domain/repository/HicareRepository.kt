package net.hicare.core.domain.repository

import net.hicare.core.common.ResultWrapper
import net.hicare.core.network.HicareService
import net.hicare.core.domain.model.Facilities
import net.hicare.core.extensions.safeApiCall
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