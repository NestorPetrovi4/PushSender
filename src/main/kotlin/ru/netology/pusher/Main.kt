package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val message = Message.builder()
        .putData("action", "LIKES")
        .putData("content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)

    val messageNewPost = Message.builder()
        .putData("action", "NEWPOST")
        .putData("content", """{
        "id": 234,
        "author": "Nesterov Oleg",
        "published": "Friday",
        "content": "Изучили отправку уведомлений в приложение через Firebase",
        "likes": 3,
        "viewOpen": 1,
        "videoURL": ""}""".trimIndent())
        .setToken(token)
        .build()

     FirebaseMessaging.getInstance().send(messageNewPost)

}