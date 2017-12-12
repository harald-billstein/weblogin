# A login portal (web)

School project focusing on how to store user credentials in a database.

## Getting Started

This repository is one of three repositories needed to launch the project. Clone all three of them to launch the portal!

1. (main) https://github.com/harald-billstein/weblogin.git
2. (api)  https://github.com/sdlonn/WebRegisterAPI.git
3. (api)  https://github.com/sdlonn/loginRestAPI.git


### Prerequisites

MySql database (See folder in main) for table structure

```
Example: XAMPP
```

### Installing

* Start by installing both api's [register](https://github.com/sdlonn/WebRegisterAPI.git) and [login](https://github.com/sdlonn/loginRestAPI.git)

* Skip if you did this when installing api's [Install MySql](https://dev.mysql.com/downloads/)
Or use [XAMPP](https://www.apachefriends.org/index.html)

* Skip if you did this when installing api's Download and install [TomcatEE plus](http://openejb.apache.org/downloads.html) to [Eclipse](http://www.eclipse.org/downloads/eclipse-packages/) or [Intellij](https://www.jetbrains.com/idea/download/#section=windows)

* Clone https://github.com/harald-billstein/weblogin.git

* Download and import the webresources.sql file into your databse

* Configure context.xml located in "weblogin/WebContent/META-INF/"
    If needed change url username password to match the setup in your database
    
* Compile with maven inside your ide or run `mvn clean install`

* In Eclipse run all three modules in same TomEE server

* In Intellij go to `run` then `edit configurations` under the `deployment` tab add both api's, then you need to set Application context
 weblogin to `/webLoginProject` webregister???.war to `/WebRegisterAPI` loginapi???.war to `/LoginApi`

## Deployment

Additional notes about how to deploy this on a live system
* If you run all modules on same tomcatEE you may have to change the JNDI config
* May be necessary to use an [external image servlet](https://github.com/sdlonn/WebPictureServlet) if you deploy this live

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [JSF](http://www.oracle.com/technetwork/java/javaee/javaserverfaces-139869.html) - Server side user interfaces
* [REST/JERSEY](https://jersey.github.io) - API communication
* [GITHUB](https://github.com) - Version control
* [MYSQL](https://www.mysql.com/) - Database 
* [TOMEE](http://tomee.apache.org) - EE application server
* [JNDI](http://www.oracle.com/technetwork/java/index-jsp-137536.html) - Database connection pool handling
* [PRIMEFACES](https://www.primefaces.org) - Picture gallery handling


## Authors

* **Harald Billstein** - *school project* - [Git link](https://github.com/harald-billstein)
* **Stefan Lönn** - *school project* - [Git link](https://github.com/sdlonn)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

