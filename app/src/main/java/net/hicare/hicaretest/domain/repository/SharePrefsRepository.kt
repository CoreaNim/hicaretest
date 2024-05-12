package net.hicare.hicaretest.domain.repository

import android.content.SharedPreferences
import net.hicare.hicaretest.extensions.string

class SharePrefsRepository(sharePrefs: SharedPreferences) {
    var facilityName by sharePrefs.string(defaultValue = "")

    fun saveNewFacilityName(facilityName: String) {
        this.facilityName = facilityName
    }
}
