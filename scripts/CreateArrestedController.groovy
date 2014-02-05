includeTargets << grailsScript("_GrailsArgParsing")
includeTargets << new File ("${arrestedPluginDir}/Scripts/_ArrestedInternal.groovy")

USAGE = """
    create-arrested-controller [NAME]
"""

target(default: "This creates the RESTful controller and maps it in the UrlMappings") {
    depends(parseArguments, createController)
}