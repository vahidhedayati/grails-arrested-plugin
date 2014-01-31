includeTargets << grailsScript("_GrailsArgParsing")
includeTargets << new File ("${arrestedPluginDir}/Scripts/_ArrestedInternal.groovy")

USAGE = """
    arrested-filter
"""

target(default: "Create the security filter") {
    // Make sure any arguments have been parsed.
    depends(parseArguments, createFilter)
}