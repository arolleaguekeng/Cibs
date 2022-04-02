package cm.pam.cibs
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cm.pam.cibs.Model.ProfileModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class User_EditProfile : AppCompatActivity() {

    val database = Firebase.database
    //reference dans la base de donnée
    val ref=database.getReference("Profiles")
    lateinit var _USERNAME:String
    lateinit var _PASSWORD:String
    lateinit var _EMAIL: String
    lateinit var _MONEY:String
    lateinit var _PHOTO:String
    lateinit var _PUBlicATION:String
    lateinit var _FOLLOWERS:String
    lateinit var _FOLLOWING:String
    lateinit var _FAVOURITE:String

    lateinit var image:ImageView
    lateinit var username:EditText
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var money:EditText
    lateinit var btnCancel:Button
    lateinit var btnpicture:Button
    lateinit var btnupdate:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_edit_profile)
        var intents= Intent(this, My_Profile::class.java)
        //Valeurs
         image=findViewById(R.id.ImgPicture)
         username=findViewById(R.id.txtUsername)
         email=findViewById(R.id.txtEmail)
         password=findViewById(R.id.txtPassword)
         money=findViewById(R.id.txtMoney)
         btnupdate=findViewById(R.id.btnUpdate)
         btnpicture=findViewById(R.id.btnAddPicture)
         btnCancel=findViewById(R.id.btnCancel)


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

    }
    fun Update(view:View){
        if(IsUsernameChanged()&&IsPasswordChanged()&&IsEmailChanged()&&IsMoneyChanged()){
            Toast.makeText(this,"Data is changed",Toast.LENGTH_LONG).show()
        }
        else if(IsUsernameChanged()||IsPasswordChanged()||IsEmailChanged()||IsMoneyChanged()){
            Toast.makeText(this,"Data is changed not for all",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this,"Data is same and cannot change",Toast.LENGTH_LONG).show()
        }
    }

    private fun IsUsernameChanged(): Boolean {
        if(!_USERNAME.equals(username.text.toString())){
            ref.child("userName").setValue(username.text.toString())
            _USERNAME=username.text.toString()
            return true
        }else{
            return false
        }
    }
    private fun IsPasswordChanged(): Boolean {
        if(!_PASSWORD.equals(password.text.toString())){
            ref.child("password").setValue(password.text.toString())
            _PASSWORD=password.text.toString()
            return true
        }else{
            return false
        }
    }
    private fun IsEmailChanged(): Boolean {
        if(!_EMAIL.equals(email.text.toString())){
            ref.child("email").setValue(email.text.toString())
            _EMAIL=email.text.toString()
            return true
        }else{
            return false
        }
    }
    private fun IsMoneyChanged(): Boolean {
        if(!_MONEY.equals(money.text.toString())){
            ref.child("money").setValue(money.text.toString())
            _MONEY=money.text.toString()
            return true
        }else{
            return false
        }
    }
}