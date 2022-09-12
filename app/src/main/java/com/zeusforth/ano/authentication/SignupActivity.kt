package com.zeusforth.ano.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.zeusforth.ano.R
import com.zeusforth.ano.authentication.SignUpPhone

class SignupActivity : AppCompatActivity() {

    private lateinit var signUpOptionsLy:LinearLayout
    private lateinit var fragmentSignUpContainer:FragmentContainerView
    private lateinit var phoneSignUpBtn:Button



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_signup)

        //To change color of statusBar
        val window: Window = getWindow()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(baseContext , R.color.primary)


        signUpOptionsLy = findViewById(R.id.signUpOptionsLy)
        fragmentSignUpContainer = findViewById(R.id.fragmentSignUpContainer)
        phoneSignUpBtn = findViewById(R.id.phoneSignUpBtn)


        // To connect the fragment with activity
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setCustomAnimations(
                     R.anim.slide_in, R.anim.fadeout,
                    R.anim.fadein,R.anim.slide_out
                )
                setReorderingAllowed(true)
                add<SignUpPhone>(R.id.fragmentSignUpContainer)
            }
        }
    }

}