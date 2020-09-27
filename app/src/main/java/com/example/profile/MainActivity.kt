package com.example.profile

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 1
    var bitmap:Bitmap?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val awesomeValidation= AwesomeValidation(ValidationStyle.BASIC)
        awesomeValidation.addValidation(this,R.id.etName,RegexTemplate.NOT_EMPTY,R.string.validateName)
        awesomeValidation.addValidation(this,R.id.etPhone,"[0-9]{9}$",R.string.validatePhone)
        awesomeValidation.addValidation(this,R.id.etUbication,RegexTemplate.NOT_EMPTY,R.string.validateUbication)
        awesomeValidation.addValidation(this,R.id.etWeb, Patterns.WEB_URL,R.string.validateWeb)



        val btnPicture:Button= findViewById<Button>(R.id.btnPicture)
        btnPicture.setOnClickListener() {
            val intent:Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }

        val btnGenerate:Button= findViewById<Button>(R.id.btnGenerate)
        btnGenerate.setOnClickListener {
            if(awesomeValidation.validate()){
                Toast.makeText(getApplicationContext(),"Goes",Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, ProfileActivity::class.java)
                intent.putExtra("PICTURE", bitmap)
                intent.putExtra("NAME", etName.text.toString())
                intent.putExtra("PHONE", etPhone.text.toString())
                intent.putExtra("UBICATION", etUbication.text.toString())
                intent.putExtra("WEB", etWeb.text.toString())
                startActivity(intent)
            } else{
                Toast.makeText(getApplicationContext(),"Valide datos",Toast.LENGTH_SHORT).show()
            }
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            bitmap = data?.extras?.get("data") as Bitmap?
            ivProfile.setImageBitmap(bitmap)
        }
    }
}