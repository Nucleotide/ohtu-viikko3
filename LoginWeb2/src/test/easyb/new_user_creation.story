import ohtu.*
import ohtu.authentication.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description """A new user account can be created 
              if a proper unused username 
              and a proper password are given"""

scenario "creation successful with correct username and password", {
    given 'register new user selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();       
    }

    when 'a valid username and password are given', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("terveys");
        element = driver.findElement(By.name("password"));
        element.sendKeys("jeejeeje0");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("jeejeeje0");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
 
    then 'user will be logged in to system', {
        driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe true
    }
}

scenario "can login with successfully generated account", {
    given 'login selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("login"));       
        element.click();       
    }

    when 'a valid username and password are given', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("terveys");
        element = driver.findElement(By.name("password"));
        element.sendKeys("jeejeeje0");
        element = driver.findElement(By.name("login"));
        element.submit();
    }
 
    then 'user will be logged in to system', {
        driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe true
    }
}

scenario "creation fails with correct username and too short password", {
    given 'register new user selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();       
    }

    when 'a valid username and too short password are given', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("terveysef");
        element = driver.findElement(By.name("password"));
        element.sendKeys("je");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("je");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
 
    then 'user will not be logged in to system', {
        driver.getPageSource().contains("length greater or equal to").shouldBe true
    }
}

scenario "creation fails with correct username and incorrect password confirmation", {
    given 'register new user selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();       
    }

    when 'a valid username and a bad password confirmation are given', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("terveysef");
        element = driver.findElement(By.name("password"));
        element.sendKeys("hurhur77");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("hurhur78");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
 
    then 'user will not be logged in to system', {
        driver.getPageSource().contains("password and confirmaton were not the same").shouldBe true
    }
}

scenario "creation fails with correct username and password consisting of letters", {
    given 'register new user selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();       
    }

    when 'a valid username and invalid password are given', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("terveysef");
        element = driver.findElement(By.name("password"));
        element.sendKeys("jassssssssse");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("jassssssssse");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
 
    then 'user will not be logged in to system', {
        driver.getPageSource().contains("must contain one character that is not a letter").shouldBe true
    }
}

scenario "creation fails with too short username and valid password", {
    given 'register new user selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();       
    }

    when 'a invalid username and password are given', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("aa");
        element = driver.findElement(By.name("password"));
        element.sendKeys("Hey00jeaa");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("Hey00jeaa");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
 
    then 'user will not be logged in to system', {
        driver.getPageSource().contains("length 5-10").shouldBe true
    }
}

scenario "creation fails with already taken username and valid password", {
    given 'register new user selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();       
    }

    when 'a taken username and password are given', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("terveys");
        element = driver.findElement(By.name("password"));
        element.sendKeys("jeejeeje0");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("jeejeeje0");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
 
    then 'user will not be logged in to system', {
        driver.getPageSource().contains("username or password invalid").shouldBe true
    }
}

scenario "can not login with account that is not succesfully created", {
    given 'login selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("login"));       
        element.click();       
    }

    when 'a valid username and password are given', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("terveysass");
        element = driver.findElement(By.name("password"));
        element.sendKeys("jeejeeje0");
        element = driver.findElement(By.name("login"));
        element.submit();
    }
 
    then 'user will not be logged in to system', {
        driver.getPageSource().contains("wrong username or password").shouldBe true
    }
}