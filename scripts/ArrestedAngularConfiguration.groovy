includeTargets << grailsScript("_GrailsArgParsing")
includeTargets << new File ("${arrestedPluginDir}/Scripts/_ArrestedInternal.groovy")

USAGE = """
    arrested-angular-configuration
"""

target(default: "Create the angular file configuration") {
    // Make sure any arguments have been parsed.
    depends(parseArguments, createAngularApp)
}