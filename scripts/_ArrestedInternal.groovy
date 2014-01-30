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

target(createController: "Creates a standard controller") {
    def (pkg, prefix) = parsePrefix()

    // Copy over the standard filters class.
    def className = "Arrested"+prefix
    installTemplateEx("${className}.groovy", "grails-app/controllers${packageToPath(pkg)}", "controllers", "Controller.groovy") {
        ant.replace(file: artefactFile) {
            ant.replacefilter(token: "@package.line@", value: (pkg ? "package ${pkg}\n\n" : ""))
            ant.replacefilter(token: '@controller.name@', value: className)
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
