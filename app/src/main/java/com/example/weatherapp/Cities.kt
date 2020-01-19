package com.example.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Cities : AppCompatActivity() {

    val TAG = "com.example.weatherapp.cities.city"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        val btnSpain = findViewById<Button>(R.id.btnWeather)
        val etLat = findViewById<EditText>(R.id.etLat)
        val etLong = findViewById<EditText>(R.id.etLon)

        btnSpain.setOnClickListener(View.OnClickListener {

            if (etLat.text.toString().isNullOrEmpty() || etLong.text.toString().isNullOrEmpty()){
                Toast.makeText(this, "Debe introducir la latitude y/o longitude", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("lat", etLat.text.toString())
                intent.putExtra("long", etLong.text.toString())
                startActivity(intent)
            }
        })


    }
}
