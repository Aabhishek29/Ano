package com.zeusforth.ano


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException


class HomeActivity : AppCompatActivity()  {
    private val TAG = "HomeActivity";


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//        jsonParse()
    }


    }


//
//        try{
//            var req:JSONObject = JSONObject({"userName":"D1",
//                "name":"D1",
//                "email":"D1@gmail.com",
//                "password":"Abhi123",
//                "phone":1234567871,
//                "organizationEmail":"D1@gmail.com",
//                "profileUrl":"https://www.youtube.com/watch?v=R4mCK5P7WZE",
//                "authenticated":"true"})
//            val request = JsonObjectRequest(Request.Method.POST, url, null, { response ->
//                try {
//                    Log.e(TAG, response.toString())
////            Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
//                    val intent = Intent(this, HomeActivity::class.java)
//                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

//            sharedPreferences = baseContext.getSharedPreferences("
//            MY_PREF", Context.MODE_PRIVATE)
//            val editor = sharedPreferences.edit()
//            editor.putString("agent_id", agentId)
//            editor.putString("company_id", companyId)
//            editor.putString("name", name)
//            editor.putString("pass", pass)
//            editor.apply()

//
//                    startActivity(intent)
//                } catch (e: JSONException) {
//                    Log.e(TAG, e.toString())
//                    e.printStackTrace()
//                }
//            }, { error ->
//                error.printStackTrace()
//                Log.e(TAG,error.toString())
//                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
//                username.text.clear()
//                password.text.clear()
//                progress_bar.visibility = View.INVISIBLE
//            })
//            requestQueue?.add(request)
//        }catch (e:Error){
//            Log.e(TAG,e.toString())