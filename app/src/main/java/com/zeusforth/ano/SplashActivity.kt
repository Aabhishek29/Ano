package com.zeusforth.ano

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.zeusforth.ano.authentication.LoginActivity


class SplashActivity : AppCompatActivity() {

    var user: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        user = FirebaseAuth.getInstance().getCurrentUser()
        YoYo.with(Techniques.FadeIn)
            .duration(2700)
            .repeat(0)
            .playOn(findViewById(R.id.companyLogo))
        YoYo.with(Techniques.FadeIn)
            .duration(2700)
            .repeat(0)
            .playOn(findViewById(R.id.CompanyName))
        Handler().postDelayed({ // This method will be executed once the timer is over

            if (user != null){
                Log.i("auth", "Signed in user : ${user!!.phoneNumber}")
                startActivity(Intent(this, HomeActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))


            }else
            {
                startActivity(Intent(this, LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))

            }
//            getsavedValues()
//            jsonParse(agentEmail,agentPass)


        }, 2000)
    }
}