package com.bilalberek.advancedcoroutines

import com.bilalberek.advancedcoroutines.Models.User
import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Utilities() {

        var bilal = User("bilal",1)
        var bedia = User("bedia",2)
        var umut = User("umut",3)
        var enes = User("enes",4)
        var ahmet = User("ahmet",5)
        var mustafa = User("mustafa",6)

   @OptIn(DelicateCoroutinesApi::class)
   suspend fun fetchDataFromNetwork(): String = suspendCoroutine <String>{ continuation ->

    GlobalScope.launch {
        delay(4000)
        val data  = "Requested data"
        continuation.resume(data)
    }

    }

    suspend fun getUserSuspend(userId: Int): User = withContext(Dispatchers.IO){
        delay(2000)
        User("bilal", userId)
    }



    fun getRecordDate() = GlobalScope.async {
        delay(5000)
        var releaseDate = "22/10/1972"
        releaseDate
    }


    fun vintageManner(userId: Int, onReady: (User) -> Unit){
        thread {
            Thread.sleep(10000)
            var user = when(userId){
                1 -> User("tarik" , userId)
                else -> User("oguzhan", userId)

            }
            onReady.invoke(user)
        }

    }

    fun getUsersFromLocalDatabase() = GlobalScope.async {
        delay(3000)

        var list = listOf(
            bilal,
            bedia,
            enes,
            ahmet,
            umut,
            mustafa
        )
        list
    }

   fun  getUserFromInternet(userId: Int) = GlobalScope.async {
       delay(1000)

       var user = when(userId){
           1 -> bilal
           2 -> bedia
           3 -> enes
           4 -> ahmet
           5 -> umut
           6 -> mustafa
           else -> User("",0)

       }
       user
   }

    fun controllingUserExistence(
        user: User,
        userList:List<User>
    ) = user in userList

}