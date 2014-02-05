class SecurityFilters {
    def filters = {
        all(uri: "/**") {
            before = {
                if(controllerName.equals('auth') || actionName.equals('index') || actionName.equals('list') || actionName.equals('create') || actionName.equals('edit') || (controllerName.equals('arrestedUser') && actionName.equals('newItem')) || (controllerName.equals(null) && actionName.equals(null))){
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