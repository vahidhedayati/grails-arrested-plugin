includeTargets << grailsScript("_GrailsArgParsing")
includeTargets << new File ("${arrestedPluginDir}/Scripts/_ArrestedInternal.groovy")

USAGE = """
    arrested-client
"""

target(default: "Make the configuration of the client angularJS") {
    // Make sure any arguments have been parsed.
    depends(parseArguments, createClient)
}