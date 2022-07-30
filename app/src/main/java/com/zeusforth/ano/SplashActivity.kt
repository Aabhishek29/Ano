package com.zeusforth.ano

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.zeusforth.ano.authentication.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        YoYo.with(Techniques.FadeIn)
            .duration(2700)
            .repeat(0)
            .playOn(findViewById(R.id.companyLogo))
        YoYo.with(Techniques.FadeIn)
            .duration(2700)
            .repeat(0)
            .playOn(findViewById(R.id.CompanyName))
        Handler().postDelayed({ // This method will be executed once the timer is over

                startActivity(Intent(this, LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
//            getsavedValues()
//            jsonParse(agentEmail,agentPass)


        }, 3000)
    }
}