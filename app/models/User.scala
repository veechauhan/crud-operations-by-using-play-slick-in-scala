package models

import play.api.libs.json.{ Json, OFormat }
case class User(
               email:Option[String],
               name:String
               )
object  User {
  implicit val userFormat: OFormat[User] = Json.format[User]
}
