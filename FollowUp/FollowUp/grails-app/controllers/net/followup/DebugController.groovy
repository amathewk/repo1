package net.followup

class DebugController {

    def loadTestData() {

        loadPatients()
        redirect(url: "/")
    }

    static loadPatients() {
        Patient patient1 = new Patient(firstName: "Leo", lastName: "Parker", patientId: "123", followUp: new FollowUp())
        Patient patient2 = new Patient(firstName: "Andrei", lastName: "Arshavin", patientId: "124", followUp: new FollowUp())
        Patient patient3 = new Patient(firstName: "Little", lastName: "John", patientId: "129", followUp: new FollowUp())
        Patient patient4 = new Patient(firstName: "Tim", lastName: "King", patientId: "131", followUp: new FollowUp())
        Patient patient5 = new Patient(firstName: "Robin", lastName: "Patton", patientId: "133", followUp: new FollowUp())

        [patient1,patient2,patient3,patient4,patient5].each{it.save()}
    }

}
