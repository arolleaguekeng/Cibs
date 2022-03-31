package cm.pam.cibs.Model

class MenuModel(var Name: String,
                var Description : String,
                var Category : String,
                var region : String,
                var culture : String,
                var Saveur : String,
                var NutritifApport : String,
                var Note : Float,
                var Publication : MutableList<PublicationModel> ) {
}