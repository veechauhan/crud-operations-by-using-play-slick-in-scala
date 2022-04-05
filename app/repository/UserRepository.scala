package repository
import com.google.inject.{Inject, Singleton}
import models.User
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.{ExecutionContext, Future}

class UserTableDef(tag: Tag) extends Table[User](tag, "users") {

  def email: Rep[String] = column[String]("email_id",O.PrimaryKey)
  def name: Rep[String] = column[String]("name")

  override def * : ProvenShape[User] =
    (
      email.?,
      name) <>((User.apply _).tupled, User.unapply)
}
@Singleton
class UserRepository @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit
                                                                                        ec: ExecutionContext
) extends HasDatabaseConfigProvider[JdbcProfile] {

private val users = TableQuery[UserTableDef]

  def getAllUsers: Future[Seq[User]] ={
    dbConfig.db.run(users.result)
  }
  def getUserByEmail(email:String): Future[Option[User]] ={
    dbConfig.db.run(users.filter(_.email===email).result.headOption)
  }

  def addUser(user: User): Future[Int] ={
    dbConfig.db.run(users+=user)
  }
  def deleteUserByEmail(Email:String): Future[Int] ={
    dbConfig.db.run(users.filter(_.email===Email).delete)
  }
 def updateUserNameByEmail(email:String,user:User): Future[Int] ={
   dbConfig.db.run(users.filter(_.email===email).update(user))
 }
}
