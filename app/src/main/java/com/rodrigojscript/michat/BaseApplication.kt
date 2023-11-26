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
        val user = FirebaseAuth.getInstance().currentUser
        FirebaseApp.initializeApp(this)

        SendbirdUIKit.init(object : SendbirdUIKitAdapter {
            override fun getAppId(): String {
                return "99A77805-DBA5-446B-B89B-34793144D7BC" // Specify your Sendbird application ID.
            }

            override fun getAccessToken(): String {
                return ""
            }

            override fun getUserInfo(): UserInfo {
                return object : UserInfo {
                    override fun getUserId(): String {
                        var id = ""
                        if(user?.uid == "stNqmlTAOISwqnzdX0X36kem6eA3") {
                             id = "2" // Specify your user ID.
                        } else if (user?.uid == "J6uimhq7jIXt5xV9fiJe7gmNBeJ2"){
                             id = "1"
                        } else {
                            id = "0"
                        }
                        return id
                    }

                    override fun getNickname(): String {
                        var name = ""
                        if(user?.uid == "stNqmlTAOISwqnzdX0X36kem6eA3") {
                            name = "Nico" // Specify your user ID.
                        } else if (user?.uid == "J6uimhq7jIXt5xV9fiJe7gmNBeJ2"){
                            name = "Rodri"
                        } else {
                            name = "0"
                        }
                        return name
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