package net.hicare.core.domain

import net.hicare.core.network.util.ResultWrapper
import net.hicare.core.data.model.Facilities
import net.hicare.core.data.repository.HicareRepository
import javax.inject.Inject

class HicareUseCase @Inject constructor(private val hicareRepository: HicareRepository) {

    suspend fun findAllFacility(): ResultWrapper<Facilities> {
        return hicareRepository.findAllFacility()
    }

}