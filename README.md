grails-arrested-plugin
======================

The Arrested plugin adds AngularJS and REST support to your GORM models.  It provides scripts to generate RESTful controllers to your models and views too!

Server REST API

To make all the configuration the application needs you can use the command:

> grails arrested-server

Or you can use only what you need using the commands:

> grails arrested-token
This create a token class to use in your security layer
> grails arrested-user
This create a user class which implements the token in the security layer
> grails arrested-auth
This create the authentication controller (login and logout functions)
> grails arrested-filter
This create the security filter to handle calls to the server
> grails arrested-url
This change the Url Mappings to configure the http requests