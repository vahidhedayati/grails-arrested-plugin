includeTargets << grailsScript("_GrailsArgParsing")
includeTargets << new File ("${arrestedPluginDir}/Scripts/_ArrestedInternal.groovy")

USAGE = """
    arrested-url
"""

target(default: "Updating the Url Mappings") {
    // Make sure any arguments have been parsed.
    depends(parseArguments, updateResources)
}