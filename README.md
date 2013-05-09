# 3JVA-SupLink2
Supinfo Project - Java EE/Android

This is an URL shortener service with user account and statistics. I made a website and an Android application which consumes a RESTful Web Service.

**For the Web part I used** :
* Servlet/JSP implementing JSP Model 2 Architecture
* JPA (Dao...)
* JAX-RS (Jersey)
* Apache Tomcat
* MySQL server

I made this with Eclipse on Mac.

**For the Android part I used** :
* Android API Level 8 (Android 2.2)

I made this with Eclipse ADT on Mac.

## Features
* Register as a new user (only on the website)
* Authenticate and logout a user
* Create a shortened URL
* List all the shortened URL created by a user
* Display some statistics about a shortened URL
* Redirect to the correct URL when a shortened URL is used
* Disable / Enable a shortened URL
* Remove a shortened URL and all its statistics

## Configuration
This is the default configuration. Make the changes you want to adapt this on your configuration.

#### HTTP
* HTTP port: 8080

#### MySQL
* MySQL port: 3306
* Database name: suplink
* Database user: root
* Database password: root

## Installation & Test
Just load:
http://localhost:8080/SupLink/initdb
This will add some example datas to easily test the service.

Here is an example user credentials :
* Email: azerty@supinfo.com
* Password: supinfo

## Note
There is currently no authentication for the REST requests. Please **DO NOT USE** in prod.

## License
```
"THE NUTELLAWARE LICENSE" (Revision 23):
Olivier Riberi <olivier@riberi.me> wrote those files. As long as you retain this notice you can do whatever you want with this stuff. If we meet some day, and you think this stuff is worth it, you can send me a jar. Olivier.
```