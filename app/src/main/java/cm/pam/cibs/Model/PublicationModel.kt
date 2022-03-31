package cm.pam.cibs.Model

class PublicationModel(var Meal : MealModel,
                       var Photo : Int,
                       var User : UserModel,
                        var Note : Float,
                        var Likes : Int,
                        var Views : Int,
                        var Remark : RemarkModel) {
}