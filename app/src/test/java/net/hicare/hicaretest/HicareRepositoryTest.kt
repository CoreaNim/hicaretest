package net.hicare.hicaretest


import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import net.hicare.core.network.HicareService
import net.hicare.core.domain.model.Facilities
import net.hicare.core.domain.model.Facility
import net.hicare.core.domain.repository.HicareRepository
import org.junit.Test

@ExperimentalCoroutinesApi
class HicareRepositoryTest : TestCase() {

    @MockK
    lateinit var api: net.hicare.core.network.HicareService

    @RelaxedMockK
    lateinit var apiRelaxed: net.hicare.core.network.HicareService

    private lateinit var hicareRepository: net.hicare.core.domain.repository.HicareRepository
    private lateinit var hicareRepositoryRelaxed: net.hicare.core.domain.repository.HicareRepository

    public override fun setUp() {
        super.setUp()
        MockKAnnotations.init(this)
        hicareRepository = net.hicare.core.domain.repository.HicareRepository(api)
        hicareRepositoryRelaxed = net.hicare.core.domain.repository.HicareRepository(apiRelaxed)

    }

    @Test
    fun testFindAllFacility() {
        val mockFacilities = listOf(
            net.hicare.core.domain.model.Facility(
                "6566e328-04f3-42c6-8a88-5f153d523c21",
                "facility_test_1",
                "https://hicare.net/facility/1"
            ),
            net.hicare.core.domain.model.Facility(
                "6566e328-04f3-42c6-8a88-5f153d523c22",
                "facility_test_2",
                "https://hicare.net/facility/2"
            ),
            net.hicare.core.domain.model.Facility(
                "6566e328-04f3-42c6-8a88-5f153d523c23",
                "facility_test_3",
                "https://hicare.net/facility/3"
            )
        )
        coEvery { api.findAllFacility() } returns net.hicare.core.domain.model.Facilities(
            200,
            "success",
            mockFacilities
        )
        runBlocking {
            val facility = hicareRepository.findAllFacility().takeValueOrThrow()
            assertEquals(3, facility.data.size)
        }
    }

}
