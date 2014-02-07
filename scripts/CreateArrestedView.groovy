includeTargets << grailsScript("_GrailsArgParsing")
includeTargets << new File ("${arrestedPluginDir}/Scripts/_ArrestedInternal.groovy")

USAGE = """
   create-arrested-view [NAME]
"""

target(default: "This creates the GSP mapped at index (view is index.gsp) AND the javascript controller") {
    depends(parseArguments, createViewController)
    depends(parseArguments, createJSController)
    depends(parseArguments, createAngularIndex)
    depends(parseArguments, updateResources)
}