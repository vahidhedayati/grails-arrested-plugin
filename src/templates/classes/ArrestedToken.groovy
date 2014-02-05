class ArrestedToken {
    String token
    boolean valid
    Date lastUpdated
    Long owner

    static constraints = {
        token nullable: false, blank: false
        valid nullable: true
        lastUpdated nullable: true
        owner nullable: true
    }

    def beforeInsert(){
        lastUpdated = new Date()
    }

    def beforeUpdate(){
        lastUpdated = new Date()
    }

    def showInformation(){
        return [id: this.id,
                token: this.token,
                lastUpdated: this.lastUpdated,
                valid: this.valid]
    }
}