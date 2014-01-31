includeTargets << grailsScript("_GrailsArgParsing")
includeTargets << new File ("${arrestedPluginDir}/Scripts/_ArrestedInternal.groovy")

USAGE = """
    arrested-auth
"""

target(default: "Create the authentication controller") {
    // Make sure any arguments have been parsed.
    depends(parseArguments, createAuth)
}