import grails.converters.JSON

class SecurityFilters {
    def filters = {
        all(uri: "/**") {
            before = {
                if(controllerName.equals('auth') || (controllerName.equals('arrestedUser') && actionName.equals('create'))){
                    return true
                }
                else{
                    ArrestedToken token = ArrestedToken.findByToken(params.token as String)
                    if(token){
                        if(token.valid){
                            return true
                        }
                    }
                    def message = [response:"bad_token"]
                    render message as JSON
                    return false
                }
            }
        }
    }
}