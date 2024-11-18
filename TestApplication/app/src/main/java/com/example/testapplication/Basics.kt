package com.example.testapplication

data class Position(
    val name:String,
    val effect:String,
    val potency: Int
)

fun main(){
    val healingPositon = Position("Healing Position", "Restores Health", 50)
    val invisiblePositon = Position("Invisiblity Position", "Grants Invisibility", 40)
    val strengthPositon = Position("Strength Position", "Boosts Health", 60)

    val positionList = listOf( healingPositon, invisiblePositon, strengthPositon)
    println(positionList)

}