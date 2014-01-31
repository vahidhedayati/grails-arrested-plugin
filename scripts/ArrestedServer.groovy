includeTargets << grailsScript("_GrailsArgParsing")
includeTargets << new File ("${arrestedPluginDir}/Scripts/_ArrestedInternal.groovy")

USAGE = """
    arrested-server
"""

target(default: "Make the configuration of the server REST API") {
    // Make sure any arguments have been parsed.
    depends(parseArguments, createToken)
    depends(parseArguments, createUser)
    depends(parseArguments, createUserController)
    depends(parseArguments, createAuth)
    depends(parseArguments, createFilter)
    depends(parseArguments, updateUrl)
}