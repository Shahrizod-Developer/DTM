package uz.gita.dtm.data.models.news

import android.os.Parcelable
import androidx.navigation.NavArgs
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class News(
    val id: String,
    val title: String,
    val desc: String,
    val image: String,
    val date: Long
): Parcelable