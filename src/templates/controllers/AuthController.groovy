import grails.converters.JSON
import grails.converters.XML
import arrested.ArrestedController

class AuthController extends ArrestedController {

    static allowedMethods = [login: "POST", logout: "GET"]

    def login(){
        if(params.username){
            if(params.passwordHash){
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
                        renderconflict("Username and/or password incorrect")
                    }
                }
                else{
                    renderconflict("Username and/or password incorrect")
                }
            }
            else{
                renderNotParam("passwordHash")
            }
        }
        else{
            renderNotParam("username")
        }
    }

    def logout() {
        if(params.token){
            ArrestedToken token = ArrestedToken.findByToken(params.token as String)
            if(token){
                ArrestedUser user = ArrestedUser.findByToken(token.id)
                if(user){
                    token.setValid(false)
                    token.save(flush: true)
                    withFormat{
                        xml {
                            render "Logout successfully"
                        }
                        json  {
                            render "Logout successfully"
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
}