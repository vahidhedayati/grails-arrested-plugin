includeTargets << grailsScript("_GrailsArgParsing")
includeTargets << new File ("${arrestedPluginDir}/Scripts/_ArrestedInternal.groovy")

USAGE = """
    arrested-create-view-controller [--name=NAME]

where
    NAME = The prefix to add to the names of the controller .
"""

target(default: "test") {
    // Make sure any arguments have been parsed.
    depends(parseArguments, createViewController)
}

