package cm.pam.cibs.Model

class ProfileModel(var UserName : String,
                   var Password : String?,
                   var Email : String?,
                   var Money:Long,
                   var ProfilePicture : Int,
                   var Publications : MutableList<PublicationModel>?,
                   var Followers : MutableList<UserModel>?,
                   var Following : MutableList<UserModel>?,
                   var Favorites : MutableList<PublicationModel>?){
}