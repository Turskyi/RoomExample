package ua.turskyi.roomexample

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_third.*
import ua.turskyi.roomexample.room.AppDatabase
import ua.turskyi.roomexample.room.model.Profile

class ThirdActivity : AppCompatActivity(R.layout.activity_third) {

    private lateinit var database: AppDatabase
    private lateinit var appThread: Handler
    private lateinit var profile: Profile
    private val uiHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handlerThread = HandlerThread("ThirdActivity")
        handlerThread.start()
        val looper: Looper = handlerThread.looper
        appThread = Handler(looper)

        database = AppDatabase.getInstance(this)

        val task = Runnable {
            val result = database.profileDAO().getAll()
            if (result.isNotEmpty()) {
                profile = result[0]
                showInfo()
            }
        }

        appThread.post(task)
    }

    private fun showInfo() {
        uiHandler.post {
            name.text = profile.name
            age.text = profile.age
            language.text = profile.language
        }
    }
}