package net.hicare.core.usecase

import net.hicare.core.common.ResultWrapper
import net.hicare.core.domain.model.Facilities
import net.hicare.core.domain.repository.HicareRepository
import javax.inject.Inject

class HicareUseCase @Inject constructor(private val hicareRepository: HicareRepository) {

    suspend fun findAllFacility(): ResultWrapper<Facilities> {
        return hicareRepository.findAllFacility()
    }

}