package net.hicare.hicaretest.domain.repository

import android.content.SharedPreferences
import com.google.gson.Gson
import net.hicare.hicaretest.extensions.string

class SharePrefsRepository(sharePrefs: SharedPreferences) {
    private val gson = Gson()
    var facilityName by sharePrefs.string(defaultValue = "")

    fun saveNewFacilityName(facilityName: String) {
        this.facilityName = facilityName
    }
}
