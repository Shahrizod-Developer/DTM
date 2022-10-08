package uz.gita.dtm.data.shp

import android.content.Context
import android.content.SharedPreferences

class MySharedPreference {
    companion object {
        private lateinit var sharedPref: SharedPreferences

        fun init(context: Context): SharedPreferences {
            if (!Companion::sharedPref.isInitialized) sharedPref =
                context.getSharedPreferences("AppDataBase", Context.MODE_PRIVATE)
            return sharedPref
        }


        var pinCode: String
            get() = sharedPref.getString("PINCode", "").toString()
            set(number) {
                sharedPref.edit().putString("PINCode", number).apply()
            }
    }
}