package cm.pam.cibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Info_Users : AppCompatActivity() {

    val database = Firebase.database
    //reference dans la base de donnée
    val ref=database.getReference("Users")

    lateinit var _USERNAME:String
    lateinit var _PASSWORD:String
    lateinit var _EMAIL: String
    lateinit var _MONEY:String
    lateinit var _PHOTO:String
    lateinit var _PUBlicATION:String
    lateinit var _FOLLOWERS:String
    lateinit var _FOLLOWING:String
    lateinit var _FAVOURITE:String

    lateinit var image: ImageView
    lateinit var username: TextView
    lateinit var email: TextView
    lateinit var password: TextView
    lateinit var money: TextView
    lateinit var LblReview: TextView
    lateinit var LblFollowers: TextView
    lateinit var LblFollowing: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_users)

        var intents= Intent(this, My_Profile::class.java)
        //Valeurs
        image=findViewById<ImageView>(R.id.ImgPicture)
        username=findViewById<TextView>(R.id.lblUsername)
        email=findViewById<TextView>(R.id.lblEmail)
        password=findViewById<TextView>(R.id.lblPhonenumber)
        money=findViewById<TextView>(R.id.lblPassword)
        LblReview=findViewById<TextView>(R.id.lblReviews)
        LblFollowers=findViewById<TextView>(R.id.lblFollowers)
        LblFollowing=findViewById<TextView>(R.id.lblFollowing)
        //Show Users
        ShowAllUser()
    }
    fun ShowAllUser(){
        var intent:Intent=getIntent()
        _USERNAME= intent.getStringExtra("userName").toString()
        _PASSWORD= intent.getStringExtra("password").toString()
        _EMAIL= intent.getStringExtra("email").toString()
        _MONEY=intent.getStringExtra("money").toString()
        _PHOTO=intent.getStringExtra("profilePicture").toString()
        _PUBlicATION=intent.getStringExtra("Publications").toString()
        _FOLLOWERS=intent.getStringExtra("Followers").toString()
        _FOLLOWING=intent.getStringExtra("Following").toString()
        _FAVOURITE=intent.getStringExtra("Favourite").toString()

        username.setText(_USERNAME)
        password.setText(_PASSWORD)
        email.setText(_EMAIL)
        money.setText(_MONEY)

        LblReview.setText(_FAVOURITE)
        LblFollowers.setText(_FOLLOWERS)
        LblFollowing.setText(_FOLLOWING)
    }
    
}