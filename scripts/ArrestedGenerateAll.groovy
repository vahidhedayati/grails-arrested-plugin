includeTargets << grailsScript("_GrailsArgParsing")
includeTargets << new File ("${arrestedPluginDir}/Scripts/_ArrestedInternal.groovy")

USAGE = """
    arrested-generate-all
"""

target(default: "quick start") {
    // Make sure any arguments have been parsed.
    depends(parseArguments, createAll)
    depends(parseArguments, createToken)
    depends(parseArguments, createUser)
    depends(parseArguments, createUserController)
    depends(parseArguments, createAuth)
    depends(parseArguments, createFilter)
    depends(parseArguments, updateUrl)
}