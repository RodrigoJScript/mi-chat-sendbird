package com.rodrigojscript.michat

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.sendbird.android.exception.SendbirdException
import com.sendbird.android.handler.InitResultHandler
import com.sendbird.uikit.SendbirdUIKit
import com.sendbird.uikit.adapter.SendbirdUIKitAdapter
import com.sendbird.uikit.interfaces.UserInfo


class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        val user = FirebaseAuth.getInstance().currentUser

        SendbirdUIKit.init(object : SendbirdUIKitAdapter {
            override fun getAppId(): String {
                return "4229ACD5-B3BD-45DF-BB1E-59E1C961DEDF" // Specify your Sendbird application ID.
            }

            override fun getAccessToken(): String {
                return ""
            }

            override fun getUserInfo(): UserInfo {
                return object : UserInfo {
                    override fun getUserId(): String {
                        return when (user?.uid) {
                            "stNqmlTAOISwqnzdX0X36kem6eA3" -> "2"
                            "J6uimhq7jIXt5xV9fiJe7gmNBeJ2" -> "1"
                            else -> throw IllegalStateException("User ID not recognized")
                        }
                    }

                    override fun getNickname(): String {
                        return when (user?.uid) {
                            "stNqmlTAOISwqnzdX0X36kem6eA3" -> "Nico"
                            "J6uimhq7jIXt5xV9fiJe7gmNBeJ2" -> "Rodri"
                            else -> throw IllegalStateException("User nickname not recognized")
                        }
                    }

                    override fun getProfileUrl(): String {
                        return ""
                    }
                }
            }

            override fun getInitResultHandler(): InitResultHandler {
                return object : InitResultHandler {
                    override fun onMigrationStarted() {
                        // DB migration has started.
                    }

                    override fun onInitFailed(e: SendbirdException) {
                        // If DB migration fails, this method is called.
                    }

                    override fun onInitSucceed() {
                        // If DB migration is successful, this method is called and you can proceed to the next step.
                        // In the sample app, the `LiveData` class notifies you on the initialization progress
                        // And observes the `MutableLiveData<InitState> initState` value in `SplashActivity()`.
                        // If successful, the `LoginActivity` screen
                        // Or the `HomeActivity` screen will show.
                    }
                }
            }
        }, this)
    }
}