# A login portal (web) [![Awesome](http://forthebadge.com/images/badges/gluten-free.svg)]()[![Awesome](http://forthebadge.com/images/badges/powered-by-electricity.svg)]()

School project focusing on how to store user credentials in a database.

## Getting Started

This repository is one of three repositories needed to launch the project. Clone all three of them to launch the portal!

1. (main) https://github.com/harald-billstein/weblogin.git
2. (api)  https://github.com/sdlonn/WebRegisterAPI.git
3. (api)  https://github.com/sdlonn/loginRestAPI.git


### Prerequisites

* MySql v5.7.20 or later
* Java 8
* Tomcat EE plus v7.0.2


### Installing

* Start by installing both api's [register](https://github.com/sdlonn/WebRegisterAPI.git) and [login](https://github.com/sdlonn/loginRestAPI.git)

* Clone https://github.com/harald-billstein/weblogin.git

* Download and import the webresources.sql file into your database

* Configure context.xml located in "weblogin/WebContent/META-INF/"
    If needed change url, username and password to match the setup in your database
    
* Compile with maven inside your ide or run `mvn clean install`

* In Eclipse run all three modules in same TomEE server

* In Intellij go to `run` then `edit configurations` under the `deployment` tab add both api's, then you need to set Application context
 weblogin to `/webLoginProject` *.war to `/WebRegisterAPI` *.war to `/LoginApi`
 
* Default Username: `admin`, Password `password`, remove when program is up and running.

## Deployment

Additional notes about how to deploy this on a live system
* If you run all modules on same tomcatEE you may have to change the JNDI config
* May be necessary to use an [external image servlet](https://github.com/sdlonn/WebPictureServlet) if you deploy this live

## Built With

* [MAVEN](https://maven.apache.org/) - Dependency Management
* [JSF](http://www.oracle.com/technetwork/java/javaee/javaserverfaces-139869.html) - Server side user interfaces
* [REST/JERSEY](https://jersey.github.io) - API communication
* [GITHUB](https://github.com) - Version control
* [MYSQL](https://www.mysql.com/) - Database 
* [TOMEE](http://tomee.apache.org) - EE application server
* [JNDI](http://www.oracle.com/technetwork/java/index-jsp-137536.html) - Database connection pool handling
* [PRIMEFACES](https://www.primefaces.org) - Picture gallery handling
* [FILTER](http://www.oracle.com/technetwork/java/filters-137243.html) - Filter
* [SERVLET](https://docs.oracle.com/javaee/6/tutorial/doc/bnafd.html) - Servlets


## Authors

* **Harald Billstein** - *school project* - [Git link](https://github.com/harald-billstein)
* **Stefan Lönn** - *school project* - [Git link](https://github.com/sdlonn)

## License

This project is licensed under the MIT License - see the [LICENSE](https://github.com/harald-billstein/weblogin/blob/master/LICENSE) file for details

