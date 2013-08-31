package net.followup

class PersonalContact {

    String firstName
    String lastName
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
        workPhoneNumber()
        cellPhoneNumber()
        emailAddress email: true
        address nullable: true
    }
}
