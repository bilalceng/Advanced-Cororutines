package com.bilalberek.advancedcoroutines

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*
import java.io.File
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main()
    }

}





fun customFunction() {
    println("Start of customFunction")

    val coroutineScope = CoroutineScope(Dispatchers.Default)

    val job = coroutineScope.launch {
        // This coroutine's lifecycle is tied to the coroutineScope
        delay(1000) // Simulate some work
        println("Coroutine inside customFunction")
    }

    runBlocking {
        job.join()
    }


    // Explicitly cancel the coroutine when customFunction is done
    coroutineScope.coroutineContext.cancelChildren()

    println("End of customFunction")
}




@SuppressLint("SuspiciousIndentation")
@OptIn(DelicateCoroutinesApi::class)

fun main() {
/*

        (1..10000).forEach {
            GlobalScope.launch {
                this.launch {
                    val threadName = Thread.currentThread().name
                    println("$it printed on thread $threadName")
                }

            }
        }
        Thread.sleep(1000)
    println("end of thread operations.")


    println("Start of main function")
    customFunction()
    println("End of main function")




    "example of Coroutine start with lazy" exampleOf {
        val job1 = GlobalScope.launch(start = CoroutineStart.LAZY) {

            println("pong")


        }

        GlobalScope.launch {
            delay(2000)
            println("ping")
            job1.join()
            println("ping")
            delay(200)
        }

        Thread.sleep(4000)
    }

    "example of transact ui thread" exampleOf {

        GlobalScope.launch {
            val bgThreadName = Thread.currentThread().name
            println("I’m Job 1 in thread $bgThreadName")
            delay(200)
            try {
                GlobalScope.launch(Dispatchers.Main.immediate) {
                    val uiThreadName = Thread.currentThread().name
                    println("I’m Job 2 in thread $uiThreadName")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        Thread.sleep(1000)


    }

    "example of retry-logic" exampleOf {

        var isDoorOpen = false
        println("Unlocking the door... please wait.\n")
        GlobalScope.launch {
            delay(3000)
            isDoorOpen = true
        }
        GlobalScope.launch {
            repeat(4) {
                println("Trying to open the door...\n")
                delay(800)
                if (isDoorOpen) {
                    println("Opened the door!\n")
                } else {
                    println("The door is still locked\n")
                }
            }
        }
    }
    Thread.sleep(10000)

    "making async calls using standard functions" exampleOf {

        getNetworkCallResult(12,) { user, throwable ->
            user?.let {
                println(user)
            }
            throwable?.let {
                throwable.printStackTrace()
            }
        }
    }

    " Example of reverse operation " exampleOf {
        GlobalScope.launch(Dispatchers.Main.immediate) {
            var user = getUserSuspend(234)
            println(user)
        }
    }
*/GlobalScope.launch {
        readFileSuspend("efrgr")
    }

    println("finish")
    Thread.sleep(5000)


}

    suspend fun getUserSuspend(userId: Int): User =
        withContext(Dispatchers.Default){
            delay(1000)
            User("bilal", userId)
    }


    data class User(
        var name: String,
        var id: Int
    )



fun getNetworkCallResult(userId:Int, callback:(User?, Throwable?) -> Unit){
    thread {
        try {
            Thread.sleep(1000)
             var user = User("bilal", userId)
            callback(user, null)
        }catch (e:Exception){
            callback(null, e)
        }

    }
}

fun readFile(path: String, onReady: (File) -> Unit) {
    Thread.sleep(3000)


    onReady(File(path))
}

suspend fun readFileSuspend(path: String): File =
    suspendCoroutine {
        println(Thread.currentThread().name)
        readFile(path) { file ->
            it.resume(file)
        }
    }


 infix fun String.exampleOf(block: () -> (Unit)){
     println("$this:")
    block.invoke()

}