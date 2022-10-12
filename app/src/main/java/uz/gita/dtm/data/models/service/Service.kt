package uz.gita.dtm.data.models.service

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Service(
    val id: String,
    val title: String,
    val image: String,
    val desc: String
) : Parcelable