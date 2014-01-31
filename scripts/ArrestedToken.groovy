includeTargets << grailsScript("_GrailsArgParsing")
includeTargets << new File ("${arrestedPluginDir}/Scripts/_ArrestedInternal.groovy")

USAGE = """
    arrested-token
"""

target(default: "Create the token class") {
    // Make sure any arguments have been parsed.
    depends(parseArguments, createToken)
}