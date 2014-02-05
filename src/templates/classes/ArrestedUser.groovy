class ArrestedUser {
    String username
    String passwordHash
    Date dateCreated
    Date lastUpdated
    Long token

    static constraints = {
        username nullable: false, blank: false, unique: true
        passwordHash nullable: false, blank: false
        dateCreated nullable: true
        lastUpdated nullable: true
        token nullable: true
    }

    def beforeInsert() {
        dateCreated = new Date()
        lastUpdated = new Date()
    }

    def beforeUpdate() {
        lastUpdated = new Date()
    }

    def toObject(){
        return [id: this.id,
                username: this.username,
                token: ArrestedToken.findById(this.token)?.token]
    }

    def showInformation(){
        return [id: this.id,
                username: this.username]
    }
}