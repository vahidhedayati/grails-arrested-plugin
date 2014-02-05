import grails.converters.JSON

class SecurityFilters {
    def filters = {
        all(uri: "/**") {
            before = {
                if(controllerName.equals('auth') || controllerName.equals('index') || actionName.equals('index') || actionName.equals('list') || actionName.equals('create') || actionName.equals('edit') || (controllerName.equals('arrestedUser') && actionName.equals('newItem'))){
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