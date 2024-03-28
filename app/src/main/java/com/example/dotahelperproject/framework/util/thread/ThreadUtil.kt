package com.example.dotahelperproject.framework.util.thread

import android.os.Looper
import java.util.concurrent.Executors
import android.os.Handler

class ThreadUtil {
    companion object {
        private val executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
        private val handler = Handler(Looper.getMainLooper())

        // start task with thread
        fun startThread(runnable: Runnable){
            executorService.submit(runnable)
        }

        // back to main thread to update UI
        fun startUIThread(delayMillis: Int, runnable: Runnable){
            handler.postDelayed(runnable, delayMillis.toLong())
        }
    }
    protected fun finalize(){
        if(!executorService.isShutdown){
            executorService.shutdown()
        }
    }
}