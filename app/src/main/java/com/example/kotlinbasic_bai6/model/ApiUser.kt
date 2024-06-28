package com.example.kotlinbasic_bai6.model

data class ApiUser(
    val name: Name,
    val email: String,
    val picture: Picture,
    val gender:String
) {
    data class Name(val title: String,val first: String, val last: String)
    data class Picture(val large: String)
}
