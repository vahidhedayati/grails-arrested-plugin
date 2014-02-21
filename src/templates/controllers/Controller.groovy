@package.line@<% import grails.persistence.Event %>import grails.converters.JSON
import grails.converters.XML
import arrested.ArrestedController

class @controller.name@ extends ArrestedController {

    static allowedMethods = [show: "GET", list: "GET", save: "POST", update: "PUT", delete: "DELETE"]

    def show(Long id) {
        if(id){
            @class.name@ instance = @class.name@.get(id)
            if(instance){
                withFormat{
                    xml {
                        render instance as XML
                    }
                    json {
                        render instance as JSON
                    }
                }
            }
            else{
                renderNotFound(id, "@class.name@")
            }
        }
        else{
            renderNotParam("id")
        }
    }

    def list() {
        def instances = @class.name@.list()
        withFormat{
            xml {
                render instances as XML
            }
            json {
                render instances as JSON
            }
        }
    }

    def save() {
        if (params.@class.instance@) {
            <%if(!cp){%>@class.name@ instance = new @class.name@(params.@class.instance@)<%}else{%>@class.name@ instance = new @class.name@() <%  excludedProps = Event.allEvents.toList() << 'id' << 'version'
                allowedNames = domainClass.name << 'dateCreated' << 'lastUpdated'
                props = domainClass.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) && it.type != null && !Collection.isAssignableFrom(it.type) }
                for (p in props) {
                    if (p.manyToOne || p.oneToOne){%>
                        if(params.@class.instance@.${p.name}) instance.${p.name} = ${p.type.name}.get(params.@class.instance@.${p.name}.id as Long)
                        <%}else if ((p.oneToMany && !p.bidirectional) || (p.manyToMany && p.isOwningSide())) {%>
                        params.@class.instance@.${p.name}.each{
                            instance.${p.name}.add(${p.type.name}.get(it.id as Long))
                        }
                        <%}else{%>
                        if(params.@class.instance@.${p.name}) instance.${p.name} = params.@class.instance@.${p.name}
                        <%}}}%>if(instance.save(flush: true)){
                withFormat {
                    xml {
                        response.status = 200
                        render instance as XML
                    }
                    json {
                        response.status = 200
                        render instance as JSON
                    }
                }
            }
            else{
                render409orEdit(instance)
            }
        }
        else{
            renderNotParam("@class.instance@")
        }
    }

    def update() {
        if (params.@class.instance@) {
            @class.name@ instance = @class.name@.get(params.@class.instance@.id as Long)
            if(instance){ <%if(!cp){%>instance.properties = params.@class.instance@
                    <%}else{
                    excludedProps = Event.allEvents.toList() << 'id' << 'version'
                    allowedNames = domainClass.name << 'dateCreated' << 'lastUpdated'
                    props = domainClass.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) && it.type != null && !Collection.isAssignableFrom(it.type) }
                    for (p in props) {
                        if (p.manyToOne || p.oneToOne){%>
                            if(params.@class.instance@.${p.name}) instance.${p.name} = ${p.type.name}.get(params.@class.instance@.${p.name}.id as Long)
                            <%}else if ((p.oneToMany && !p.bidirectional) || (p.manyToMany && p.isOwningSide())) {%>
                            params.@class.instance@.${p.name}.each{
                                instance.${p.name}.add(${p.type.name}.get(it.id as Long))
                            }
                            <%}else{%>
                            if(params.@class.instance@.${p.name}) instance.${p.name} = params.@class.instance@.${p.name}
                            <%}}}%>if(instance.save(flush: true)){
                    withFormat {
                        xml {
                            response.status = 200
                            render instance as XML
                        }
                        json {
                            response.status = 200
                            render instance as JSON
                        }
                    }
                }
                else{
                    render409orEdit(instance)
                }
            }
            else{
                renderNotFound(params.@class.instance@.id, "@class.name@")
            }
        }
        else{
            renderNotParam("@class.instance@")
        }
    }

    def delete(Long id) {
        if (id){
            @class.name@ instance = @class.name@.get(id)
            if (instance){
                instance.delete(flush: true)
                withFormat {
                    xml {
                        response.status = 200
                        render "@class.name@ deleted"
                    }
                    json {
                        response.status = 200
                        render "@class.name@ deleted"
                    }
                }
            }
            else{
                renderNotFound(id, "@class.name@")
            }
        }
        else{
            renderNotParam("id")
        }
    }
}
