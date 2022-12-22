package com.zeusforth.ano.authentication

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.apollographql.apollo3.ApolloClient
import com.google.android.material.textfield.TextInputEditText
import com.zeusforth.CreateUsersMutation
import com.zeusforth.ano.HomeActivity
import com.zeusforth.ano.R
import org.json.JSONException


class LoginActivity : AppCompatActivity() {
    private val TAG : String  = "LoginActivity";
    private var requestQueue: RequestQueue? = null
    lateinit var userName: TextInputEditText
    lateinit var userPassword:TextInputEditText
    lateinit var signup_btn : TextView
    lateinit var progress_bar: ProgressBar
    lateinit var btn1:Button
    var apolloClient: ApolloClient? = null

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_login)

        val window: Window = getWindow()
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(baseContext , R.color.primary)

        btn1 = findViewById(R.id.LoginBtn)
        userName = findViewById(R.id.username)
        userPassword = findViewById(R.id.user_password)
        signup_btn = findViewById(R.id.signup_activity_btn)


        progress_bar = findViewById(R.id.progress_bar)
        requestQueue = Volley.newRequestQueue(this)
        btn1.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            btn1.isEnabled = false

            jsonParse(userName.text.toString(),userPassword.text.toString())

        }

        mutation()
        signup_btn.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }

    }

     fun mutation() {
         Log.d(TAG,"apollo response: start")

         val createUsersMutation: CreateUsersMutation = CreateUsersMutation("P1","p","p1@gmail.com",
            "123456","1234661231","p1org@gmail.com","")
        apolloClient = ApolloClient.Builder().serverUrl("https://amsportalapp.herokuapp.com/graphql").build()
         lifecycleScope.launchWhenResumed {
               val response=  apolloClient!!.mutation(createUsersMutation).execute()
                Log.d(TAG,"apollo response: "+response.toString())

            }

        }


    private fun jsonParse(name: String , pass: String) {
        Log.e(TAG, "${name} and ${pass}")
        val url =
            "https://amsportalapp.herokuapp.com/api/users/auth?username=${name}&password=${pass}"
        try{
        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
            try {
                Log.e(TAG, response.toString())
                if (response.get("status") == "true"){
                    if (response.get("authenticated") == "true"){
                        Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeActivity::class.java)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

                        sharedPreferences = baseContext.getSharedPreferences(R.string.user_data_pref_file.toString(), Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("userName", name)
                        editor.putString("userPassword", pass)
                        editor.apply()


                        progress_bar.visibility = View.GONE
                        btn1.isEnabled = true

                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())

                    }else{
                        Toast.makeText(this,"Login Failed, User is Blocked",Toast.LENGTH_SHORT).show()
                        progress_bar.visibility = View.GONE
                        btn1.isEnabled = true
                    }



                }else{
                    Toast.makeText(this,"Login Failed, User not found",Toast.LENGTH_SHORT).show()
                    progress_bar.visibility = View.GONE
                    btn1.isEnabled = true

                }


            } catch (e: JSONException) {
                Log.e(TAG, e.toString())
                e.printStackTrace()
                progress_bar.visibility = View.GONE
                btn1.isEnabled = true

            }
        }, { error ->
            error.printStackTrace()
            Log.e(TAG,error.toString())
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            userName.text!!.clear()
            userPassword.text!!.clear()
            progress_bar.visibility = View.GONE
            btn1.isEnabled = true

        })
            requestQueue?.add(request)
    }catch (e:Error){
        Log.e(TAG,e.toString())

    }
    }
}