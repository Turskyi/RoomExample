package ua.turskyi.roomexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*
import ua.turskyi.roomexample.room.AppDataBase
import ua.turskyi.roomexample.room.DBAppThread
import ua.turskyi.roomexample.room.model.Profile

class SecondActivity : AppCompatActivity(R.layout.activity_second) {


    private lateinit var dataBase: AppDataBase
    private lateinit var appThread: DBAppThread
    private lateinit var profile: Profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBase = AppDataBase.getInstance(this)
        appThread = DBAppThread(threadName = "SecondActivity")
        appThread.start()
        appThread.postTask(Runnable {
            val result = dataBase.profileDAO().getAll()
            if(result.isNotEmpty()){
                profile = result[0]
            }
        })

        btnSendToThirdActivity.setOnClickListener {
            saveProfile()
            sendToThirdActivity()
        }
    }

    private fun saveProfile() {
        profile.language = editTextLanguage.text.toString()
        appThread.postTask(Runnable {
            dataBase.profileDAO().insertData(profile)
        })
    }

    private fun sendToThirdActivity() {
        val intent = Intent(this, ThirdActivity::class.java)
        this.startActivity(intent)
    }

}