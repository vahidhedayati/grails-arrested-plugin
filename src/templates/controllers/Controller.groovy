@package.line@

import grails.converters.JSON

class @controller.name@ {

    static allowedMethods = [getById: "GET", getAll: "GET", create: "POST", update: "PUT", delete: "DELETE"]

    def getById(){
        render @class.name@.findById(params.id as Long).showInformation() as JSON
    }

    def getAll(){
        def areas = []
        @class.name@.list().each {
            areas.add(it.toObject())
        }
        render areas as JSON
    }

    def create(){
        def message = [response:"@class.name@_not_created", id:""]
        if(params.@class.instance@){
            @class.name@ item = new @class.name@(params.@class.instance@).save(flush: true)
            message.response = "@class.name@_created"
            message.id = item.id
        }
        render message as JSON
    }
}
