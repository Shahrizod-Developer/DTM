package uz.gita.dtm.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uz.gita.dtm.data.shp.MySharedPreference

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MySharedPreference.init(this)
    }
}