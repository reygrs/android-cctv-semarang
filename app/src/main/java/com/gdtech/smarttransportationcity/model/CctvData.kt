package com.gdtech.smarttransportationcity.model

data class CctvData(
    val data: List<Data>
)

data class Data(
    val lokasi: String,
    val url: String
)