package com.example.profile

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        val name = getIntent().getStringExtra("NAME")
        val phone = getIntent().getStringExtra("PHONE")
        val ubication = getIntent().getStringExtra("UBICATION")
        val web = getIntent().getStringExtra("WEB")



        findViewById<TextView>(R.id.tvNameResponse).setText(name)
        findViewById<TextView>(R.id.tvPhoneResponse).setText(phone)
        findViewById<TextView>(R.id.tvUbicationResponse).setText(ubication)
        findViewById<TextView>(R.id.tvPaginaWebResponse).setText(web)


        val bitmap = getIntent().getParcelableExtra<Bitmap>("PICTURE")
        findViewById<ImageView>(R.id.ivProfileResponse).setImageBitmap(bitmap)


        //Intents
        findViewById<TextView>(R.id.tvPhoneResponse).setOnClickListener{
            val phoneIntent:Intent=Intent(Intent.ACTION_DIAL).apply {
                data= Uri.parse("tel:$phone")
            }
            startActivity(phoneIntent)
        }

        findViewById<TextView>(R.id.tvUbicationResponse).setOnClickListener{
            val array:List<String> = ubication!!.split(' ')
            var direction:String=""
            var cont:Int=0;
            for (i in array){
                if(cont==0){
                    direction=direction+"$i"
                } else {
                    direction=direction+"+$i"
                }
                cont++
            }
            direction="geo:0.0?q=$direction"
            val ubicationIntent:Intent=Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(direction)
            }
            startActivity(ubicationIntent)
        }

        findViewById<TextView>(R.id.tvPaginaWebResponse).setOnClickListener{
            val uri=Uri.parse("https://$web")
            val webIntent:Intent=Intent(Intent.ACTION_VIEW,uri)
            startActivity(webIntent)
        }



    }
}