package com.zeusforth.ano.chatscreens

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apollographql.apollo3.ApolloClient
import com.zeusforth.ano.R

class ChatActivity : AppCompatActivity() {

    private lateinit var chat_recycler_view:RecyclerView
    private lateinit var layoutManagerRv: LinearLayoutManager
    private lateinit var chatScreenAdapter:ChatScreenAdapter
    private lateinit var apolloClient : ApolloClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        chat_recycler_view = findViewById(R.id.chat_recycler_view)
        layoutManagerRv = LinearLayoutManager(this)
        apolloClient = ApolloClient.builder()
            .serverUrl("https://amsportalapp.herokuapp.com/graphql").build()

        prepareContactRV()

//        fetchData()
    }

//    private fun fetchData() {
//        lifecycleScope.launchWhenResumed {
//            val response = apolloClient.query(AllUsersQuery()).execute()
//            Log.d("allUsers Data", "Success ${response.data}")
//        }
//    }

    private fun prepareContactRV() {
        chatScreenAdapter = ChatScreenAdapter(this)
        chat_recycler_view.layoutManager = layoutManagerRv
        chat_recycler_view.adapter = chatScreenAdapter
    }


}