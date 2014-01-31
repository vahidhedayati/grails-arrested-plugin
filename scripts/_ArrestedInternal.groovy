import grails.util.GrailsNameUtils

installTemplate = { String artefactName, String artefactPath, String templatePath ->
    installTemplateEx(artefactName, artefactPath, templatePath, artefactName, null)
}

installTemplateEx = { String artefactName, String artefactPath, String templatePath, String templateName, Closure c ->
    // Copy over the standard auth controller.
    def artefactFile = "${basedir}/${artefactPath}/${artefactName}"

    if (new File(artefactFile).exists()) {
        ant.input(
                addProperty: "${args}.${artefactName}.overwrite",
                message: "${artefactName} already exists. Overwrite? [y/n]")

        if (ant.antProject.properties."${args}.${artefactName}.overwrite" == "n") {
            return
        }
    }

    // Copy the template file to the 'grails-app/controllers' directory.
    templateFile = "${arrestedPluginDir}/src/templates/${templatePath}/${templateName}"
    println(templateFile)
    if (!new File(templateFile).exists()) {
        ant.echo("[Arrested plugin] Error: src/templates/${templatePath}/${templateName} does not exist!")
        return
    }

    ant.copy(file: templateFile, tofile: artefactFile, overwrite: true)

    // Perform any custom processing that may be required.
    if (c) {
        c.delegate = [ artefactFile: artefactFile ]
        c.call()
    }
    event("CreatedFile", [artefactFile])
}

//**************************** Create a single controller

target(createController: "Creates a standard controller") {
    def (pkg, prefix) = parsePrefix()
    // Copy over the standard filters class.
    def className = prefix+"Controller"
    installTemplateEx("${className}.groovy", "grails-app/controllers${packageToPath(pkg)}", "controllers", "Controller.groovy") {
        ant.replace(file: artefactFile) {
            ant.replacefilter(token: "@package.line@", value: (pkg ? "package ${pkg}\n\n" : ""))
            ant.replacefilter(token: '@controller.name@', value: className)
            ant.replacefilter(token: '@class.name@', value: prefix.toUpperCase())
            ant.replacefilter(token: '@class.instance@', value: prefix)
        }
    }
}
}


//**************************** Create a single Angular controller

target(createJSController: "Creates a standard angular controller") {
    def (pkg, prefix) = parsePrefix()
    // Copy over the standard filters class.
    def className = prefix+"Ctrl"
    installTemplateEx("${className}.js", "web-app/js/${packageToPath(pkg)}", "views/controllers", "Controller.js") {
        ant.replace(file: artefactFile) {
            ant.replacefilter(token: '@controller.name@', value: className)
            ant.replacefilter(token: '@class.name@', value: prefix.toUpperCase())
            ant.replacefilter(token: '@class.instance@', value: prefix)
        }
    }
target(createToken: "Create a token class") {
    def (pkg, prefix) = parsePrefix()
    installTemplateEx("ArrestedToken.groovy", "grails-app/domain${packageToPath(pkg)}", "classes", "ArrestedToken.groovy") {
        ant.replace(file: artefactFile) {
            ant.replacefilter(token: "@package.line@", value: (pkg ? "package ${pkg}\n\n" : ""))
        }
    }
}

target(createUser: "Create a user class") {
    def (pkg, prefix) = parsePrefix()
    installTemplateEx("ArrestedUser.groovy", "grails-app/domain${packageToPath(pkg)}", "classes", "ArrestedUser.groovy") {
        ant.replace(file: artefactFile) {
            ant.replacefilter(token: "@package.line@", value: (pkg ? "package ${pkg}\n\n" : ""))
        }
    }
}

//****************************  Creates view

target(createViewController: "Creates view") {
    depends(loadApp)
    def (pkg, prefix) = parsePrefix()

    String name = prefix
    name = name.indexOf('.') > 0 ? name : GrailsNameUtils.getClassNameRepresentation(name)
    def domainClass = grailsApp.getDomainClass(name)

    println(domainClass)

//    generateForDomainClass(domainClass)
//    def domainFile = "${basedir}/grails-app/domain/${packageToPath(pkg)}"+prefix+".groovy"
//    if (new File(domainFile).exists()) {
//        Class clazz = Class.forName("${prefix}", true, Thread.currentThread().getContextClassLoader())
//        def className = prefix
//        installTemplateEx("list.gsp", "grails-app/views/${packageToPath(pkg)}${className}", "views/view", "list.gsp") {
//            ant.replace(file: artefactFile) {
//                ant.replacefilter(token: "@class.name@", value: prefix.toUpperCase())
//                ant.replacefilter(token: '@class.instance@', value: prefix)
//            }
//        }
//    }
}

//void generateForDomainClass(domainClass) {
//    def DefaultGrailsTemplateGenerator = classLoader.loadClass('org.codehaus.groovy.grails.scaffolding.DefaultGrailsTemplateGenerator')
//
//    def templateGenerator = DefaultGrailsTemplateGenerator.newInstance(classLoader)
//    templateGenerator.grailsApplication = grailsApp
//    templateGenerator.pluginManager = pluginManager
//        event("StatusUpdate", ["Generating views for domain class ${domainClass.fullName}"])
//        templateGenerator.generateViews(domainClass, basedir)
//        event("GenerateViewsEnd", [domainClass.fullName])
//
//}
target(createUserController: "Create a user class") {
    def (pkg, prefix) = parsePrefix()
    installTemplateEx("ArrestedUserController.groovy", "grails-app/controllers${packageToPath(pkg)}", "controllers", "ArrestedUserController.groovy") {
        ant.replace(file: artefactFile) {
            ant.replacefilter(token: "@package.line@", value: (pkg ? "package ${pkg}\n\n" : ""))
        }
    }
}

target(createAuth: "Create a authentication controller") {
    def (pkg, prefix) = parsePrefix()
    installTemplateEx("AuthController.groovy", "grails-app/controllers${packageToPath(pkg)}", "controllers", "AuthController.groovy") {
        ant.replace(file: artefactFile) {
            ant.replacefilter(token: "@package.line@", value: (pkg ? "package ${pkg}\n\n" : ""))
        }
    }
}

target(createFilter: "Create a security filter") {
    def (pkg, prefix) = parsePrefix()
    installTemplateEx("SecurityFilters.groovy", "grails-app/conf${packageToPath(pkg)}", "configuration", "SecurityFilters.groovy") {
        ant.replace(file: artefactFile) {
            ant.replacefilter(token: "@package.line@", value: (pkg ? "package ${pkg}\n\n" : ""))
        }
    }
}

target(updateUrl: "Updating the Url Mappings") {
    def (pkg, prefix) = parsePrefix()
    installTemplateEx("UrlMappings.groovy", "grails-app/conf${packageToPath(pkg)}", "configuration", "UrlMappings.groovy") {
        ant.replace(file: artefactFile) {
            ant.replacefilter(token: "@package.line@", value: (pkg ? "package ${pkg}\n\n" : ""))
        }
    }
}

private parsePrefix() {
    def prefix = "Arrested"
    def pkg = ""
    if (argsMap["name"] != null) {
        def givenValue = argsMap["name"].split(/\./, -1)
        prefix = givenValue[-1]
        pkg = givenValue.size() > 1 ? givenValue[0..-2].join('.') : ""
    }
    return [ pkg, prefix ]
}

private packageToPath(String pkg) {
    return pkg ? '/' + pkg.replace('.' as char, '/' as char) : ''
}