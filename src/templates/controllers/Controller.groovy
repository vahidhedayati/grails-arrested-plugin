@package.line@<% import grails.persistence.Event %>

import grails.converters.JSON

class @controller.name@ {

    static allowedMethods = [getById: "GET", getAll: "GET", newItem: "POST", update: "PUT", delete: "DELETE"]

    def index() {}

    def getById(){
        render @class.name@.findById(params.id as Long) as JSON
    }

    def getAll(){
        def areas = []
        @class.name@.list().each {
            areas.add(it)
        }
        render areas as JSON
    }

    def newItem(){
        def message = [response:"@class.name@_not_created", id:""]
        if(params.@class.instance@){
            <%if(!cp){%>@class.name@ item = new @class.name@(params.@class.instance@).save(flush: true)
            <%}else{%>
                @class.name@ item = new @class.name@()
                <%  excludedProps = Event.allEvents.toList() << 'id' << 'version'
                allowedNames = domainClass.name << 'dateCreated' << 'lastUpdated'
                props = domainClass.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) && it.type != null && !Collection.isAssignableFrom(it.type) }
                for (p in props) {
                    if (p.manyToOne || p.oneToOne){%>
                        if(params.@class.instance@.${p.name}) item.${p.name} = ${p.type.name}.findById(params.@class.instance@.${p.name}.id as Long)
                        <%}else if ((p.oneToMany && !p.bidirectional) || (p.manyToMany && p.isOwningSide())) {%>
                        params.@class.instance@.${p.name}.each{
                            item.${p.name}.add(${p.type.name}.findById(it.id as Long))
                        }
                        <%}else{%>
                        item.${p.name} = params.@class.instance@.${p.name}
                        <%}}}%>message.response = "@class.name@_created"
            message.id = item.id
        }
        render message as JSON
    }

    def update(){
        def message = [response:"@class.name@_not_updated"]
        if(params.@class.instance@){
            @class.name@ item = @class.name@.findById(params.@class.instance@.id as Long)
            if(item){
                <%if(!cp){%> item.properties = params.@class.instance@
                    <%}else{
                    excludedProps = Event.allEvents.toList() << 'id' << 'version'
                    allowedNames = domainClass.name << 'dateCreated' << 'lastUpdated'
                    props = domainClass.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) && it.type != null && !Collection.isAssignableFrom(it.type) }
                    for (p in props) {
                        if (p.manyToOne || p.oneToOne){%>
                            if(params.@class.instance@.${p.name}) item.${p.name} = ${p.type.name}.findById(params.@class.instance@.${p.name}.id as Long)
                            <%}else if ((p.oneToMany && !p.bidirectional) || (p.manyToMany && p.isOwningSide())) {%>
                            item.${p.name}?.clear()
                            params.@class.instance@.${p.name}.each{
                                item.${p.name}.add(${p.type.name}.findById(it.id as Long))
                            }
                            <%}else{%>
                            item.${p.name} = params.@class.instance@.${p.name}
                            <%}}}%>
                item.save(flush: true)
                message.response = "@class.name@_updated"
            }
        }
        render message as JSON
    }

    def delete(){
        def message = [response:"@class.name@_not_deleted"]
        @class.name@ item = @class.name@.findById(params.id as Long)
        if(item){

            item.delete(flush: true)
            message.response = "@class.name@_deleted"
        }
        render message as JSON
    }
}
