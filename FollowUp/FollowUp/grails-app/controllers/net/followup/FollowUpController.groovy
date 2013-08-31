package net.followup

import net.followup.FollowUp
import org.springframework.dao.DataIntegrityViolationException

class FollowUpController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [followUpInstanceList: FollowUp.list(params), followUpInstanceTotal: FollowUp.count()]
    }

    def create() {
        [followUpInstance: new FollowUp(params)]
    }

    def save() {
        def followUpInstance = new FollowUp(params)
        if (!followUpInstance.save(flush: true)) {
            render(view: "create", model: [followUpInstance: followUpInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'followUp.label', default: 'FollowUp'), followUpInstance.id])
        redirect(action: "show", id: followUpInstance.id)
    }

    def show(Long id) {
        def followUpInstance = FollowUp.get(id)
        if (!followUpInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'followUp.label', default: 'FollowUp'), id])
            redirect(action: "list")
            return
        }

        [followUpInstance: followUpInstance]
    }

    def edit(Long id) {
        def followUpInstance = FollowUp.get(id)
        if (!followUpInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'followUp.label', default: 'FollowUp'), id])
            redirect(action: "list")
            return
        }

        [followUpInstance: followUpInstance]
    }

    def update(Long id, Long version) {
        def followUpInstance = FollowUp.get(id)
        if (!followUpInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'followUp.label', default: 'FollowUp'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (followUpInstance.version > version) {
                followUpInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'followUp.label', default: 'FollowUp')] as Object[],
                        "Another user has updated this FollowUp while you were editing")
                render(view: "edit", model: [followUpInstance: followUpInstance])
                return
            }
        }

        followUpInstance.properties = params

        if (!followUpInstance.save(flush: true)) {
            render(view: "edit", model: [followUpInstance: followUpInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'followUp.label', default: 'FollowUp'), followUpInstance.id])
        redirect(action: "show", id: followUpInstance.id)
    }

    def delete(Long id) {
        def followUpInstance = FollowUp.get(id)
        if (!followUpInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'followUp.label', default: 'FollowUp'), id])
            redirect(action: "list")
            return
        }

        try {
            followUpInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'followUp.label', default: 'FollowUp'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'followUp.label', default: 'FollowUp'), id])
            redirect(action: "show", id: id)
        }
    }
}
