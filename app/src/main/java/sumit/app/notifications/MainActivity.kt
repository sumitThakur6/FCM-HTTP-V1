package sumit.app.notifications

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.launch
import sumit.app.notifications.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var flag = intent.getIntExtra("flag", 0)
        Log.d("asdf", "Bundles flag -> ${intent.extras}")
        intent?.extras?.let {

            if(it.getInt("flag") == 1 ){
                val intent = Intent(this, ChildActivity::class.java)
                startActivity(intent)
            }
        }

        binding.btnNotify.setOnClickListener {
            val intent = Intent(this, ChildActivity::class.java)
            startActivity(intent)
        }


        var policy : StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val accessToken = AccessToken()
        lifecycleScope.launch {
            val authToken = accessToken.accessToken
            Log.d("asdf", "Auth token -> $authToken")
        }

        getToken()

    }

    private fun getToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.d("asdf", "Fetching FCM registration token failed" )
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Log.d("asdf", "Token -> $token")

        })
    }


}