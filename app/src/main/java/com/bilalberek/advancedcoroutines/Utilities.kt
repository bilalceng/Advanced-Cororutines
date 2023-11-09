package com.bilalberek.advancedcoroutines

import com.bilalberek.advancedcoroutines.Models.User
import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Utilities() {

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
}