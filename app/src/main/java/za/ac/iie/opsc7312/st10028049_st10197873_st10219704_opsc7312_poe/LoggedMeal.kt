package za.ac.iie.opsc7312.st10028049_st10197873_st10219704_opsc7312_poe

data class Meal(
    val name: String,
    val description: String,
    val type: String,
    val calories: Int,
    val date: String,
    val time: String
) {
    val dateTime: String
        get() = "$date $time"
}
