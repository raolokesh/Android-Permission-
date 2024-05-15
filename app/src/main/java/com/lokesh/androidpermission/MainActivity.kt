package com.lokesh.androidpermission

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.lokesh.androidpermission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var CAMERA_PERMISSION_CODE: Int = 1
    private var ACCESS_FINE_LOCATION_CODE: Int = 2
    private var ACCESS_MEDIA_LOCATION_CODE: Int = 3
    private var BLUETOOTH_CODE: Int = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
//        enableEdgeToEdge()
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        binding.cameraButton.setOnClickListener(this)
        binding.locationButton.setOnClickListener(this)
        binding.mediaButton.setOnClickListener(this)
        binding.bluetoothButton.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v) {
            binding.cameraButton -> cameraPermission()
            binding.locationButton -> locationPermission()
            binding.mediaButton -> mediaPermission()
            binding.bluetoothButton -> bluetoothPermission()
        }
    }

    private fun bluetoothPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.BLUETOOTH_ADMIN
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.BLUETOOTH_ADMIN),
                BLUETOOTH_CODE
            )
        }else{
            Toast.makeText(this,"Bluetooth permission is granted",Toast.LENGTH_LONG).show()
        }
    }

    private fun mediaPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_MEDIA_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_MEDIA_LOCATION),
                ACCESS_MEDIA_LOCATION_CODE
            )
        } else {
            Toast.makeText(this, "Media Location permission is granted", Toast.LENGTH_LONG).show()
        }
    }

    private fun locationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                ACCESS_FINE_LOCATION_CODE
            )
        } else {
            Toast.makeText(this, "Location permission is granted", Toast.LENGTH_LONG).show()
        }
    }

    private fun cameraPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )

        } else {
            Toast.makeText(this, "Camera permission is granted", Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "Camera Permission is Granted", Toast.LENGTH_LONG).show()

                } else {

                    Toast.makeText(this, "Camera Permission is Denial", Toast.LENGTH_LONG).show()


                }
            }

            ACCESS_FINE_LOCATION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Location permission is granted", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Location permission is Denial", Toast.LENGTH_LONG).show()
                }
            }

            ACCESS_MEDIA_LOCATION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Media Location permission is granted", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(this, "Media Location permission is Denial", Toast.LENGTH_LONG)
                        .show()
                }
            }

            BLUETOOTH_CODE ->{
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Bluetooth permission is granted", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(this, "Bluetooth permission is Denial", Toast.LENGTH_LONG)
                        .show()
                }
            }

        }
    }
}