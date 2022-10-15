package uz.gita.dtm.utils


import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import uz.gita.dtm.R


class InternetBroadCast(context: Context) : BroadcastReceiver() {
    private val db = Firebase.firestore
    var dialog: Dialog
    var view: View
    private var block: (() -> Unit)? = null

    init {
        view = LayoutInflater.from(context).inflate(R.layout.overlay_no_internet, null)
        dialog = Dialog(context)
        dialog.setContentView(
            view
        )

        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog.setCancelable(false)
        view.findViewById<AppCompatButton>(R.id.confirm).setOnClickListener {
            block?.invoke()
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
            db.clearPersistence()
            val docRef = db.collection("pin").document("connection")
            docRef.get().addOnSuccessListener {
                if (it.metadata.isFromCache) {
                    dialog.show()
                } else {
                    dialog.dismiss()
                }
            }.addOnFailureListener {
                dialog.show()
            }
                .addOnCanceledListener { dialog.show() }
        }
    }
}