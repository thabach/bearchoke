## Bearchoke Tempest ##

Welcome to the Bearchoke Tempest. This platform aims to be a best practices framework using the latest Java based frameworks, Enterprise Integration Patterns (EIP) and specifically focusing on using an asynchronous, message based, event driven approach to creating web-based software. The current push in the user interface space is SPA/SPI (Single Page Applications / Single Page Interface). The samples will leverage AngularJS to showcase this. Bearchoke Tempest was created to show how an SPA can interface with the Spring server world as well as leverage other SPA modular goodies. 

Key frameworks that I've leveraged to make this work are:

* Spring
* Spring Integration
* Spring AMQP
* Redis
* Axon CQRS Framework
* Rabbit MQ
* Web Socket

Another key focus has been on the separation of front-end and back-end technologies. This was born out of the requirement to "build for mobile first". To the layman, it means that we want to avoid building more than one way of accessing our service endpoints. It should be the same calls being made in the browser as in a native mobile application. 

Below, I will cover what is required to get up and running using all the goodies available on the platform. But first, here is the list of technologies and frameworks the platform uses to get the job done.

## Back-end Technologies
* [Spring Framework](http://projects.spring.io/spring-framework/) (Inversion of Control IoC)
* Spring Web MVC (Part of Spring Framework: REST mappings)
* Spring Web Socket (Part of Spring Framework: Web Socket mappings)
* [Spring Security](http://projects.spring.io/spring-security/) (security + authentication)
* [Spring Integration](http://projects.spring.io/spring-integration/) (Enterprise Integration Patterns EIP and implementations)
* [Spring Data MongoDb](http://projects.spring.io/spring-data-mongodb) (Persistence layer to MongoDb)
* [Spring Data Jpa](http://projects.spring.io/spring-data-jpa) (Persistence layer to Jpa)
* [Spring Data Redis](http://projects.spring.io/spring-data-redis) (Persistence layer to Redis)
* [Spring Session](https://github.com/spring-projects/spring-session) (for easier persisting session for REST / Web Socket based clients)
* [Axon Framework](http://www.axonframework.org) (Command Query Responsibility Segmentation CQRS)
* [Jasypt](http://jasypt.org/) (Encryption)
* [Boon](https://github.com/RichardHightower/boon) & [Jackson](https://github.com/FasterXML/jackson) (JSON transformation)

## Databases and Queues
* [MongoDb](http://www.mongodb.org)
* [MySQL](http://dev.mysql.com) / [PostgreSQL](http://www.postgresql.org) / [DerbyDb](http://db.apache.org/derby/) or any other RDBMS with a Java driver
* [Redis](http://www.redis.io)
* [RabbitMQ](http://www.rabbitmq.com)

## Front-end Technologies
* [AngularJS](http://www.angularjs.org) (MVC and IoC framework in javascript)
* [Lineman](http://www.linemanjs.com) (Build and test tool)
* [Grunt](http://www.gruntjs.com) (Dependency management for Javascript)
* Web Socket using [SockJS](https://github.com/sockjs) + [Stomp](http://stomp.github.io/) sub-protocol
* [Bootstrap](http://getbootstrap.com/) (Responsive web framework)

## Version Control
* [Github](https://www.github.com) [https://github.com/bjornharvold/bearchoke](https://github.com/bjornharvold/bearchoke)

## IDE, Build & Deployment Tools
* [JetBrains IntelliJ IDEA](http://www.jetbrains.com/idea/) (Integrated Development Environment IDE)
* [Maven](http://maven.apache.org) (Dependency management)
* [Pivotal Cloud Foundry](http://www.pivotal.io/platform-as-a-service/pivotal-cf) (Scalable Platform as a Service PaaS)

## Project Management Tools
* [Github](https://github.com/bjornharvold/bearchoke/issues) (Issue tracking)
* [Github](https://github.com/bjornharvold/bearchoke/wiki) (Wiki)

## Requirements
* Maven v3.2.3+
* Redis v2.8.13+
* RabbitMQ v3.3.4+
* MongoDb v2.6.4+
* JetBrains IntelliJ v13+
* Lineman (latest)
* Grunt (latest)

## Project Directory Structure
* **bearchoke-frontend**
* **bearchoke-backend**

From the root directory, you will see two sub-directories: **bearchoke-frontend** and **bearchoke-backend**. These are two distinct Maven projects that should be imported into your IDE separately. 

**Importing: bearchoke-frontend**
If you are using IntelliJ, you can just import the project by clicking File - Import Project and selecting the directory bearchoke-frontend. IntelliJ project files have already been committed to Git to support this. If you want to create the project from scratch, you can create a new "Static Web" project and set the root directory to be bearchoke-frontend. That's it!

**Importing: bearchoke-backend**
If you are using IntelliJ, you can just import the project by clicking File - Import Project and selecting the directory bearchoke-backend. IntelliJ project files have already been committed to Git to support this. If you want to import it from scratch, you can choose to import the project from the Maven pom.xml file that is located directly under bearchoke-backend. In case you encounter any issues with third-party dependencies, you get more information about the error if you run the initial Maven project compilation from your Terminal / Command Prompt. Go into bearchoke-backend and type:

	$ cd bearchoke-backend
	$ mvn clean install -Dmaven.test.skip=true

This will download all dependencies and install all Bearchoke libraries in the Maven repository. If you do this before you import the project into IntelliJ, project creation will also be faster as all dependencies have already been downloaded.

When you are set up with your project successfully, install the Project Lombok plugin for IntelliJ.

## Setting up Databases and Queues

## Redis (required for every type of active spring profile)
[Redis](http://www.redis.io) is used by [Spring Session](https://github.com/spring-projects/spring-session) to keep a user session over several protocols and devices without needing a cookie (aka JSESSION_ID). Once a user is authenticated, the user can make calls over REST and Web Socket seamlessly.

Download Redis from here: [http://redis.io/download](http://redis.io/download). Once you have it installed, you can start it up from the command line by typing (assuming you have Linux or Mac OSX):
	
	$ redis-server /usr/local/etc/redis.conf
	
## RabbitMQ (required for every type of spring profile)
[RabbitMQ](http://www.rabbitmq.com) is the AMQP implementation used by Spring Integration to broker messages. RabbitMQ is a message broker. The principal idea is pretty simple: it accepts and forwards messages. You can think about it as a post office: when you send mail to the post box you're pretty sure that Mr. Postman will eventually deliver the mail to your recipient. Using this metaphor RabbitMQ is a post box, a post office and a postman. The major difference between RabbitMQ and the post office is the fact that it doesn't deal with paper, instead it accepts, stores and forwards binary blobs of data ‒ messages. RabbitMQ helps in making the architecture event driven.

Download RabbitMQ from here: [http://www.rabbitmq.com/download.html](http://www.rabbitmq.com/download.html). Once you have it installed, you want to first enable the STOMP protocol for RabbitMQ. Go into your installation directory and type:

	$ cd sbin
	$ ./rabbitmq-plugins enable rabbitmq_stomp
	
Then you can start RabbitMQ by typing:

	$ ./rabbitmq-server start
	
## MongoDb (required for 'mongodb' spring profile)
[MongoDb](http://www.mongodb.org) is a NoSQL database, more specifically known as a "document store". Install MongoDb and add it to your PATH. I suggest creating a 'data' directory on the same level as bearchoke-backend and bearchoke-frontend and then starting MongoDb up by typing:

	$ mongod --dbpath data/ &

Create a database called 'bearchoke' with credentials: bearchoke / beachoke

## Jpa (required for 'jpa' spring profile)
Jpa is the standard Java Persistence API. To showcase the framework's Jpa support, we're using an in-memory version of [DerbyDb](http://db.apache.org/derby/). There is no installation necessary.

## Lineman
In order to install Lineman, you also need to install the latest version of [Node.JS](http://www.nodejs.org/). Once Node.JS is installed, you can install Lineman by typing this in your Terminal / Command Prompt:

	$ sudo npm install -g lineman
	
The last requirement for Lineman is that you have [PhantomJS](http://phantomjs.org/download.html) installed and on your PATH.

## Grunt
Once you have installed Lineman, Grunt is next. This one is simple. Just type this in your Terminal / Command Prompt:

	$ npm install -g grunt-cli	

## Getting Up and Running
To see a basic web application that showcases the technologies mentioned above, you need to start the bearchoke-server web app and start the bearchoke-frontend app. To do this without and IDE, first make sure you have already run the command I mentioned before:

	$ cd bearchoke-backend
	$ mvn clean install -Dmaven.test.skip=true
	
Then type the following into your Terminal / Command Prompt:

	$ cd bearchoke-server
	$ mvn tomcat7:run -Dspring.profiles.active=embedded
	
Alternatively, if you want the sample app to run in the Jetty application server you can type:

	$ mvn jetty:run -Dspring.profiles.active=embedded
	
You can verify that the web application is running by going to http://localhost:8080 in your browser.

Next, you have to start the bearchoke-frontend application. Type the following in a new Terminal / Command Prompt window:

	$ cd bearchoke-frontend
	$ lineman run
	
You can verify that it's running by going to http://localhost:8000 in your browser. This is the sample app. Enjoy!

## What the Framework Gives You
There are a few things the Bearchoke Tempest gives you out of the box. 

**On the backend, the framework gives you:**

* Spring Security authentication implementations with a device agnostic session manager to help keep state across devices and protocols.
* Spring Social implementations. Current profiles are embedded, mongodb and jpa.
* Beans to help you quickly get set up with using Command Query Responsibility Segregation (CQRS) and Enterprise Integration Patterns (EIP).
* Examples on how to create versioned, REST based, controller request mappings using Spring Web MVC.
* Examples on how to create Web Socket URL mappings using Spring Web Socket with StompJS.
* Spring Profiles so you can choose what type of authentication implementation you want to use. Current profiles are embedded, mongodb and jpa.
	* Embedded is a pure in-memory configuration (except you still need to have Redis and RabbitMQ running)
	* MongoDb uses MongoDb to store its authentication tokens and user profiles
	* Jpa uses DerbyDb to store its authentication tokens and user profiles
You are not restricted to just using one Spring profile at the time, but it is easier if you choose one persistence implementation and run with it. If your project requires a NoSQL database for example, choose the 'mongodb' profile.

**On the frontend, the framework gives you:**

* Model-View-Controller and Dependency Injection framework [and much MUCH more] with AngularJS.
* A fully featured build tool with LinemanJS. Much like Maven, it offers code separation. Controllers, views, css, services and third party libraries are all separated into different directories. Lineman concatenates, minifies, uglyfies etc all the javascript and css into 2 files that are then being used by the application. Files you are working on are updated in real-time and you just have to refresh your browser.
* Remember me functionality using local storage
* Bootstrap for building responsive web applications
* Client-side translation using Angular-Translate
* Restangular for use with REST-based server-side calls
* Ui-router to handle your page setup and navigation
* StompJS client for communication via websocket
* Lodash and underscore javascript utility libraries
* Template based views with AngularJS
* Javascript code separation with Lineman. Controllers, views, css, services and third party libraries are all separated into different directories. Lineman concatenates, minifies, uglyfies etc all the javascript and css into 2 files that are then being used by the application.
* Existing services handle Facebook authentication, remember me, MixPanel analytics, local storage 
* [Less CSS](http://lesscss.org/) is a CSS pre-processor that adds new features that allow variables, mixins, functions and many other techniques that allow you to make CSS that is more maintainable, themable and extendable.

## Current release
2014-10-16 - 0.0.1 Initial version

## Next release features
This framework is for me a way to prove that the technologies can co-exist together [peacefully] and that they work and are testable. As my other projects require new features, I will add these here first as a test bed. Upcoming features I already want to add are:

* Facebook integration
* MixPanel integration
* File upload example
* Charting and reporting

If you would like to commit to this project you are more than welcome to do so. You can message me on Twitter at @bjornharvold or on Github.