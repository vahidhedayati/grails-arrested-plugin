includeTargets << grailsScript("_GrailsArgParsing")
includeTargets << new File ("${arrestedPluginDir}/Scripts/_ArrestedInternal.groovy")

USAGE = """
   create-arrested-app
"""

target(default: "Token, User, SecurityFilter, and the base JS files and mapping defaults for REST") {
    depends(parseArguments, createToken)
    depends(parseArguments, createUser)
    depends(parseArguments, createArrestedController)
    depends(parseArguments, createUserController)
    depends(parseArguments, createAngularUser)
    depends(parseArguments, createAuth)
    depends(parseArguments, createFilter)
    depends(parseArguments, updateUrl)
    depends(parseArguments, updateResources)
    depends(parseArguments, createAngularService)
    depends(parseArguments, createAngularIndex)
    depends(parseArguments, updateLayout)
}