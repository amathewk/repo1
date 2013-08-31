package net.followup

import net.followup.FollowUp

class Patient {

    String patientId
    String firstName
    String lastName
    FollowUp followUp

    static hasMany = [personalContacts : PersonalContact, professionalContacts : ProfessionalContact]

    static constraints = {
        patientId nullable: true, unique: true
        firstName nullable: true
        lastName nullable: true
    }
}
