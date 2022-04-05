package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.Application
import play.api.mvc.ControllerComponents
import play.api.test._
import play.api.test.Helpers._
import repository.UserRepository


class HomeControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting  {

  "HomeController GET" should {

    "render the index page from a new instance of controller" in {
      val cc = Application.instanceCache[ControllerComponents].apply(app)
      val userRepository= Application.instanceCache[UserRepository].apply(app)
      val controller = new HomeController(cc, userRepository)
      val home = controller.index().apply(FakeRequest(GET, "/"))
      status(home) mustBe OK
    }

    "render the index page from the application" in {
      val controller = inject[HomeController]
      val home = controller.index().apply(FakeRequest(GET, "/"))
      status(home) mustBe OK
    }

    "render the index page from the router" in {
      val request = FakeRequest(GET, "/")
      val home = route(app, request).get
      status(home) mustBe OK
    }

    "render the allUsers page from a new instance of controller" in {
      val cc = Application.instanceCache[ControllerComponents].apply(app)
      val userRepository= Application.instanceCache[UserRepository].apply(app)
      val controller = new HomeController(cc, userRepository)
      val home = controller.getAllUsers().apply(FakeRequest(GET, "/allUsers"))
      status(home) mustBe OK
    }

    "render the allUsers page from the application" in {
      val controller = inject[HomeController]
      val home = controller.getAllUsers().apply(FakeRequest(GET, "/allUsers"))
      status(home) mustBe OK
    }

    "render the allUsers page from the router" in {
      val request = FakeRequest(GET, "/allUsers")
      val home = route(app, request).get
      status(home) mustBe OK
    }
    "render the addUser page from a new instance of controller" in {
      val cc = Application.instanceCache[ControllerComponents].apply(app)
      val userRepository= Application.instanceCache[UserRepository].apply(app)
      val controller = new HomeController(cc, userRepository)
      val home = controller.addUser("user@knoldus.com","user").apply(FakeRequest(GET, "/addUser"))
      status(home) mustBe OK
    }

    "render the addUser page from the application" in {
      val controller = inject[HomeController]
      val home = controller.addUser("user1@knoldus.com","1user").apply(FakeRequest(GET, "/addUser"))
      status(home) mustBe OK
    }

    "render the updateUserNameByEmail page from a new instance of controller" in {
      val cc = Application.instanceCache[ControllerComponents].apply(app)
      val userRepository= Application.instanceCache[UserRepository].apply(app)
      val controller = new HomeController(cc, userRepository)
      val home = controller.updateUserNameByEmail("user@knoldus.com","newUser").apply(FakeRequest(GET, "/updateUserNameByEmail"))
      status(home) mustBe OK
    }

    "render the updateUserNameByEmail page from the application" in {
      val controller = inject[HomeController]
      val home = controller.updateUserNameByEmail("user1@knoldus.com","newUser1").apply(FakeRequest(GET, "/updateUserNameByEmail"))
      status(home) mustBe OK
    }


    "render the deleteUserByUserId page from a new instance of controller" in {
      val cc = Application.instanceCache[ControllerComponents].apply(app)
      val userRepository= Application.instanceCache[UserRepository].apply(app)
      val controller = new HomeController(cc, userRepository)
      val home = controller.deleteUserByEmail("user@knoldus.com").apply(FakeRequest(GET, "/deleteUserByUserId"))
      status(home) mustBe OK
    }

    "render the deleteUserByUserId page from the application" in {
      val controller = inject[HomeController]
      val home = controller.deleteUserByEmail("user1@knoldus.com").apply(FakeRequest(GET, "/deleteUserByUserId"))
      status(home) mustBe OK
    }

  }
}
