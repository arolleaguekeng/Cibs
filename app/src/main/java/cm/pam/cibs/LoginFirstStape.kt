package cm.pam.cibs

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class LoginFirstStape : AppCompatActivity(){
    private var forceResedingToken: PhoneAuthProvider.ForceResendingToken? = null
    private var mCalbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks? = null
    private var mverificationId: String? = null
    private lateinit var firebaseAuth: FirebaseAuth
    private val TAG = "MAIN_TAG"
    private lateinit var progressDialog: ProgressDialog
    var phoneIsValide: Boolean = false


    lateinit var recyclerView: RecyclerView


    private  val repas = mutableListOf<Repas>(
        Repas( R.drawable.fritte, "burgeur au fritte","voici une description",2f),
        Repas( R.drawable.fritte, "burgeur au fritte","voici une description",4.3f),
        Repas( R.drawable.fritte, "burgeur au fritte","voici une description",3.6f)

    )

    lateinit var groupTel : androidx.constraintlayout.widget.Group
    lateinit var groupPas : androidx.constraintlayout.widget.Group
    lateinit var text: TextView
    var phoneNum: String= ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_first_stape)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle(("please wait"))
        progressDialog.setCanceledOnTouchOutside(false)
        groupTel = findViewById(R.id.groupTel)
        groupPas = findViewById(R.id.groupPas)
        text = findViewById(R.id.forgotPass)

        mCalbacks = object  : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential)

            }
            override fun onVerificationFailed(e: FirebaseException) {
                progressDialog.dismiss()
                Toast.makeText(applicationContext," ${e.message}",Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(verificationId:String,token: PhoneAuthProvider.ForceResendingToken) {
                Log.d(TAG,"onCodeSent: $verificationId")
                mverificationId = verificationId
                forceResedingToken = token
                progressDialog.dismiss()

                Toast.makeText(this@LoginFirstStape,"verification code ",Toast.LENGTH_SHORT).show()
            }
        }


        testPhone()
        testNext()
        testPassword()
        testConnexion()

        text.setOnClickListener(){
            resendVerificationCode("+237"+phoneNum, forceResedingToken)
        }



    }


    private fun testPhone() {
        var tel: com.google.android.material.textfield.TextInputLayout = findViewById(R.id.textInputLayout)

        var telfield: com.google.android.material.textfield.TextInputEditText =  findViewById(R.id.textInputEditText)

        telfield.doOnTextChanged { text, start, before, count ->

            if (text!!.length > 9 ) {
                tel.error = "pas plus"
                phoneIsValide = false
            } else if (text.length < 9) {
                tel.error = "pas moins"
                phoneIsValide = false
            } else if (text.length == 9) {
                tel.helperText = "valide"
                tel.setEndIconDrawable(R.drawable.ic_check)
                phoneIsValide = true
            }

        }
    }

    private fun testNext() {
        var telfield: com.google.android.material.textfield.TextInputEditText =
            findViewById(R.id.textInputEditText)
        phoneNum = telfield.text.toString()



        var btn: com.google.android.material.button.MaterialButton = findViewById(R.id.next)

        var toast: Toast
        btn.setOnClickListener() {

            if(phoneIsValide){
                startPhoneNumberVerification("+237"+telfield.text.toString())
                groupTel.visibility = View.GONE
                groupPas.visibility = View.VISIBLE
                text.visibility = View.VISIBLE
                var intent: Intent = Intent(applicationContext, CategorySearch::class.java)
                startActivity(intent)

            }
            else
            {
                toast = Toast.makeText(
                    applicationContext,
                    "phone number is incorrect",
                    Toast.LENGTH_LONG
                )
                toast.setGravity(Gravity.BOTTOM, 0, 10)
                toast.show()

            }

        }


    }


    private  fun startPhoneNumberVerification(phone : String)
    {
        progressDialog.setMessage(("verifie phone Number..."))
        progressDialog.show()
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phone)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(mCalbacks!!)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
    private fun resendVerificationCode(phone:String,token: PhoneAuthProvider.ForceResendingToken?){
        progressDialog.setMessage(("Resending Code..."))
        progressDialog.show()
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phone)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(mCalbacks!!)          // OnVerificationStateChangedCallbacks
            .setForceResendingToken(token!!)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }

    private fun verifyPhoneNumberWhithcode(verificationId: String?, code:String){
        progressDialog.setMessage(("Verifying Code..."))
        progressDialog.show()
        val credential = PhoneAuthProvider.getCredential(verificationId.toString(),code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        progressDialog.setMessage(("Resending Code..."))
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener {
                val phone = firebaseAuth.currentUser?.phoneNumber
                Toast.makeText(this,"Login as $phone",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,HomePage::class.java))
                finish()
            }
            .addOnFailureListener { e->
                progressDialog.dismiss()
                Toast.makeText(this,"${e.message}",Toast.LENGTH_SHORT).show()
            }
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

    private fun testConnexion() {
        var passfield : com.google.android.material.textfield.TextInputEditText = findViewById(R.id.passwordInputEditText)
        var btn: com.google.android.material.button.MaterialButton = findViewById(R.id.connect)
        btn.setOnClickListener() {
            var toast: Toast
            if (passfield.text.isNullOrEmpty()) {
                toast = Toast.makeText(applicationContext, " password is incorrect", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.BOTTOM, 0, 10)
                toast.show()

            } else {
                verifyPhoneNumberWhithcode(mverificationId, passfield.text.toString())
            }


        }
    }
}