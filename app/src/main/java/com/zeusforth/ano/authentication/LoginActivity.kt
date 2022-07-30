package com.zeusforth.ano.authentication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.zeusforth.ano.HomeActivity
import com.zeusforth.ano.R


class LoginActivity : AppCompatActivity() {
    private var requestQueue: RequestQueue? = null
    lateinit var username: EditText
    lateinit var password:EditText
    lateinit var signup_btn : TextView
    lateinit var progress_bar: ProgressBar
    lateinit var companyId:String
    lateinit var agentId:String
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_login)

        val window: Window = getWindow()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(ContextCompat.getColor(baseContext , R.color.primary))

        val btn1 = findViewById<Button>(R.id.LoginBtn)
        username = findViewById<EditText>(R.id.username)
        password = findViewById<EditText>(R.id.user_password)
        signup_btn = findViewById(R.id.signup_activity_btn)


        progress_bar = findViewById<ProgressBar>(R.id.progress_bar)
        requestQueue = Volley.newRequestQueue(this)
        btn1.setOnClickListener {
            progress_bar.visibility = View.VISIBLE

            jsonParse(username.text.toString(),password.text.toString())

        }

        signup_btn.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }

    }
    private fun jsonParse(name: String , pass: String){
//        val url = "https://staging-webservice.supportgenie.io/v3/agent/validate?email=${name}&&password=${pass}"
//        val request = JsonObjectRequest(Request.Method.GET, url, null, {
//                response ->try {
//            val JsonData = response.getJSONObject("agent")
//            companyId = JsonData.getString("companyId").toString()
//            agentId = JsonData.getString("userId").toString()
//            Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
//            val intent = Intent(this,HomeActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
////            val intent = Intent(this,TempHomeActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//
//
//            sharedPreferences = baseContext.getSharedPreferences("MY_PREF", Context.MODE_PRIVATE)
//            val editor = sharedPreferences.edit()
//            editor.putString("agent_id", agentId)
//            editor.putString("company_id", companyId)
//            editor.putString("name", name)
//            editor.putString("pass", pass)
//            editor.apply()
//
//            progress_bar.visibility = View.INVISIBLE
//            startActivity(intent)
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        }, { error -> error.printStackTrace()
//            Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show()
//            username.text.clear()
//            password.text.clear()
//            progress_bar.visibility = View.INVISIBLE
//        })
//        requestQueue?.add(request)
        progress_bar.visibility = View.GONE
        startActivity(Intent(this, HomeActivity::class.java))
    }
}