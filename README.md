grails-arrested-plugin
======================

The Arrested plugin is a framework that generates RESTful controllers for your GORM objects and maps them in your UrlMappings, generates AngularJS based views in the form a single page per domain entity, and finally it provides a simple token based security model.

AngularJs + RESTful = Arrested


# Installation:

Add plugin Dependency in BuildConfig.groovy :
>compile ":arrested:1.2"


# Getting Started

We need to set up a couple things before we get started generating your REST controllers and Angular views.  Namely we need our User and Token Entities, a Security Filter that validates a token header field, and some base Angular js files that act as a DAO to your rest services.

To get started with arrested run:

> grails create-arrested-app


# domainClass 
To generate a REST domainClass run: 
(This will generate a Controller and put the appropriate configurations in UrlMapping to be RESTful)
> grails create-domain-class DomainClassName

Once you have generated a domainClass ensure you add at least one element inside it before proceeding to the next step:

# Controller

To generate a REST controller run: 
(This will generate a Controller and put the appropriate configurations in UrlMapping to be RESTful)

> grails create-arrested-controller DomainClassName

# View
To generate views for your newly created REST controller run: 
(This will generate a index.gsp file in your views directory ie. /views/domainclassname/index.gsp; and the javascript files to interact with your REST controller.  It's configured to use the security token and will pass a token on each request for data.)

> grails create-arrested-view DomainClassName


# Security


# How to Integrate

### Creating a user at startup:

```

BootStrap:
```
import org.apache.shiro.crypto.hash.Sha256Hash

import arrested.ArrestedToken
import arrested.ArrestedUser

class BootStrap {

   def init = { servletContext ->
	ArrestedUser user,user1
		ArrestedToken token,token1
		
		user = new ArrestedUser(
			username: "me@domain.com",
			passwordHash: new Sha256Hash("password").toHex(),
			dateCreated: new Date()
		).save()
		
		 //Create tokens for users
		token = new ArrestedToken(
			token: 'token',
			valid: true,
			owner: user.id
		).save(flush: true)
		user.setToken(token.id)
		user.save()
		
    
    }
    def destroy = {
    }
}
```

Configuration completed


Limitations:

1. The relationship does not appear to be working or passed between authors and books - possibly using a true DB will get around this issue, since a refresh to main site also logs user back out.

2. Your project name should not have - (dashes) for some reason an example project called arrested-test doing this exact task bypasses authentication - and does not allow any record manipluation, after a few tests it turned out to be related to dashes in project name.
 

```


