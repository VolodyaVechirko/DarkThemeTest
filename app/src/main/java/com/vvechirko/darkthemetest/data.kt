package com.vvechirko.darkthemetest

class Data(
    val title: String,
    val data1: String? = null,
    val data2: String? = null,
    val chart: String? = null
)

fun mock() = listOf(
    Data("Marketing", "123.4 M"),
    Data("Conversion", "537", "+ 22% of target", "chart1"),
    Data("Conversion", "432.1 M", "+ 12.3% of target", "chart2"),
    Data("Sales", "345.8 M", "+ 11% of target"),
    Data("Users", "45.5 M"),
    Data("Avg. sessions", "4:54 H", "+ 56.6% of target", "chart2"),
    Data("Sessions", "23 242"),
    Data("Bounce rate", "12%", null, "chart1"),
    Data("Page views", "1 345")
)