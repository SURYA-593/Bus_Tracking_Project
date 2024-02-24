package com.demo.bustracking.data

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Handle the incoming message
        val data = remoteMessage.data
        val value = data["value"]?.toIntOrNull()
        value?.let {
            //myViewModel.handleRealTimeUpdate(value)
            println(it)
        }
    }
}