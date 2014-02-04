grails-arrested-plugin
======================

The Arrested plugin adds AngularJS and REST support to your GORM models.  It provides scripts to generate RESTful controllers to your models and views too!

Installing the plugin is only the first step to make your application a REST application.
You need have your domain classes ready to start the process.

Server REST API

On the server side you only need use this command to make the basic configuration.

> grails arrested-server

With this you would have your domain classes for security model, and the url mapping configuration.

Or you can use only what you need using the commands:

> grails arrested-token | This create a token class to use in your security layer

> grails arrested-user | This create a user class which implements the token in the security layer

> grails arrested-auth | This create the authentication controller (login and logout functions)

> grails arrested-filter | This create the security filter to handle calls to the server

> grails arrested-url | This change the Url Mappings to configure the http requests

Cliente side, angularJS configurations

> arrested-createJS-controller --name=NAME | This command takes a 'name' argument: using 'NAME' as the Domain class, and create a angular controller for the client

> arrested-create-controller --name=NAME | This command takes a 'name' argument: using 'NAME' as the Domain class, and create REST controller on the server

> 
