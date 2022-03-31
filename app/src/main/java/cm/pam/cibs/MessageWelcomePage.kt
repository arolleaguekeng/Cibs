package cm.pam.cibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MessageWelcomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_welcome_page)

        var btn: com.google.android.material.button.MaterialButton = findViewById(R.id.turn)
        var btn2: com.google.android.material.button.MaterialButton = findViewById(R.id.btn_skip)

        btn.setOnClickListener(){
            var intent: Intent = Intent(this, CategoriesList::class.java)
            startActivity(intent)
        }

        btn2.setOnClickListener(){
            var intent: Intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }



    }
}