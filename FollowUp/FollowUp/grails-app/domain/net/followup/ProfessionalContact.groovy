package net.followup

class ProfessionalContact {

    String firstName
    String lastName
    String organization
    String workPhoneNumber
    String cellPhoneNumber
    String emailAddress
    Address address
    Patient patient

    static belongsTo = [patient : Patient]
    static embedded = ['address']
    static constraints = {
        firstName blank: false
        lastName blank: false
        organization   nullable: true
        workPhoneNumber blank: false
        cellPhoneNumber blank: false
        emailAddress email: true
        address nullable: true
    }
}
