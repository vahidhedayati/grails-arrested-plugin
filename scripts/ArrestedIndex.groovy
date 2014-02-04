includeTargets << grailsScript("_GrailsArgParsing")
includeTargets << new File ("${arrestedPluginDir}/Scripts/_ArrestedInternal.groovy")

USAGE = """
    arrested-index
"""

target(default: "Create the index page and controller") {
    // Make sure any arguments have been parsed.
    depends(parseArguments, createIndexController)
    depends(parseArguments, createIndexView)
}