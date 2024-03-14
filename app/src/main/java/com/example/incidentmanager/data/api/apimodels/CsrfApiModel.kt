package com.example.incidentmanager.data.api.apimodels

data class CsrfApiModel(
    val parameterName: String,
    val token: String,
    val headerName: String
)