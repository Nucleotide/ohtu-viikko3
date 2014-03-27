import ohtu.*
import ohtu.services.*
import ohtu.data_access.*
import ohtu.domain.*
import ohtu.io.*

description """A new user account can be created 
              if a proper unused username 
              and a proper password are given"""

scenario "creation successful with correct username and password", {
    given 'command new user is selected', {
       userDao = new InMemoryUserDao()
       auth = new AuthenticationService(userDao)
       io = new StubIO("new", "eero", "sala1nen" ) 
       app = new App(io, auth)
    }
 
    when 'a valid username and password are entered', {
      app.run()
    }

    then 'new user is registered to system', {
      io.getPrints().shouldHave("new user registered")
    }
}

scenario "can login with successfully generated account", {
    given 'command new user is selected', {
       userDao = new InMemoryUserDao()
       auth = new AuthenticationService(userDao)
       io = new StubIO("new", "eero", "sala1nen", "login", "eero", "sala1nen") 
       app = new App(io, auth)
    }
 
    when 'a valid username and password are entered', {
      app.run()
    }

    then  'new credentials allow logging in to system', {
       io.getPrints().shouldHave("logged in")
    }
}

scenario "creation fails with correct username and too short password", {
    given 'command new user is selected', {
       userDao = new InMemoryUserDao()
       auth = new AuthenticationService(userDao)
       io = new StubIO("new", "tero", "as") 
       app = new App(io, auth)
    }

    when 'a valid username and too short password are entered', {
      app.run()
    }

    then 'new user is not be registered to system', {
       io.getPrints().shouldHave("new user not registered")
    }
}

scenario "creation fails with correct username and password consisting of letters", {
    given 'command new user is selected', {
       userDao = new InMemoryUserDao()
       auth = new AuthenticationService(userDao)
       io = new StubIO("new", "liero", "awigjwrighegs") 
       app = new App(io, auth)
    }

    when 'a valid username and a password that has only letters are entered', {
      app.run()
    }

    then 'new user is not be registered to system', {
       io.getPrints().shouldHave("new user not registered")
    }
}

scenario "creation fails with too short username and valid password", {
    given 'command new user is selected', {
       userDao = new InMemoryUserDao()
       auth = new AuthenticationService(userDao)
       io = new StubIO("new", "ro", "a4wg0wgjwws") 
       app = new App(io, auth)
    }

    when 'an invalid username and good password are entered', {
      app.run()
    }

    then 'new user is not be registered to system', {
       io.getPrints().shouldHave("new user not registered")
    }
}

scenario "creation fails with already taken username and valid password", {
    given 'command new user is selected', {
       userDao = new InMemoryUserDao()
       auth = new AuthenticationService(userDao)
       io = new StubIO("new", "tero", "a4wg0wgjws", "new", "tero", "salam1esS") 
       app = new App(io, auth)
    }

    when 'an already taken username and good password are entered', {
      app.run()
    }

    then 'new user is not be registered to system', {
       io.getPrints().shouldHave("new user not registered")
    }
}

scenario "can not login with account that is not successfully created", {
    given 'command new user is selected', {
       userDao = new InMemoryUserDao()
       auth = new AuthenticationService(userDao)
       io = new StubIO("new", "tero", "pyssy", "login", "tero", "pyssy") 
       app = new App(io, auth)
    }

    when 'trying to login with user credentials that did not create a user successfully', {
      app.run()
    }

    then 'new user should not be logged in to the system', {
       io.getPrints().shouldHave("wrong username or password")
    }
}