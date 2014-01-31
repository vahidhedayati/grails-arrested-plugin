includeTargets << grailsScript("_GrailsArgParsing")
includeTargets << new File ("${arrestedPluginDir}/Scripts/_ArrestedInternal.groovy")

USAGE = """
    arrested-user
"""

target(default: "Create the user class") {
    // Make sure any arguments have been parsed.
    depends(parseArguments, createUser)
    depends(parseArguments, createUserController)
}