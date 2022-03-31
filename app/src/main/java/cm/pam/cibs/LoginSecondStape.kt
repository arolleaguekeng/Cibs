package cm.pam.cibs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
var phoneIsValide: Boolean = true
var passIsValide: Boolean = false
class LoginSecondStape : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_second_stape)

        testPassword()

        testConnexion()
    }

    private fun testPassword(){
        var pass : com.google.android.material.textfield.TextInputLayout = findViewById(R.id.passwordInputLayout)
        var passfield : com.google.android.material.textfield.TextInputEditText = findViewById(R.id.passwordInputEditText)
        passfield.doOnTextChanged { text, start, before, count ->
            if(text!!.length>8){
                pass.error = "trop long"
                passIsValide = false

            }
            else if(text.length < 6){
                pass.error = "trop simple"
                passIsValide = false
            }
            else if(text!!.length<10 && text.length>5){
                pass.helperText = "valide"
                passIsValide = true
            }

        }
    }

    private fun testConnexion(){
        var btn : com.google.android.material.button.MaterialButton = findViewById(R.id.connect)
        btn.setOnClickListener(){
            var toast : Toast
            if(passIsValide && phoneIsValide){
                toast = Toast.makeText(applicationContext, "connexion sucess", Toast.LENGTH_LONG )
                toast.setGravity(Gravity.BOTTOM, 0, 10)
                toast.show()
                var intent: Intent = Intent(this, MessageWelcomePage::class.java)
                startActivity(intent)

            }
            else if(!passIsValide && phoneIsValide){
                toast = Toast.makeText(applicationContext, "password is incorrect", Toast.LENGTH_SHORT )
                toast.setGravity(Gravity.BOTTOM, 0, 10)
                toast.show()

            }



        }
    }
}