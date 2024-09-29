package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

import java.io.Serializable

data class Recipe(
    val name: String,
    val ingredients: String,
    val method: String,
    val cookware: String
) : Serializable