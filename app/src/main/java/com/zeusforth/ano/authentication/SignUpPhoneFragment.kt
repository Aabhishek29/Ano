package com.zeusforth.ano. authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.hbb20.CountryCodePicker
import com.zeusforth.ano.R
import com.zeusforth.ano.authentication.LoginActivity
import com.zeusforth.ano.authentication.OtpFragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SignUpPhoneFragment : Fragment() {

    private lateinit var userName:String ;
    private lateinit var userPassword:String ;
    private lateinit var userPhoneNumber:String ;
    private var check:Boolean = false;
    private var param1: String? = null
    private var param2: String? = null
    private val TAG: String = "SignUPFragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val root = inflater.inflate(R.layout.fragment_sign_up_phone, container, false)

        val signup = root.findViewById<Button>(R.id.signup)
        val username_v = root.findViewById<TextInputEditText>(R.id.username)
        val password_v = root.findViewById<TextInputEditText>(R.id.password)
        val confirm_password_v = root.findViewById<TextInputEditText>(R.id.confirm_password)
        val ph_number_v = root.findViewById<TextInputEditText>(R.id.ph_number)
        val ccp = root.findViewById<CountryCodePicker>(R.id.ccp);
        ccp.registerCarrierNumberEditText(ph_number_v);

        signup.setOnClickListener{
            Log.d(TAG,"Signup call to Fragment OTP")
            check = true;
            if (!username_v.text?.isEmpty()!!){
                userName = username_v.text.toString()

            }else{
                Toast.makeText(context,"username is required",Toast.LENGTH_SHORT).show()
                check = false
            }

            if (password_v.text!!.isNotEmpty() && password_v.text!!.length >= 6){
                if(password_v.text.toString().equals(confirm_password_v.text.toString())){
                    userPassword = password_v.text.toString()
                }else{

                    Toast.makeText(context,"Password does not match!",Toast.LENGTH_SHORT).show()
                    check =false
                }

            }else {
                Toast.makeText(context,"Password is required, it should be greater than 6 character",Toast.LENGTH_SHORT).show()
                check = false
            }

            if (ph_number_v.text!!.isNotEmpty()){
                Log.d(TAG,"Phone"+ph_number_v.text)

                userPhoneNumber = ccp.fullNumberWithPlus.replace(" ","");


            }else{

                check = false
                Toast.makeText(context,"Valid Phone number required",Toast.LENGTH_SHORT).show()

            }


            var data:Bundle = Bundle()
            if (check){
                data.putString("userName",userName)
                data.putString("userPassword",userPassword)
                data.putString("userPhoneNumber",userPhoneNumber)
                Log.d(TAG,"userPhoneNumber "+userPhoneNumber)


                val otpFragment: Fragment = OtpFragment()
                otpFragment.arguments = data
                activity?.supportFragmentManager?.beginTransaction()?.setReorderingAllowed(true)?.replace(R.id.fragmentSignUpContainer,otpFragment)?.commit()
            }else{

                Toast.makeText(context,"Please fill all details",Toast.LENGTH_SHORT).show()

            }







        }

        val login_activity_btn = root.findViewById<TextView>(R.id.login_activity_btn)
        login_activity_btn.setOnClickListener {
            startActivity(Intent(root.context, LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }
        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUpPhone.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpPhoneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}