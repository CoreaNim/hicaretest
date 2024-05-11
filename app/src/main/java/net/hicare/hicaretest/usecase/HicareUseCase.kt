package net.hicare.hicaretest.usecase

import net.hicare.hicaretest.common.ResultWrapper
import net.hicare.hicaretest.domain.model.Facilities
import net.hicare.hicaretest.domain.repository.HicareRepository
import javax.inject.Inject

class HicareUseCase @Inject constructor(private val hicareRepository: HicareRepository) {

    suspend fun findAllFacility(): ResultWrapper<Facilities> {
        return hicareRepository.findAllFacility()
    }

}