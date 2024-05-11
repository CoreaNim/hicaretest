package net.hicare.hicaretest.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import net.hicare.hicaretest.common.ResultWrapper
import net.hicare.hicaretest.domain.model.Facilities
import net.hicare.hicaretest.extensions.resultCallbackFlow
import net.hicare.hicaretest.usecase.HicareUseCase
import net.hicare.hicaretest.usecase.SharePrefsUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
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