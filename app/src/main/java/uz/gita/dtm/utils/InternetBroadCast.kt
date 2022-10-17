package uz.gita.dtm.utils


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import uz.gita.dtm.R


class InternetBroadCast(val context: Context, private val window: Window) : BroadcastReceiver() {
    private val db = Firebase.firestore
    var dialog: Dialog = Dialog(context)
    @SuppressLint("InflateParams")
    var view: View = LayoutInflater.from(context).inflate(R.layout.overlay_no_internet, null)
    private var block: (() -> Unit)? = null

    init {
        dialog.setContentView(
            view
        )
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog.setCancelable(false)
        view.findViewById<AppCompatButton>(R.id.confirm).setOnClickListener {
            window.statusBarColor = ContextCompat.getColor(context, R.color.red)
            dialog.cancel()
        }
    }

    fun check(chain: () -> Unit) {
        block = chain
    }

    override fun onReceive(context: Context, intent: Intent?) {
        val connMgr = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi = connMgr
            .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobile = connMgr
            .getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wifi!!.isAvailable || mobile!!.isAvailable) {
            check()
        }

    }

    private fun check() {
        val docRef = db.collection("pin").document("connection")
        docRef.get().addOnSuccessListener {
            if (it.metadata.isFromCache) {
                Log.d("TTT", "ishla")
                dialog.show()
            } else {
                window.statusBarColor = ContextCompat.getColor(context, R.color.black)
                dialog.hide()
            }
        }.addOnFailureListener {
            dialog.show()
        }
            .addOnCanceledListener { dialog.show() }
    }

}