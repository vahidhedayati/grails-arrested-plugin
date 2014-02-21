includeTargets << grailsScript("_GrailsArgParsing")
includeTargets << new File (arrestedPluginDir, "Scripts/_ArrestedInternal.groovy")

USAGE = """
    grails create-arrested-controller [NAME]
"""

target(createArrestedController: "This creates the RESTful controller and maps it in the UrlMappings") {
    depends(parseArguments, createController)
}

setDefaultTarget 'createArrestedController'
