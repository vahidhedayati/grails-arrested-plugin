class SecurityFilters {
    def filters = {
        all(uri: "/**") {
            before = {
                if(controllerName.equals('auth') || (controllerName.equals('arrestedUser') && actionName.equals('save')) || (controllerName.equals(null) && actionName.equals(null))){
                    return true
                }
                else{
                    if(params.token){
                        ArrestedToken token = ArrestedToken.findByToken(params.token as String)
                        if(token){
                            if(token.valid){
                                return true
                            }
                        }
                    }
                    return false
                }
            }
        }
    }
}