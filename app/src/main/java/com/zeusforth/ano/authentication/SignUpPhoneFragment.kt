package com.zeusforth.ano.authentication

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.zeusforth.ano.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SignUpPhone : Fragment() {

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
        signup.setOnClickListener{
            Log.d(TAG,"Signup call to Fragment OTP")
            val otpFragment: Fragment = OtpFragment()
            activity?.supportFragmentManager?.beginTransaction()?.setReorderingAllowed(true)?.replace(R.id.fragment_sign_up_phone,otpFragment)?.commit()

        }

        val login_activity_btn = root.findViewById<TextView>(R.id.login_activity_btn)
        login_activity_btn.setOnClickListener {
            startActivity(Intent(root.context,LoginActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK),
                ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
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
            SignUpPhone().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}