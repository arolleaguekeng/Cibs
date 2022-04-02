package cm.pam.cibs.Model

class ProfileModel(
    var UserName: String,
    var Password: String,
    var Email: String,
    var Money: String,
    var ProfilePicture: Int,
    var Publications: MutableList<PublicationModel>?,
   ){
}