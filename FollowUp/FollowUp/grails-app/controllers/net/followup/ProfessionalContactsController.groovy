package net.followup

import org.springframework.dao.DataIntegrityViolationException

class ProfessionalContactsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
//        params.max = Math.min(max ?: 10, 100)

        String patientId = session["patientId"]
        Patient patient = Patient.findByPatientId(patientId)
        if (patient) {
            List<ProfessionalContact> professionalContacts = patient?.professionalContacts?.sort{it.id}
            render (model:[professionalContactInstanceList:professionalContacts, professionalContactInstanceTotal: professionalContacts.size()], view:"/professionalContacts/list" )
        }
//        [professionalContactInstanceList: ProfessionalContact.list(params), professionalContactInstanceTotal: ProfessionalContact.count()]
    }

    def create() {
        render (model: [professionalContactInstance: new ProfessionalContact()], view: "/professionalContacts/create")
//        [professionalContactInstance: new ProfessionalContact(params)]
    }

    def save() {
        log.debug "saving professional contact"

        def professionalContactInstance = new ProfessionalContact(params)
//        professionalContactInstance.address = new Address(params)
//        professionalContactInstance.address = new Address()
//        professionalContactInstance.address.properties = params


        String patientId = session["patientId"]
        Patient patient = Patient.findByPatientId(patientId)
        professionalContactInstance.patient = patient

        if (!professionalContactInstance.save(flush: true)) {
            render(view: "create", model: [professionalContactInstance: professionalContactInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'professionalContact.label', default: 'ProfessionalContact'), "$professionalContactInstance.lastName,$professionalContactInstance.firstName"])
        redirect(action: "list")
    }

    def show(Long id) {
        def professionalContactInstance = ProfessionalContact.get(id)
        if (!professionalContactInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'professionalContact.label', default: 'ProfessionalContact'), id])
            redirect(action: "list")
            return
        }

        [professionalContactInstance: professionalContactInstance]
    }

    def edit(Long id) {
        def professionalContactInstance = ProfessionalContact.get(id)
        if (!professionalContactInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'professionalContact.label', default: 'ProfessionalContact'), id])
            redirect(action: "list")
            return
        }

        [professionalContactInstance: professionalContactInstance]
    }

    def update(Long id, Long version) {
        def professionalContactInstance = ProfessionalContact.get(id)
        if (!professionalContactInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'professionalContact.label', default: 'ProfessionalContact'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (professionalContactInstance.version > version) {
                professionalContactInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'professionalContact.label', default: 'ProfessionalContact')] as Object[],
                        "Another user has updated this ProfessionalContact while you were editing")
                render(view: "edit", model: [professionalContactInstance: professionalContactInstance])
                return
            }
        }

        professionalContactInstance.properties = params

        if (!professionalContactInstance.save(flush: true)) {
            render(view: "edit", model: [professionalContactInstance: professionalContactInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'professionalContact.label', default: 'ProfessionalContact'), "$professionalContactInstance.lastName,$professionalContactInstance.firstName"])
        redirect(action: "list")
    }

    def delete(Long id) {
        def professionalContactInstance = ProfessionalContact.get(id)
        def fullName = "$professionalContactInstance.lastName,$professionalContactInstance.firstName"
        if (!professionalContactInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'professionalContact.label', default: 'ProfessionalContact'), fullName])
            redirect(action: "list")
            return
        }

        try {
            professionalContactInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'professionalContact.label', default: 'ProfessionalContact'), fullName])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'professionalContact.label', default: 'ProfessionalContact'), fullName])
            redirect(action: "edit", id: id)
        }
    }
}
