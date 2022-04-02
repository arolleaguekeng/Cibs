package cm.pam.cibs.Model

class UserModel(var PhoneNumber : Int,
                var Profile : ProfileModel,
                var Followers: MutableList<UserModel>?,
                var Following: MutableList<UserModel>?,
                var Favorites: MutableList<PublicationModel>?
                ){
}