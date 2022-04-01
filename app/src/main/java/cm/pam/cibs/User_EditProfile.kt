package cm.pam.cibs
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import cm.pam.cibs.Model.*

class User_EditProfile : AppCompatActivity() {
    lateinit var users:MutableList<UserModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_edit_profile)

        var username=findViewById<EditText>(R.id.txtUsername).text
        var phoneNumber=findViewById<EditText>(R.id.txtPhone).text
        var money=findViewById<EditText>(R.id.txtMoney).text
        var image=findViewById<ImageView>(R.id.ImgPicture).id
        var btnImage=findViewById<Button>(R.id.btnAddPicture)

        var defaultuser:UserModel=CreateUser(username.toString(),phoneNumber.toString(),money.toString(),image,null,null,null,null)
        var user1:UserModel=CreateUser("Arolle","5553535","100000",1,null,1,null,1)
        users.add(1,user1)
        users.add(2,defaultuser)

    }
     fun CreateUser(username:String,phoneNumber:String,money:String,photo:Int,numFollowers:Int?,numFollowing:Int?,numPub:Int?,numfavor:Int?):UserModel {
            var user=UserModel(phoneNumber as Int,CreateProfile(username,money,photo,numPub,numFollowers,numFollowing,numfavor))
        return user
    }
     fun CreateProfile(username: String,money: String,photo: Int,numFollowers:Int?,numFollowing:Int?,numPub:Int?,numfavor:Int?):ProfileModel{
        var profile=ProfileModel(username,"","", money as Long,photo as Int,Pubs(numPub),followers(numFollowers),following(numFollowing),favor(numfavor))
        return profile
    }
     fun Pubs(numPub: Int?):MutableList<PublicationModel>?{
        lateinit var pub:MutableList<PublicationModel>
        var publi=PublicationModel(
            null,
            2,
            CreateUser("Israel","698524956","20000",2,null,null,null,null),
            null,
            2,
            2,
            null
        )
        if(numPub==null){
            return null
        }
        else{
            for(i in 0..numPub){
                pub.add(i,publi)
            }
            return pub
        }
    }
     fun followers(numFollowers: Int?):MutableList<UserModel>?{
        lateinit var fol:MutableList<UserModel>
        var follower=UserModel(
            66682738,
            CreateProfile("Paco","3211211",3,2,1,0,1)
        )
        if(numFollowers==null){
            return null
        }
        else{
            for(i in 0..numFollowers){
                fol.add(i,follower)
            }
            return fol
        }
    }
     fun following(numFollowing: Int?):MutableList<UserModel>?{
        lateinit var fol:MutableList<UserModel>
        var follow=UserModel(
            66682738,
            CreateProfile("Paco","3211211",3,null,null,null,null)
        )
        if(numFollowing==null){
            return null
        }
        else{
            for(i in 0..numFollowing){
                fol.add(i,follow)
            }
            return fol
        }
    }
     fun favor(numfavor: Int?):MutableList<PublicationModel>?{
        lateinit var fav:MutableList<PublicationModel>
        var favori=PublicationModel(
            null,
            2,
            CreateUser("Dumont","677777777","3000000",1,1,null,null,null),
            null,
            2,
            2,
            null
        )
        if(numfavor==null){
            return null
        }
        else{
            for(i in 0..numfavor){
                fav.add(1,favori)
            }
            return fav
        }
    }
}