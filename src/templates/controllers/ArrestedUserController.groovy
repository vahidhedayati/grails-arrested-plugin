import grails.converters.JSON

class ArrestedUserController {
    static allowedMethods = [getCurrent: "GET", getAll: "GET", create: "POST", update: "PUT", changePassword: "PUT", delete: "DELETE"]



    def getCurrent(){
        render ArrestedUser.findByToken(ArrestedToken.findByToken(params.token as String).id)?.toObject() as JSON
    }

    def getAll(){
        def users = []
        ArrestedUser.list().each{
            users.add(it.showInformation())
        }
        render users as JSON
    }

    def create(){
        def message = [response:"user_not_created"]
        if(params.user){
            if(ArrestedUser.findByUsername(params.user.username as String)){
                message.response = "username_used"
            }
            else{
                ArrestedUser user = new ArrestedUser(params.user)
                user.save(flush: true)
                message.response = "user_created"
            }
        }
        render message as JSON
    }

    def update(){
        def message = [response:"user_not_updated"]
        ArrestedUser user = ArrestedUser.findByToken(ArrestedToken.findByToken(params.token as String).id)
        if(user && params.user){
            if(user.username != params.user.username && ArrestedUser.findByUsername(params.user.username as String)){
                message.response = "username_used"
            }
            else{
                user.properties = params.user
                user.save(flush: true)
                message.response = "user_updated"
            }
        }
        render message as JSON
    }

    def changePassword(){
        def message = [response:"user_not_updated"]
        ArrestedUser user = ArrestedUser.findByToken(ArrestedToken.findByToken(params.token as String).id)
        if(user && params.currentPassword && params.newPassword){
            if(user.passwordHash == params.currentPassword){
                user.setPasswordHash(params.newPassword)
                user.save(flush: true)
                message.response = "user_updated"
            }
            else{
                message.response = "password_incorrect"
            }
        }
        render message as JSON
    }

    def delete(){
        def message = [response:"user_not_deleted"]
        ArrestedToken token = ArrestedToken.findByToken(params.token as String)
        if(token){
            ArrestedUser user = ArrestedUser.findByToken(token.id)
            if(user){
                token.delete(flush: true)
                user.delete(flush: true)
                message.response = "user_deleted"
            }
        }
        render message as JSON
    }
}