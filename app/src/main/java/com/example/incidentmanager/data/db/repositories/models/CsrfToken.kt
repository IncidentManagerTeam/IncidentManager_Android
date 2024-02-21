package com.example.incidentmanager.data.db.repositories.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CsrfToken(
    val token: String,
    val headerName: String
) : Parcelable
