package com.unscript.chatbot.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.unscript.chatbot.databinding.ChatItemBinding
import com.unscript.chatbot.databinding.FragmentDashboardBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class DashboardFragment : Fragment() {


    lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater)


        binding.send.setOnClickListener({

            var text = "\""+binding.messageText.text.toString()+"\""

            getTranscription(text)
            var chatItemBinding: ChatItemBinding = ChatItemBinding.inflate(inflater)
            chatItemBinding.outgoingMessageModule.visibility = View.GONE
            chatItemBinding.incomingText.text = text


            binding.messagelist.addView(chatItemBinding.root)
            binding.messageText.setText("")


        })

        return binding.root
    }

    fun getTranscription(id: String) {
        Toast.makeText(context, "Transcript Requested", Toast.LENGTH_SHORT)
            .show();
        val url = HttpUrl.parse("https://unscript-2020.herokuapp.com/")!!.newBuilder()
            //   val url = HttpUrl.parse("http://192.168.137.1:4555/upload_page/")!!.newBuilder()
            .addQueryParameter("message", id)
            .build()
        Log.d("TAG", "getTranscription: Requested ")


        val request: Request = Request.Builder()
            .addHeader("cache-control", "no-cache")
            .addHeader("content-type", "application/json")
            .addHeader("name", id)
            .url(url)
            .build()

        Log.d("host", "getTranscription: " + request.toString())

        val client = OkHttpClient()

        client.newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                    activity?.runOnUiThread({


                        Toast.makeText(
                            context,
                            "Failed" + e.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    })
                }


                override fun onResponse(call: Call, response: Response) {
                    activity?.runOnUiThread({

                        val json = JSONObject(response.body().string())
                        val name = json.getString("value")
                        Log.d("RAGHAV", "onResponse: " + name)

                        var chatItemBinding: ChatItemBinding = ChatItemBinding.inflate(layoutInflater)
                        chatItemBinding.incomingMessageModule.visibility = View.GONE
                        chatItemBinding.outgoingText.text = name
                        binding.messagelist.addView(chatItemBinding.root)

                        Log.d("RAGHAV", "onResponse: " + response.body().toString())

                        Toast.makeText(
                            context,
                            "Success" + name,
                            Toast.LENGTH_LONG
                        )
                            .show()
                    })
                }


            })


    }

}