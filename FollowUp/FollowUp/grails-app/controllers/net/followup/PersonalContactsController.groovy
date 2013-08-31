package net.followup

import org.springframework.dao.DataIntegrityViolationException

class PersonalContactsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
//        params.max = Math.min(max ?: 10, 100)

        String patientId = session["patientId"]
        Patient patient = Patient.findByPatientId(patientId)
        if (patient) {
            List<PersonalContact> personalContacts = patient?.personalContacts?.sort{it.id}
            render (model:[personalContactInstanceList:personalContacts, personalContactInstanceTotal: personalContacts.size()], view:"/personalContacts/list" )
        }
//        [personalContactInstanceList: PersonalContact.list(params), personalContactInstanceTotal: PersonalContact.count()]
    }

    def create() {
        render (model: [personalContactInstance: new PersonalContact()], view: "/personalContacts/create")
//        [personalContactInstance: new PersonalContact(params)]
    }

    def save() {
        log.debug "saving personal contact"

        def personalContactInstance = new PersonalContact(params)
//        personalContactInstance.address = new Address(params)
//        personalContactInstance.address = new Address()
//        personalContactInstance.address.properties = params


        String patientId = session["patientId"]
        Patient patient = Patient.findByPatientId(patientId)
        personalContactInstance.patient = patient

        if (!personalContactInstance.save(flush: true)) {
            render(view: "create", model: [personalContactInstance: personalContactInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'personalContact.label', default: 'PersonalContact'), "$personalContactInstance.lastName,$personalContactInstance.firstName"])
        redirect(action: "list")
    }

    def show(Long id) {
        def personalContactInstance = PersonalContact.get(id)
        if (!personalContactInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'personalContact.label', default: 'PersonalContact'), id])
            redirect(action: "list")
            return
        }

        [personalContactInstance: personalContactInstance]
    }

    def edit(Long id) {
        def personalContactInstance = PersonalContact.get(id)
        if (!personalContactInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'personalContact.label', default: 'PersonalContact'), id])
            redirect(action: "list")
            return
        }

        [personalContactInstance: personalContactInstance]
    }

    def update(Long id, Long version) {
        def personalContactInstance = PersonalContact.get(id)
        if (!personalContactInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'personalContact.label', default: 'PersonalContact'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (personalContactInstance.version > version) {
                personalContactInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'personalContact.label', default: 'PersonalContact')] as Object[],
                          "Another user has updated this PersonalContact while you were editing")
                render(view: "edit", model: [personalContactInstance: personalContactInstance])
                return
            }
        }

        personalContactInstance.properties = params

        if (!personalContactInstance.save(flush: true)) {
            render(view: "edit", model: [personalContactInstance: personalContactInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'personalContact.label', default: 'PersonalContact'), "$personalContactInstance.lastName,$personalContactInstance.firstName"])
        redirect(action: "list")
    }

    def delete(Long id) {
        def personalContactInstance = PersonalContact.get(id)
        def fullName = "$personalContactInstance.lastName,$personalContactInstance.firstName"
        if (!personalContactInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'personalContact.label', default: 'PersonalContact'), fullName])
            redirect(action: "list")
            return
        }

        try {
            personalContactInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'personalContact.label', default: 'PersonalContact'), fullName])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'personalContact.label', default: 'PersonalContact'), fullName])
            redirect(action: "edit", id: id)
        }
    }
}
