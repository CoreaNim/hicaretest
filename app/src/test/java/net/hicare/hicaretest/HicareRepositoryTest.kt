package net.hicare.hicaretest


import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import net.hicare.hicaretest.data.network.HicareService
import net.hicare.hicaretest.domain.model.Facilities
import net.hicare.hicaretest.domain.model.Facility
import net.hicare.hicaretest.domain.repository.HicareRepository
import org.junit.Test

@ExperimentalCoroutinesApi
class HicareRepositoryTest : TestCase() {

    @MockK
    lateinit var api: HicareService

    @RelaxedMockK
    lateinit var apiRelaxed: HicareService

    private lateinit var hicareRepository: HicareRepository
    private lateinit var hicareRepositoryRelaxed: HicareRepository

    public override fun setUp() {
        super.setUp()
        MockKAnnotations.init(this)
        hicareRepository = HicareRepository(api)
        hicareRepositoryRelaxed = HicareRepository(apiRelaxed)

    }

    @Test
    fun testFindAllFacility() {
        val mockFacilities = listOf(
            Facility(
                "6566e328-04f3-42c6-8a88-5f153d523c21",
                "facility_test_1",
                "https://hicare.net/facility/1"
            ),
            Facility(
                "6566e328-04f3-42c6-8a88-5f153d523c22",
                "facility_test_2",
                "https://hicare.net/facility/2"
            ),
            Facility(
                "6566e328-04f3-42c6-8a88-5f153d523c23",
                "facility_test_3",
                "https://hicare.net/facility/3"
            )
        )
        coEvery { api.findAllFacility() } returns Facilities(200, "success", mockFacilities)
        runBlocking {
            val facility = hicareRepository.findAllFacility().takeValueOrThrow()
            assertEquals(3, facility.data.size)
        }
    }

}
