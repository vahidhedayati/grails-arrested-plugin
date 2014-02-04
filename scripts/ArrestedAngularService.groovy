includeTargets << grailsScript("_GrailsArgParsing")
includeTargets << new File ("${arrestedPluginDir}/Scripts/_ArrestedInternal.groovy")

USAGE = """
    arrested-angular-service
"""

target(default: "Create the angular service") {
    // Make sure any arguments have been parsed.
    depends(parseArguments, createAngularService)
}