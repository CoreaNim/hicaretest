package net.hicare.hicaretest.ui.facility

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import net.hicare.core.common.ResultWrapper
import net.hicare.core.domain.model.Facilities
import net.hicare.core.extensions.resultCallbackFlow
import net.hicare.core.usecase.HicareUseCase
import net.hicare.core.usecase.SharePrefsUseCase
import javax.inject.Inject

@HiltViewModel
class FacilityViewModel @Inject constructor(
    private val hicareUseCase: HicareUseCase,
    private val sharePrefsUseCase: SharePrefsUseCase
) : ViewModel() {
    val facilitiesResult = MutableStateFlow<ResultWrapper<Facilities>>(ResultWrapper.Start)
    val facilityName = MutableStateFlow(sharePrefsUseCase.facilityName)

    fun saveFacilityName(facilityName: String) {
        if (this.facilityName.value == facilityName) {
            return
        }
        this.facilityName.value = facilityName
        sharePrefsUseCase.saveNewFacilityName(facilityName)
    }

    fun findAllFacilities() =
        resultCallbackFlow(facilitiesResult) {
            hicareUseCase.findAllFacility()
        }

}