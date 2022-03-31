package cm.pam.cibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class UserProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        var img: ImageView = findViewById(R.id.back)

        img.setOnClickListener(){
            var intent: Intent = Intent(this, MessageWelcomePage::class.java)
            startActivity(intent)

        }
    }
}