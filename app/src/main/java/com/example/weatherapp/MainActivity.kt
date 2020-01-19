package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.Model.City
import com.google.gson.Gson
import java.io.IOException

class MainActivity : AppCompatActivity() {

    var tvCity:TextView? = null
    var tvGrade:TextView? = null
    var tvStatus:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCity = findViewById(R.id.tvCity)
        tvGrade = findViewById(R.id.tvGrade)
        tvStatus = findViewById(R.id.tvStatus)

        val lat = intent.getStringExtra("lat")
        val long = intent.getStringExtra("long")

        if (Network.ThereIsNetwork(this)){
            requestVolley("http://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&appid=f8af5693fccc42bf70e695927328e73a&lang=es")
        }else{
            Toast.makeText(this, "Make sure there is an internet connection!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun requestVolley(url:String){
        val queue = Volley.newRequestQueue(this)

        val request = StringRequest(Request.Method.GET, url, Response.Listener<String>{
                response ->
            try {
                Log.d("Volley", response)
                val gson = Gson()
                val city = gson.fromJson(response, City::class.java)
                tvCity?.text = city.name
                tvGrade?.text = city.main?.temp.toString()
                tvStatus?.text = city.weather?.get(0)?.description
            }catch (e: IOException){

            }
        }, Response.ErrorListener {  })

        queue.add(request)

    }


}
