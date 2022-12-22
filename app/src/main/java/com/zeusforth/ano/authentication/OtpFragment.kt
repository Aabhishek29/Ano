package com.zeusforth.ano.authentication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.zeusforth.ano.HomeActivity
import java.util.concurrent.TimeUnit
import com.zeusforth.ano.R



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OtpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OtpFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val TAG: String = "OtpFragment"


    private lateinit var userName:String
    private lateinit var userPassword:String
    private lateinit var userPhoneNumber:String
    private lateinit var otpId:String
    private lateinit var mAuth: FirebaseAuth
    private lateinit var checkOtp:Button
    private lateinit var otp1:EditText
    private lateinit var otp2:EditText
    private lateinit var otp3:EditText
    private lateinit var otp4:EditText
    private lateinit var otp5:EditText
    private lateinit var otp6:EditText
    private lateinit var sharedPreferences: SharedPreferences

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
        val root =inflater.inflate(R.layout.fragment_otp, container, false)
        userName = arguments?.getString("userName").toString()
        userPassword = arguments?.getString("userPassword").toString()
        userPhoneNumber = arguments?.getString("userPhoneNumber").toString()
        mAuth = FirebaseAuth.getInstance();

        Log.i(TAG, "onCreateView: username: ${userName}  password ${userPassword} ph_no ${userPhoneNumber}")

        checkOtp = root.findViewById(R.id.check_otp)
        otp1 =root.findViewById(R.id.otp_1)
        otp2 =root.findViewById(R.id.otp_2)
        otp3 =root.findViewById(R.id.otp_3)
        otp4 =root.findViewById(R.id.otp_4)
        otp5 =root.findViewById(R.id.otp_5)
        otp6 =root.findViewById(R.id.otp_6)

//        checkOtp.setOnClickListener{
//
//            val profileFragment: Fragment =
//                ProfileFragment()
//            activity?.supportFragmentManager?.beginTransaction()?.setReorderingAllowed(true)?.replace(R.id.fragmentSignUpContainer,profileFragment)?.commit()
//
//        }

        Log.i(TAG, "onCreateView: ph no. $userPhoneNumber")
        verifyOtp(userPhoneNumber)

        checkOtp.setOnClickListener{

            var otp_code:String = otp1.text.toString()+otp2.text.toString()+otp3.text.toString()+otp4.text.toString()+otp5.text.toString()+otp6.text.toString()
            otp_code =otp_code.trim()
            otp_code = otp_code.replace(" ","")
            Log.i(TAG, "onCreateView: otp code. $otp_code")

            if (otp_code.length==6){
                checkOtp.isActivated = false
                val credential = PhoneAuthProvider.getCredential(otpId,otp_code)
                signInWithPhoneAuthCredential(credential)

            }else{
                Toast.makeText(activity,"Invalid Otp Code",Toast.LENGTH_SHORT).show()
            }

        }



//        otp authentication
//        otpAuthentication( ph_number)
        return root
    }


    private fun verifyOtp(pnumber: String) {
        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(pnumber) // Phone number to verify
            .setTimeout(30L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity()) // Activity (for callback binding)
            .setCallbacks(object : OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                    Log.i(TAG, "onVerificationCompleted: In")
                    signInWithPhoneAuthCredential(phoneAuthCredential)
                }

                override fun onCodeSent(s: String, forceResendingToken: ForceResendingToken) {
                    otpId = s
//                    val intent = Intent(this@SignUp, ProcessOtp::class.java)
//                    intent.putExtra("pnumber", pnumber)
//                    intent.putExtra("password", password)
//                    intent.putExtra("otpid", otpid)
//                    startActivity(intent)
//                    finish()
                    //                                super.onCodeSent(s, forceResendingToken);

                    Log.i(TAG, "onCodesent: In "+otpId)

                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    Log.i(TAG, "failed verification: In ")

                }
            }) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity(),
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.i(TAG, "onsuccessful:  Otp Verification")

                        sharedPreferences = requireActivity().baseContext.getSharedPreferences(R.string.user_data_pref_file.toString(), Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("userName", userName)
                        editor.putString("userPassword", userPassword)
                        editor.apply()

                        val toast = Toast.makeText(activity, "Account successfully created!", Toast.LENGTH_SHORT)
                        toast.show()

                        val intent = Intent(activity, HomeActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)



                        //                            FirebaseUser user = task.getResult().getUser();
                    } else {
                        // Sign in failed, display a message and update the UI
                        //                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        val toast = Toast.makeText(activity, "Sign in error", Toast.LENGTH_SHORT)
                        toast.show()
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            val toast2 = Toast.makeText(
                                activity,
                                "Invalid OTP entered",
                                Toast.LENGTH_SHORT
                            )
                            toast2.show()
                        }
                    }
                })
    }

//    private fun otpAuthentication(phoneNumber: String) {
//
//        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                // This callback will be invoked in two situations:
//                // 1 - Instant verification. In some cases the phone number can be instantly
//                //     verified without needing to send or enter a verification code.
//                // 2 - Auto-retrieval. On some devices Google Play services can automatically
//                //     detect the incoming verification SMS and perform verification without
//                //     user action.
//                Log.d(TAG, "onVerificationCompleted:$credential")
//                signInWithPhoneAuthCredential(credential)
//            }
//
//            override fun onVerificationFailed(e: FirebaseException) {
//                // This callback is invoked in an invalid request for verification is made,
//                // for instance if the the phone number format is not valid.
//                Log.w(TAG, "onVerificationFailed", e)
//
//                if (e is FirebaseAuthInvalidCredentialsException) {
//                    // Invalid request
//                } else if (e is FirebaseTooManyRequestsException) {
//                    // The SMS quota for the project has been exceeded
//                }
//
//                // Show a message and update the UI
//            }
//
//            override fun onCodeSent(
//                verificationId: String,
//                token: PhoneAuthProvider.ForceResendingToken
//            ) {
//                // The SMS verification code has been sent to the provided phone number, we
//                // now need to ask the user to enter the code and then construct a credential
//                // by combining the code with a verification ID.
//                Log.d(TAG, "onCodeSent:$verificationId")
//
//                // Save verification ID and resending token so we can use them later
//                storedVerificationId = verificationId
//                resendToken = token
//            }
//        }
//
//
//
//        val options = PhoneAuthOptions.newBuilder(auth)
//            .setPhoneNumber(phoneNumber)       // Phone number to verify
//            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//            .setActivity(activity!!)                 // Activity (for callback binding)
//            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
//            .build()
//        PhoneAuthProvider.verifyPhoneNumber(options)







    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OtpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OtpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}