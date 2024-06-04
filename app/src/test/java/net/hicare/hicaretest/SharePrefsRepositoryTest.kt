package net.hicare.hicaretest

import android.content.Context
import android.content.SharedPreferences
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.hicare.core.data.repository.SharePrefsRepository
import org.junit.Test

@ExperimentalCoroutinesApi
class SharePrefsRepositoryTest : TestCase() {
    @RelaxedMockK
    lateinit var sharedPrefs: SharedPreferences

    @RelaxedMockK
    lateinit var sharedPrefsEditor: SharedPreferences.Editor

    private lateinit var sharePrefsRepo: SharePrefsRepository

    public override fun setUp() {
        super.setUp()
        MockKAnnotations.init(this)
        val context = mockk<Context>(relaxed = true)
        coEvery { context.getSharedPreferences(any(), any()) } returns sharedPrefs
        coEvery { sharedPrefs.edit() } returns sharedPrefsEditor
        coEvery { sharedPrefsEditor.putString(any(), any()) } returns sharedPrefsEditor

        sharePrefsRepo = SharePrefsRepository(sharedPrefs)
    }

    @Test
    fun testSaveNewFacilityName() {
        coEvery { sharePrefsRepo.facilityName } answers { TEST_FACILITY_NAME }
        sharePrefsRepo.saveNewFacilityName(TEST_FACILITY_NAME)
        val facilityName = sharePrefsRepo.facilityName
        assertEquals(TEST_FACILITY_NAME, facilityName)
    }

    companion object {
        const val TEST_FACILITY_NAME = "Facility 1"
    }
}