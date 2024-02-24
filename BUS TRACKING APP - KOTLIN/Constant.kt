package com.demo.bustracking

object Constant {

    const val URL = "https://blynk.cloud"
    const val URL_QUERY = "/external/api/getAll?token=AMeNCwsiXJQAOQxV4MDH5NMCnOQWpkkA"
    const val MAX_SEATS  = 60
    val locations = listOf(
        Location(
            latitude = "11.043487458971315",
            longitude = "76.86402232415257",
            name = "70 (Gandhipuram to marudhamali)",
            locationName = "R.S. Puram bus stop coimbatore"

        ),
        Location(
            latitude = "11.01647393963337",
            longitude = "76.96898864796009",
            name = "70 (marudhamali to Gandhipuram)",
            locationName = "law college in Bus Stop, Coimbatore, Tamil Nadu"
        ),
        Location(
            latitude = "11.043487458971315",
            longitude = "76.86402232415257",
            name = "S26 (Gandhi puram to marudha mali)",
            locationName = "Bharathiyar University 2nd Gate, Bharathiyar University, Coimbatore, Tamil Nadu"
        ),
        Location(
            latitude = "11.01647393963337",
            longitude = "76.96898864796009",
            name = "S26 (marudha mali to Gandhi puram)",
            locationName = "Navavoor Pirivu bus stop , Coimbatore, Tamil Nadu"
        ),
        Location(
            latitude = "10.994633376049718",
            longitude = "76.95979399133644",
            name = "46 (marudha mali to Town Hall)",
            locationName = "R.S.PURAM bus stop, R.S. Puram, Coimbatore, Tamil Nadu"
        ),
        Location(
            latitude = "11.043487458971315",
            longitude = "76.86402232415257",
            name = "46 (Town Hall to marudha mali)",
            locationName = "Lawley Road Bus Stop, Lawley Road, Tamil Nadu Agricultural University, Coimbatore, P N Pudur, Tamil Nadu"
        ),
        Location(
            latitude = "11.003278534308238",
            longitude = "77.04994615799416",
            name = "1C (Vadavalli to Ondipudur)",
            locationName = "Railway Station Bus Stop, State Bank Road, Opp.Railway Station, Gopalapuram, Coimbatore, Tamil Nadu"
        ),
        Location(
            latitude = "11.027824488196298",
            longitude = "76.9059129007831",
            name = "1C (Ondipudur to Vadavali)",
            locationName = "Vadavalli Bus Stand, Coimbatore, Tamil Nadu"
        ),
    )
}
data class Location(
    val latitude : String ,
    val longitude : String ,
    val name : String = "",
    val locationName : String = ""
)

