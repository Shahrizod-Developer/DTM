package uz.gita.dtm.domain.repository.auth.impl

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import uz.gita.dtm.data.models.auth.Authentication
import uz.gita.dtm.data.models.auth.User
import uz.gita.dtm.data.models.mapper.Mapper.toAuthentication
import uz.gita.dtm.data.shp.MySharedPreference
import uz.gita.dtm.domain.repository.auth.AuthRepository
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    private val db = Firebase.firestore
    private lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var mAuth: FirebaseAuth

    override suspend fun sendSmsCode(activity: Activity, phoneNumber: String) {
        mAuth = Firebase.auth
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d("TTT", "onVerificationCompleted: ${credential.smsCode}")
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.d("TTT", "sms junatilmadi ${e.toString()}")

            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                Log.d("TTT", "sms junatildi")
            }
        }

        PhoneAuthProvider.verifyPhoneNumber(
            PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(activity)
                .setCallbacks(mCallbacks)
                .build()
        )
    }

    override suspend fun verificationSmsCode(context: Context, OTPpassword: String) {
        val credential =
            PhoneAuthProvider.getCredential(MySharedPreference.verificationId, OTPpassword)
        Firebase.auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var user = Authentication(
                        userId = UUID.randomUUID().toString(),
                        phoneNumber = MySharedPreference.phoneNumber,
                        password = MySharedPreference.password,
                        jShShIR = -1L,
                    )
                    db.collection("authentication").document(MySharedPreference.phoneNumber)
                        .set(user).addOnSuccessListener { Log.d("TTT", "cod tasdiqlandi") }
                } else {

                }
            }
    }

    override suspend fun login(user: User) {
        val docRef = db.collection("authentication").document(user.phoneNumber)
        docRef.get().addOnSuccessListener { document ->
            if (document != null) {
                val authData = document.toAuthentication()
                if (authData.phoneNumber == user.phoneNumber && authData.password == user.password) {
                    MySharedPreference.JShShIR = authData.jShShIR
                    Log.d("TTT", "tug'ri terildi")
                }
                Log.d("TTT", "phoneNumber yoki  password xato")

            } else {
                Log.d("TTT", "bunday foydalanuvchi topilmadi")

            }
        }
    }
}