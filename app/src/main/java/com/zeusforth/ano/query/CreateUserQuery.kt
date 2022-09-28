package com.zeusforth.ano.query
////
////import com.apollographql.apollo3.api.Response
////import com.apollographql.apollo3.exception.ApolloException
////import javax.security.auth.callback.Callback
//
//import android.util.Log
//import com.apollographql.apollo3.ApolloClient
//import com.apollographql.apollo3.exception.ApolloException
//import com.zeusforth.CreateUsersMutation
//import javax.security.auth.callback.Callback
//
//
//class CreateUserQuery {
//
//    var apolloClient: ApolloClient? = null
//    val TAG:String = "CreateUser"
//
//    public fun mutation() {
//    val createUsersMutation:CreateUsersMutation = CreateUsersMutation("P1","p","p1@gmail.com",
//    "123456","1234661231","p1org@gmail.com","")
//    apolloClient = ApolloClient.Builder().serverUrl("https://amsportalapp.herokuapp.com/graphql").build()
//    val response = try {
//        lifecycleScope.launchWhenResumed {
////            val response = apolloClient.query(AllUsersQuery()).execute()
////            Log.d("allUsers Data", "Success ${response.data}")
////        }
//        apolloClient!!.mutation(createUsersMutation).execute()
//    } catch(e: ApolloException) {
//        Log.d(TAG,"apollo response: error "+e.message)
//
//        // handle error
//    }
//    Log.d(TAG,"apollo response: "+response.toString())
//
//}
//}