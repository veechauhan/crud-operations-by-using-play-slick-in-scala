package controllers

import models.User
import javax.inject._
import play.api.mvc._
import repository.UserRepository
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


@Singleton
class HomeController @Inject()(
                                cc: ControllerComponents,
                                 userRepository: UserRepository

                              ) extends AbstractController(cc) {

  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
  def getAllUsers: Action[AnyContent] =Action.async{ implicit request =>
    userRepository.getAllUsers.map{user=>
      Ok(views.html.user(user))
    }
  }
  def addUser(email:String,name:String): Action[AnyContent] = Action.async{

    implicit request =>
      val user = User(
       Some( email),
        name,
      )
      userRepository.getUserByEmail(email).flatMap {
        case Some(_) =>Future(Ok("User already exist!"))
        case None => userRepository.addUser(user).map { _ =>

          Ok(views.html.index())
        }
      }
  }
  def deleteUserByEmail(email:String): Action[AnyContent] = Action.async{
    implicit request=>
      userRepository.deleteUserByEmail(email).map{
        case 1=>Ok(views.html.deleteUser("Deleted"))
        case 0 =>Ok(views.html.deleteUser(""))

      }
  }
  def updateUserNameByEmail(email:String,name:String): Action[AnyContent] =Action.async{
    implicit request=>
        val user =User(Some(email),name)
        userRepository.updateUserNameByEmail(email,user).map{
          case 1=>Ok(views.html.updateUser("Updated"))
          case 0=>Ok(views.html.updateUser(""))
        }
  }
}
