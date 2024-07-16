package com.example.kotlinbasic_bai6.model

data class ApiUser(
    val name: Name,
    val email: String,
    val picture: Picture,
    val gender: String,
    val location: Location
) {

    data class Name(val title: String, val first: String, val last: String)
    data class Picture(val large: String)
    data class Location(
        val street: Street,
        val city: String,
        val state: String,
        val country: String,
        val postcode: String,
        val timezone: Timezone
    )


    data class Street(val number: Int, val name: String)
    data class Timezone(val offset: String, val description: String)

}
