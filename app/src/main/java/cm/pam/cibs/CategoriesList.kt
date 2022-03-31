package cm.pam.cibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class CategoriesList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories_list)

        var img: ImageView = findViewById(R.id.back2)

        img.setOnClickListener(){
            var intent: Intent = Intent(this, MessageWelcomePage::class.java)
            startActivity(intent)
        }
    }
}
