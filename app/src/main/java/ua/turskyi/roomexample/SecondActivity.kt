package ua.turskyi.roomexample

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*
import ua.turskyi.roomexample.room.AppDataBase
import ua.turskyi.roomexample.room.model.Profile

class SecondActivity : AppCompatActivity(R.layout.activity_second) {

    private lateinit var dataBase: AppDataBase
    private lateinit var appHandler: Handler
    private lateinit var profile: Profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handlerThread = HandlerThread("SecondActivity")
        handlerThread.start()
        val looper: Looper = handlerThread.looper
        appHandler = Handler(looper)

        dataBase = AppDataBase.getInstance(this)

        getLocalData()
    }

    private fun getLocalData() {
        val task = Runnable {
            val result = dataBase.profileDAO().getAll()
            profile = result[0]
            btnSendToThirdActivity.setOnClickListener {
                saveProfile()
                sendToThirdActivity()
            }
        }
        appHandler.post(task)
    }

    private fun saveProfile() {
        profile.language = editTextLanguage.text.toString()
        appHandler.post {
            dataBase.profileDAO().insert(profile)
        }
    }

    private fun sendToThirdActivity() {
        val intent = Intent(this, ThirdActivity::class.java)
        this.startActivity(intent)
    }
}