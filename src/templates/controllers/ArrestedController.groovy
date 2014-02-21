package arrested

import grails.converters.JSON
import grails.converters.XML

class ArrestedController {

    def renderNotFound(id, className){
        withFormat{
            xml {
                response.status = 404
                render className+" "+id+" not found"
            }
            json {
                response.status = 404
                render className+" "+id+" not found"
            }
        }
    }

    def render409orEdit(instance){
        withFormat{
            xml {
                response.status = 409
                render instance.errors.allErrors as XML
            }
            json {
                response.status = 409
                render instance.errors.allErrors as JSON
            }
        }
    }

    def renderconflict(conflict){
        withFormat{
            xml {
                response.status = 409
                render conflict
            }
            json {
                response.status = 409
                render conflict
            }
        }
    }

    def renderMisingParam(param){
        withFormat{
            xml {
                response.status = 412
                render param+" expected"
            }
            json {
                response.status = 412
                render param+" expected"
            }
        }
    }
}