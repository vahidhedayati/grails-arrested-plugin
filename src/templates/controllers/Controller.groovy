@ package

.line @

import grails.converters.JSON

class @controller.name @ {

    static allowedMethods = [getById: "GET", getAll: "GET", newItem: "POST", update: "PUT", delete: "DELETE"]

    def index() {}

    def getById() {
        render @ class.name @.findById(params.id as Long) as JSON
    }

    def getAll() {
        def areas = []
        @ class.name @.list().each {
            areas.add(it)
        }
        render areas as JSON
    }

    def newItem() {
        def message = [response: "@class.name@_not_created", id: ""]
        if (params.@class.instance @) {
            @ class.name @item = new @ class.name @(params.@class.instance @).save(flush: true)
            message.response = "@class.name@_created"
            message.id = item.id
        }
        render message as JSON
    }

    def update() {
        def message = [response: "@class.name@_not_updated"]
        if (params.@class.instance @) {
            @ class.name @item = @ class.name @.findById(params.@class.instance @.id as Long)
            if (item) {
                item.properties = params.@class.instance @
                item.save(flush: true)
                message.response = "@class.name@_updated"

            }
        }
        render message as JSON
    }

    def delete() {
        def message = [response: "@class.name@_not_deleted"]
        @ class.name @item = @ class.name @.findById(params.id as Long)
        if (item) {

            item.delete(flush: true)
            message.response = "@class.name@_deleted"
        }
        render message as JSON
    }
}
