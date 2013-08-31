package net.followup

class SearchController {

    def patient(String patientId, String firstName, String lastName) {
        log.debug(" patientId : $patientId, firstName : $firstName, lastName : $lastName ")
        if (!patientId && !(firstName && lastName) ) {
            render "Please enter either the patient id or both the firstName and lastName"
        } else {

            List<Patient> results
            if (patientId) {
                results = Patient.findAllByPatientId(patientId)
            }  else {
                results = Patient.findAllByLastNameAndFirstName(lastName, firstName)
            }

            render(model: [patientInstanceList: results, patientInstanceTotal:results.size()], view: "/patient/patientList")

//            render "${results[0]?.lastName}"
//            render (view: "/patient/list")
//            render "!23"

        }

    }

    def patientForm() {
        render (view:"searchForm")
    }

    def select() {
        Patient patient = Patient.findById(params.id)
        log.debug("Selected patient is : $patient")
        session["patientId"] = patient.patientId
        session["lastName"] = patient.lastName
        session["firstName"] = patient.firstName
        redirect(url: "/")
    }
}
