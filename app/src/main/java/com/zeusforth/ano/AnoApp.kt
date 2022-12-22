package com.zeusforth.ano

import android.app.Application
import android.content.Context

class AnoApp : Application() {
    private var userId: String? = null;
    private var userName: String? = null;
    private var userRealName:String? = null;
    private var userPassword: String? = null;
    private var instance: AnoApp? = null;


    public fun getInstance(): AnoApp? {
        return instance
    }
    public fun setInstance(){
        this.instance = this;
    }
    public fun setUserName(userName:String){
        this.userName = userName
    }
    public fun setUserRealName(userRealName:String){
        this.userRealName = userRealName
    }
    public fun setUserId(userId:String){
        this.userId = userId
    }
    public fun setUserPassword(userPassword:String){
        this.userPassword = userPassword
    }

    public fun getUserName(): String? {
        return userName
    }
    public fun getUserRealName(): String? {
        return userRealName
    }
    public fun getUserId(): String? {
        return userId
    }
    public fun getUserPassword(): String? {
        return userPassword
    }

    override fun onCreate() {
        super.onCreate()
        setInstance()


    }



}