@package.line@

import grails.converters.JSON

class AuthController {

    static allowedMethods = [login: "POST", logout: "GET"]

    def login(){
        def message = [response:"bad_login"]
        if(params.passwordHash && params.username){
            ArrestedUser user = ArrestedUser.findByUsername(params.username as String)
            if(user){
                if (user.passwordHash == params.passwordHash as String){
                    Date valid = new Date()
                    valid + 1
                    ArrestedToken token = ArrestedToken.findById(user.token)
                    if(!token){
                        user.setToken(new ArrestedToken( token: UUID.randomUUID().toString(), valid: true, owner: user.id).save(flush: true).id)
                        user.save(flush: true)
                    }else if(token.lastUpdated.time > valid.time || !token.valid){
                        token.token = UUID.randomUUID()
                        token.valid = true
                        token.save(flush: true)
                    }
                    render user.toObject() as JSON
                }
                else{
                    render message as JSON
                }
            }
            else{
                render message as JSON
            }
        }
        else{
            render message as JSON
        }
    }

    def logout() {
        def message = [response:"bad_logout"]
        if(params.token){
            ArrestedToken token = ArrestedToken.findByToken(params.token as String)
            ArrestedUser user = ArrestedUser.findByToken(token.id)
            if(user){
                token.setValid(false)
                token.save(flush: true)
                message.response = "logout_successfully"
            }
        }
        render message as JSON
    }
}