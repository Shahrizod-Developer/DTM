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
        var password: String
            get() = sharedPref.getString("PASSWORD", "").toString()
            set(number) {
                sharedPref.edit().putString("PASSWORD", number).apply()
            }
        var phoneNumber: String
            get() = sharedPref.getString("PhoneNumber", "").toString()
            set(number) {
                sharedPref.edit().putString("PhoneNumber", number).apply()
            }
        var verificationId: String
            get() = sharedPref.getString("VerificationId", "").toString()
            set(number) {
                sharedPref.edit().putString("VerificationId", number).apply()
            }
        var JShShIR: Long
            get() = sharedPref.getLong("JShShIR", -1L)
            set(number) {
                sharedPref.edit().putLong("JShShIR", number).apply()
            }
    }
}