package cm.pam.cibs
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class User_EditProfile : AppCompatActivity() {

    val database = Firebase.database
    //reference dans la base de donnée
    val ref=database.getReference("Profiles")
    var _USERNAME:String=TODO()
    var _PASSWORD:String
    var _EMAIL: String
    var _MONEY:String
    var _PHOTO:String
    var _PUBlicATION:String
    var _FOLLOWERS:String
    var _FOLLOWING:String
    var _FAVOURITE:String

    var image:ImageView
    var username:EditText
    var email:EditText
    var password:EditText
    var money:EditText
    var btnCancel:Button
    var btnpicture:Button
    var btnupdate:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_edit_profile)
        var intents= Intent(this, My_Profile::class.java)
        //Valeurs
         image=findViewById<ImageView>(R.id.ImgPicture)
         username=findViewById<EditText>(R.id.txtUsername)
         email=findViewById<EditText>(R.id.txtEmail)
         password=findViewById<EditText>(R.id.txtPassword)
         money=findViewById<EditText>(R.id.txtMoney)
         btnupdate=findViewById<Button>(R.id.btnUpdate)
         btnpicture=findViewById<Button>(R.id.btnAddPicture)
         btnCancel=findViewById<Button>(R.id.btnCancel)

       //ShowData when we enter
        ShowAllProfile()

        btnCancel.setOnClickListener {
            startActivity(intents)
        }
        btnpicture.setOnClickListener {

        }
        btnupdate.setOnClickListener {
            Update(btnupdate)
        }

    }
    /*fun CreateProfile(username:String,email:String,password:String,money:Long,picture:Int):ProfileModel{
        var profile=ProfileModel(username,email,password,money,picture,null,null,null,null)
        return profile
    }
    //Lire dans la base de données
    fun showProfileDatabase(){
        _USERNAME.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<String>()
                Log.d(TAG, "Value is: " + value)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
        _EMAIl.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<String>()
                Log.d(TAG, "Value is: " + value)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
        _PASSWORD.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<String>()
                Log.d(TAG, "Value is: " + value)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
        _MONEY.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<String>()
                Log.d(TAG, "Value is: " + value)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
        _PHOTO.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = snapshot.getValue<String>()
                Log.d(TAG, "Value is: " + value)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }*/

    fun ShowAllProfile(){
        lateinit var intent:Intent
        _USERNAME= intent.getStringExtra("Username").toString()
        _PASSWORD= intent.getStringExtra("Password").toString()
        _EMAIL= intent.getStringExtra("Email").toString()
        _MONEY=intent.getStringExtra("Money").toString()
        _PHOTO=intent.getStringExtra("Photo").toString()


        username.setText(_USERNAME)
        password.setText(_PASSWORD)
        email.setText(_EMAIL)
        money.setText(_MONEY)
    }
    fun Update(view:View){
        if(IsUsernameChanged()||IsPasswordChanged()||IsEmailChanged()||IsMoneyChanged()){
            Toast.makeText(this,"Data is changed",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this,"Data is same and cannot change",Toast.LENGTH_LONG).show()
        }
    }

    private fun IsUsernameChanged(): Boolean {
        if(!_USERNAME.equals(username.text.toString())){
            ref.child(_USERNAME).setValue(username.text.toString())
            _USERNAME=username.text.toString()
            return true
        }else{
            return false
        }
    }
    private fun IsPasswordChanged(): Boolean {
        if(!_PASSWORD.equals(password.text.toString())){
            ref.child(_PASSWORD).setValue(password.text.toString())
            _PASSWORD=username.text.toString()
            return true
        }else{
            return false
        }
    }
    private fun IsEmailChanged(): Boolean {
        if(!_EMAIL.equals(email.text.toString())){
            ref.child(_EMAIL).setValue(email.text.toString())
            _EMAIL=username.text.toString()
            return true
        }else{
            return false
        }
    }
    private fun IsMoneyChanged(): Boolean {
        if(!_MONEY.equals(money.text.toString())){
            ref.child(_MONEY).setValue(money.text.toString())
            _MONEY=username.text.toString()
            return true
        }else{
            return false
        }
    }
}