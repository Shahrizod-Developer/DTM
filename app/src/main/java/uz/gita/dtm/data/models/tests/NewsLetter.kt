package uz.gita.dtm.data.models.tests

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.UUID
@Parcelize
data class NewsLetter(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val image: String,
    val desc: String
):Parcelable