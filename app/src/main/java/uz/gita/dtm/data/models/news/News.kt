package uz.gita.dtm.data.models.news

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    val id: String,
    val title: String,
    val desc: String,
    val image: String,
    val date: Long
): Parcelable