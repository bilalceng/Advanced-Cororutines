package com.bilalberek.advancedcoroutines


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import com.bilalberek.advancedcoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    private lateinit var dataText: TextView
    private lateinit var userText: TextView
    private lateinit var textWithoutCoroutine: TextView

    @SuppressLint("SetTextI18n")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataText = binding.dataText
        userText = binding.userText
        textWithoutCoroutine = binding.textWithoutCoroutine




        "1" exampleOf {
            var utilities  = Utilities()

            GlobalScope.launch(Dispatchers.Main) {
                dataText.text =  utilities.fetchDataFromNetwork()
            }
        }



        "2" exampleOf {

            var utilities = Utilities()
           GlobalScope.launch {
               var user = utilities.getUserSuspend(123)

               withContext(Dispatchers.Main){
                   userText.text = user.name
               }

           }
        }



        "3" exampleOf {
            textWithoutCoroutine.text = "Without Coroutine"
        }



        "3" exampleOf {
            val utilities = Utilities()

            GlobalScope.launch {
                var releaseDate = utilities.getRecordDate().await()
                withContext(Dispatchers.Main){
                    userText.text = releaseDate
                }
            }

        }



        "4" exampleOf {
            var utilities = Utilities()

            var user = utilities.vintageManner(23){ user ->

                Handler(Looper.getMainLooper()).post {
                    textWithoutCoroutine.text = user.name
                }
            }

            }


        "5" exampleOf {
            GlobalScope.launch {
                var utilities = Utilities()
                var defferedList = utilities.getUsersFromLocalDatabase()

                var userFromInternet = utilities.getUserFromInternet(2)

                    println( utilities.controllingUserExistence(userFromInternet.await(), defferedList.await()))

                }
            }


        }

}


    infix fun String.exampleOf(block: () -> Unit){
        println("$this : ")
        block.invoke()
    }






