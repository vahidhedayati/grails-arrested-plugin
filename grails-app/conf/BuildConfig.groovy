grails.project.work.dir = 'target'

grails.project.dependency.resolution = {

    inherits 'global'
    log 'warn'

    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }

    dependencies {
       /*  compile ('org.apache.shiro:shiro-core:1.2.1',
			'org.apache.shiro:shiro-web:1.2.1',
			'org.apache.shiro:shiro-spring:1.2.1',
			'org.apache.shiro:shiro-ehcache:1.2.1',
			'org.apache.shiro:shiro-quartz:1.2.1') {
			excludes 'ejb', 'jsf-api', 'servlet-api', 'jsp-api', 'jstl', 'jms',
			'connector-api', 'ehcache-core', 'slf4j-api', 'commons-logging'
			}
	*/		
    }

    plugins {
       // runtime ':cors:1.1.6'
        //runtime ':resources:1.2.8' 
        build ':release:2.2.1', ':rest-client-builder:1.0.3', {
            export = false
        }
        runtime ':shiro:1.2.1', {
		excludes 'quartz'
		excludes 'hibernate'
	}
    }
}
