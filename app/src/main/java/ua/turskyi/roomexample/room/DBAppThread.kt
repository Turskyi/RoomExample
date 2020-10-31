package ua.turskyi.roomexample.room

import android.os.Handler
import android.os.HandlerThread

class DBAppThread(threadName: String) : HandlerThread(threadName) {
    private lateinit var personalHandler: Handler
    override fun onLooperPrepared() {
        super.onLooperPrepared()
        personalHandler = Handler(looper)
    }
    fun postTask(task: Runnable) {
        try {
            personalHandler.post(task)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}