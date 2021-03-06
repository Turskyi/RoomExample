package ua.turskyi.roomexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ua.turskyi.roomexample.room.AppDatabase
import ua.turskyi.roomexample.room.DBAppThread
import ua.turskyi.roomexample.room.model.Profile

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var database: AppDatabase
    private lateinit var appThread: DBAppThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appThread = DBAppThread(threadName = "MainActivity")
        appThread.start()
        database = AppDatabase.getInstance(this)

        btnSendToSecondActivity.setOnClickListener {
            saveProfile()
            sendToSecondActivity()
        }

        btnShowData.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveProfile() {
        val profile = Profile("${editTextName.text}", "${editTextAge.text}")
        appThread.postTask {
            database.profileDAO().insert(profile)
        }
    }


    private fun sendToSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        this.startActivity(intent)
    }
}
