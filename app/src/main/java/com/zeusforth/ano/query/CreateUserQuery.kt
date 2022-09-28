package com.zeusforth.ano.query
//
//import com.apollographql.apollo3.api.Response
//import com.apollographql.apollo3.exception.ApolloException
//import javax.security.auth.callback.Callback
//
//
//
//
//class CreateUserQuery {
//
//    fun mutation(message: String?) {
//        val createUserQuery: createUsers = .builder()
//            .sessionId(chatChannelSessionId)
//            .messageId(getUUID())
//            .message(message)
//            .sender(sender)
//            .senderType("customer")
//            .mimeType("text/plain")
//            .build()
//        apolloClient
//            .mutate(addMessageMutation)
//            .enqueue(object : Callback<AddMessageMutation.Data?>() {
//                fun onResponse(response: Response<.Data?>) {
//                    Log.i("response", response.toString())
//                }
//
//                fun onFailure(e: ApolloException) {
//                    Log.e("response", e.message, e)
//                }
//            })
//    }
//}