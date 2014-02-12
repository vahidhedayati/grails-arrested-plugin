import grails.converters.JSON
import grails.converters.XML
import arrested.ArrestedController

class ArrestedUserController extends ArrestedController {

    static allowedMethods = [show: "GET", list: "GET", save: "POST", update: "PUT", delete: "DELETE"]

    def show() {
        if(params.token){
            ArrestedToken token = ArrestedToken.findByToken(params.token as String)
            if(token){
                ArrestedUser user = ArrestedUser.findByToken(token.id)
                if(user){
                    withFormat{
                        xml {
                            render user.toObject() as XML
                        }
                        json  {
                            render user.toObject() as JSON
                        }
                    }
                }
                else{
                    renderNotFound("", "User")
                }
            }
            else{
                renderNotFound("", "Token")
            }
        }
        else{
            renderNotParam("token")
        }
    }

    def list() {
        def users = []
        ArrestedUser.list().each {
            users.add(it.showInformation())
        }
        withFormat{
            xml {
                render users as XML
            }
            json  {
                render users as JSON
            }
        }
    }

    def save() {
        if (params.user) {
            if (ArrestedUser.findByUsername(params.user.username as String)) {
                renderconflict("Username used")
            } else {
                ArrestedUser user = new ArrestedUser(params.user)
                if(user.save(flush: true)){
                    withFormat {
                        xml {
                            response.status = 200
                            render user.toObject() as XML
                        }
                        json {
                            response.status = 200
                            render user.toObject() as JSON
                        }
                    }
                }
                else{
                    render409orEdit(user)
                }
            }
        }
        else{
            renderNotParam("user")
        }
    }

    def update() {
        if(params.user){
            if(params.token){
                ArrestedToken token = ArrestedToken.findByToken(params.token as String)
                if(token){
                    ArrestedUser user = ArrestedUser.findByToken(token.id)
                    if(user){
                        if (user.username != params.user.username && ArrestedUser.findByUsername(params.user.username as String)){
                            renderconflict("Username used")
                        } else {
                            user.properties = params.user
                            if(user.save(flush: true)){
                                withFormat {
                                    xml {
                                        response.status = 200
                                        render user.toObject() as XML
                                    }
                                    json {
                                        response.status = 200
                                        render user.toObject() as JSON
                                    }
                                }
                            }
                            else{
                                render409orEdit(user)
                            }
                        }
                    }
                    else{
                        renderNotFound(params.user.id, "User")
                    }
                }
                else{
                    renderNotFound("", "Token")
                }
            }
            else{
                renderNotParam("token")
            }
        }
        else{
            renderNotParam("user")
        }
    }

    def delete() {
        if(params.token){
            ArrestedToken token = ArrestedToken.findByToken(params.token as String)
            if (token){
                ArrestedUser user = ArrestedUser.findByToken(token.id)
                if (user){
                    if(token.delete(flush: true)){
                        if(user.delete(flush: true)){
                            withFormat {
                                xml {
                                    response.status = 200
                                    render "User deleted"
                                }
                                json {
                                    response.status = 200
                                    render "User deleted"
                                }
                            }
                        }
                        else{
                            renderconflict("User could not be deleted")
                        }
                    }
                    else{
                        renderconflict("Token could not be deleted")
                    }
                }
                else{
                    renderNotFound("", "User")
                }
            }
            else{
                renderNotFound("", "Token")
            }
        }
        else{
            renderNotParam("token")
        }
    }
}