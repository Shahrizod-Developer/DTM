package uz.gita.dtm.data.shp

import android.content.Context
import android.content.SharedPreferences

class MySharedPreference {
    companion object {
        private lateinit var sharedPref: SharedPreferences

        fun init(context: Context): SharedPreferences {
            if (!Companion::sharedPref.isInitialized) sharedPref =
                context.getSharedPreferences("app_name", Context.MODE_PRIVATE)
            return sharedPref
        }

        var pinCode: String
            get() = sharedPref.getString("pin_code", "").toString()
            set(number) {
                sharedPref.edit().putString("pin_code", number).apply()
            }
        var password: String
            get() = sharedPref.getString("password", "").toString()
            set(number) {
                sharedPref.edit().putString("password", number).apply()
            }
        var phoneNumber: String
            get() = sharedPref.getString("phone_number", "").toString()
            set(number) {
                sharedPref.edit().putString("phone_number", number).apply()
            }
        var verificationId: String
            get() = sharedPref.getString("verification_id", "").toString()
            set(number) {
                sharedPref.edit().putString("verification_id", number).apply()
            }
        var JShShIR: Long
            get() = sharedPref.getLong("jshshr", 0)
            set(number) {
                sharedPref.edit().putLong("jshshr", number).apply()
            }

        var passportNumber: Long
            get() = sharedPref.getLong("passport_number", -1L)
            set(number) {
                sharedPref.edit().putLong("passport_number", number).apply()
            }
        var series: String
            get() = sharedPref.getString("series", "").toString()
            set(value) {
                sharedPref.edit().putString("series", value).apply()
            }

        var connectedPhone: Boolean
            get() = sharedPref.getBoolean("CONNECTEDPhone", false)
            set(value) = sharedPref.edit().putBoolean("CONNECTEDPhone", value).apply()

    }
}