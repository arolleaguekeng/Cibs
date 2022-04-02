package cm.pam.cibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import cm.pam.cibs.Model.ProfileModel
import cm.pam.cibs.Model.UserModel

class My_Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)
        var EditProfile=findViewById<Button>(R.id.btnEdit)
        //var Setting=findViewById<Button>(R.id.btnSetting)
        var intent = Intent(this, User_EditProfile::class.java)

        EditProfile.setOnClickListener {
            startActivity(intent)
        }
    }
}