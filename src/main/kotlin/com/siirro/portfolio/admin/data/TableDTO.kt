package com.siirro.portfolio.admin.data

class TableDTO(
    val name: String,
    val columns: List<String>,
    var records: List<List<String>>
)