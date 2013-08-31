package net.followup.domain

import net.followup.FollowUp
import grails.test.mixin.*
import net.followup.FollowUpController

@TestFor(FollowUpController)
@Mock(FollowUp)
class FollowUpControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/followUp/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.followUpInstanceList.size() == 0
        assert model.followUpInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.followUpInstance != null
    }

    void testSave() {
        controller.save()

        assert model.followUpInstance != null
        assert view == '/followUp/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/followUp/show/1'
        assert controller.flash.message != null
        assert FollowUp.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/followUp/list'

        populateValidParams(params)
        def followUp = new FollowUp(params)

        assert followUp.save() != null

        params.id = followUp.id

        def model = controller.show()

        assert model.followUpInstance == followUp
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/followUp/list'

        populateValidParams(params)
        def followUp = new FollowUp(params)

        assert followUp.save() != null

        params.id = followUp.id

        def model = controller.edit()

        assert model.followUpInstance == followUp
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/followUp/list'

        response.reset()

        populateValidParams(params)
        def followUp = new FollowUp(params)

        assert followUp.save() != null

        // test invalid parameters in update
        params.id = followUp.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/followUp/edit"
        assert model.followUpInstance != null

        followUp.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/followUp/show/$followUp.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        followUp.clearErrors()

        populateValidParams(params)
        params.id = followUp.id
        params.version = -1
        controller.update()

        assert view == "/followUp/edit"
        assert model.followUpInstance != null
        assert model.followUpInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/followUp/list'

        response.reset()

        populateValidParams(params)
        def followUp = new FollowUp(params)

        assert followUp.save() != null
        assert FollowUp.count() == 1

        params.id = followUp.id

        controller.delete()

        assert FollowUp.count() == 0
        assert FollowUp.get(followUp.id) == null
        assert response.redirectedUrl == '/followUp/list'
    }
}
