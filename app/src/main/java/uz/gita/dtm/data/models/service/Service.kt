package uz.gita.dtm.data.models.service

import android.os.Parcelable
import androidx.navigation.NavArgs
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


@Parcelize
data class Service(
    val id: String,
    val title: String,
    val image: String,
    val desc: String
) : Parcelable