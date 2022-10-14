package uz.gita.dtm.app

import android.app.Application
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import dagger.hilt.android.HiltAndroidApp
import uz.gita.dtm.data.shp.MySharedPreference
import uz.gita.dtm.utils.InternetBroadCast

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MySharedPreference.init(this)


    }
}