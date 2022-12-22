package com.zeusforth.ano.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.zeusforth.ano.R

class SignupActivity : AppCompatActivity() {

    private lateinit var signUpOptionsLy:LinearLayout
    private lateinit var fragmentSignUpContainer:FragmentContainerView
    private lateinit var phoneSignUpBtn:Button
    private lateinit var emailSignUpBtn:Button
    private lateinit var anoySignUp:Button
    private lateinit var signUpType:String



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
        emailSignUpBtn = findViewById(R.id.emailSignUpBtn)
        anoySignUp = findViewById(R.id.anoySignUp)

        phoneSignUpBtn.setOnClickListener {

            signUpType = "phone"
            changeFragments(savedInstanceState)

        }
        emailSignUpBtn.setOnClickListener {

            signUpType = "email"
            changeFragments(savedInstanceState)

        }
        anoySignUp.setOnClickListener {

            signUpType = "anoy"
            changeFragments(savedInstanceState)

        }



    }

    private fun changeFragments(savedInstanceState: Bundle?) {
        signUpOptionsLy.visibility = View.GONE
        fragmentSignUpContainer.visibility = View.VISIBLE
        when (signUpType) {
            "phone" ->  {

                showPhoneFragment(savedInstanceState)



            }
            "email" -> {
                showEmailFragment(savedInstanceState)

            }
            "anoy" -> {

            }
            else -> {
                showAnoyFragment(savedInstanceState)

            }
        }

    }

    private fun showPhoneFragment(savedInstanceState: Bundle?) {
        // To connect the fragment with activity
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setCustomAnimations(
                    R.anim.slide_in, R.anim.fadeout,
                    R.anim.fadein,R.anim.slide_out
                )
                setReorderingAllowed(true)
                add<SignUpPhoneFragment>(R.id.fragmentSignUpContainer)
            }
        }else{
            val SignUpPhone: Fragment = SignUpPhoneFragment()
            this.supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true).replace(R.id.fragmentSignUpContainer,SignUpPhone).commit()

        }
    }

    private fun showEmailFragment(savedInstanceState: Bundle?) {
        // To connect the fragment with activity
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setCustomAnimations(
                    R.anim.slide_in, R.anim.fadeout,
                    R.anim.fadein,R.anim.slide_out
                )
                setReorderingAllowed(true)
                add<SignUpEmailFragment>(R.id.fragmentSignUpContainer)
            }
        }
    }

    private fun showAnoyFragment(savedInstanceState: Bundle?) {
        // To connect the fragment with activity
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setCustomAnimations(
                    R.anim.slide_in, R.anim.fadeout,
                    R.anim.fadein,R.anim.slide_out
                )
                setReorderingAllowed(true)
//                add<>(R.id.fragmentSignUpContainer)
            }
        }
    }

}