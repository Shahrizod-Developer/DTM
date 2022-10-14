package uz.gita.dtm.domain.repository.auth.impl

import android.app.Activity
import android.content.Context
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import uz.gita.dtm.R
import uz.gita.dtm.data.models.auth.Authentication
import uz.gita.dtm.data.models.auth.User
import uz.gita.dtm.data.models.mapper.Mapper.toAuthentication
import uz.gita.dtm.data.shp.MySharedPreference
import uz.gita.dtm.data.utils.MessageData
import uz.gita.dtm.data.utils.ResultData
import uz.gita.dtm.domain.repository.auth.AuthRepository
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {


    private val db = Firebase.firestore
    private lateinit var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var mAuth: FirebaseAuth

    override fun sendSmsCode(activity: Activity, phoneNumber: String): Flow<ResultData<Unit>> =
        callbackFlow {
            val docRef = db.collection("authentication").document(phoneNumber)
            docRef.get().addOnSuccessListener { document ->
                if (document != null && document.data != null) {
                    val authData = document.toAuthentication()
                    if (authData.phoneNumber == phoneNumber) {
                        trySend(ResultData.messageResource(R.string.text1))
                    }
                } else {
                    mAuth = Firebase.auth
                    mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        override fun onVerificationCompleted(credential: PhoneAuthCredential) {}

                        override fun onVerificationFailed(e: FirebaseException) {
                            trySend(ResultData.error(e))
                        }

                        override fun onCodeSent(
                            verificationId: String, token: PhoneAuthProvider.ForceResendingToken
                        ) {
                            MySharedPreference.verificationId = verificationId
                            trySend(ResultData.success(Unit))
                        }
                    }

                    PhoneAuthProvider.verifyPhoneNumber(
                        PhoneAuthOptions.newBuilder(mAuth).setPhoneNumber(phoneNumber)
                            .setTimeout(60L, TimeUnit.SECONDS).setActivity(activity)
                            .setCallbacks(mCallbacks).build()
                    )
                }
            }
            awaitClose()
        }

    override fun verificationSmsCode(
        context: Context,
        OTPpassword: String
    ): Flow<ResultData<Unit>> = callbackFlow {
        val credential =
            PhoneAuthProvider.getCredential(MySharedPreference.verificationId, OTPpassword)
        Firebase.auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var user = hashMapOf(
                        "userId" to UUID.randomUUID().toString(),
                        "phoneNumber" to MySharedPreference.phoneNumber,
                        "password" to MySharedPreference.password,
                        "jShShIR" to MySharedPreference.JShShIR,
                    )
                    db.collection("authentication").document(MySharedPreference.phoneNumber)
                        .set(user).addOnSuccessListener {
                            MySharedPreference.connectedPhone = true
                            trySend(ResultData.success(Unit))
                        }
                } else {
                    trySend(ResultData.message(MessageData.messageResource(R.string.text2)))
                }
            }.addOnFailureListener { eror ->
                trySend(ResultData.error(eror))
            }
        awaitClose()
    }

    override fun login(user: User): Flow<ResultData<Unit>> = callbackFlow {
        val docRef = db.collection("authentication").document(user.phoneNumber)
        docRef.get().addOnSuccessListener { document ->
            if (document != null) {
                if (document.data != null) {
                    val authData = document.toAuthentication()
                    if (authData.phoneNumber == user.phoneNumber && authData.password == user.password) {
                        MySharedPreference.JShShIR = authData.jShShIR
                        MySharedPreference.connectedPhone = true
                        trySend(ResultData.success(Unit))
                    } else {
                        trySend(ResultData.message(MessageData.messageResource(R.string.text3)))
                    }
                } else {
                    trySend(ResultData.message(MessageData.messageResource(R.string.text8)))
                }
            }
        }.addOnFailureListener { eror ->
            trySend(ResultData.error(eror))
        }
        awaitClose()
    }

    override fun restorePassword(phoneNumber: String): Flow<ResultData<Unit>> = callbackFlow {
        val docRef = db.collection("authentication").document(phoneNumber)
        docRef.get().addOnSuccessListener { document ->
            if (document != null) {
                val authData = document.toAuthentication()
                if (authData.phoneNumber == phoneNumber) {
                    MySharedPreference.JShShIR = authData.jShShIR
                    trySend(ResultData.success(Unit))
                } else {
                    trySend(ResultData.message(MessageData.messageResource(R.string.text4)))
                }
            }
        }.addOnFailureListener { eror ->
            trySend(ResultData.error(eror))
        }
        awaitClose()
    }

    override fun setNewPassword(password: String): Flow<ResultData<Unit>> = callbackFlow {
        val user = Authentication(
            userId = UUID.randomUUID().toString(),
            phoneNumber = MySharedPreference.phoneNumber,
            password = password,
            jShShIR = MySharedPreference.JShShIR,
        )
        db.collection("authentication").document(MySharedPreference.phoneNumber).set(user)
            .addOnSuccessListener { trySend(ResultData.success(Unit)) }
            .addOnFailureListener { error -> trySend(ResultData.error(error)) }
    }

    override fun userPresence(): Flow<Boolean> = flow {
        if (MySharedPreference.connectedPhone) {
            emit(true)
        } else {
            emit(false)
        }
    }
}