package uz.gita.dtm.presentation.ui.screen.activity

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.dtm.R
import uz.gita.dtm.presentation.navigation.Handler
import uz.gita.dtm.utils.InternetBroadCast
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val broadCast = InternetBroadCast(this)
        registerReceiver(
            broadCast,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        broadCast.check { finish() }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        handler.navigationStack
            .onEach {
                it.invoke(navHostFragment.findNavController())
            }.launchIn(lifecycleScope)
    }

}