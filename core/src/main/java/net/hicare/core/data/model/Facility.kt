package net.hicare.core.data.model

data class Facilities(var statusCode: Int, var message: String, var data: List<Facility>)

data class Facility(
    val facilityId: String,
    val facilityName: String,
    val logoUrl: String
)