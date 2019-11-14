package ua.turskyi.roomexample

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_third.*
import ua.turskyi.roomexample.room.AppDataBase
import ua.turskyi.roomexample.room.DBAppThread
import ua.turskyi.roomexample.room.model.Profile

class ThirdActivity : AppCompatActivity(R.layout.activity_third) {

    private lateinit var dataBase: AppDataBase
    private lateinit var appThread: DBAppThread
    private lateinit var profile: Profile
    private val uiHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBase = AppDataBase.getInstance(this)
        appThread = DBAppThread(threadName = "ThirdActivity")
        appThread.start()
        appThread.postTask(Runnable {
            val result = dataBase.profileDAO().getAll()
            if (result.isNotEmpty()) {
                profile = result[0]
                showInfo()
            }
        })
    }

    private fun showInfo() {
        uiHandler.post {
            name.text = profile.name
            age.text = profile.age
            language.text = profile.language
        }
    }
}