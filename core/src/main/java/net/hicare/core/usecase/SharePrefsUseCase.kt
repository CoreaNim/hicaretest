package net.hicare.core.usecase

import net.hicare.core.domain.repository.SharePrefsRepository
import javax.inject.Inject

class SharePrefsUseCase @Inject constructor(private val sharePrefsRepository: SharePrefsRepository) {

    val facilityName = sharePrefsRepository.facilityName
    fun saveNewFacilityName(facilityName: String) {
        return sharePrefsRepository.saveNewFacilityName(facilityName)
    }


}