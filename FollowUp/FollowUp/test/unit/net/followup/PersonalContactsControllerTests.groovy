package net.followup

import grails.test.mixin.*

@TestFor(PersonalContactsController)
@Mock(PersonalContact)
class PersonalContactsControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/personalContact/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.personalContactInstanceList.size() == 0
        assert model.personalContactInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.personalContactInstance != null
    }

    void testSave() {
        controller.save()

        assert model.personalContactInstance != null
        assert view == '/personalContact/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/personalContact/show/1'
        assert controller.flash.message != null
        assert PersonalContact.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/personalContact/list'

        populateValidParams(params)
        def personalContact = new PersonalContact(params)

        assert personalContact.save() != null

        params.id = personalContact.id

        def model = controller.show()

        assert model.personalContactInstance == personalContact
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/personalContact/list'

        populateValidParams(params)
        def personalContact = new PersonalContact(params)

        assert personalContact.save() != null

        params.id = personalContact.id

        def model = controller.edit()

        assert model.personalContactInstance == personalContact
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/personalContact/list'

        response.reset()

        populateValidParams(params)
        def personalContact = new PersonalContact(params)

        assert personalContact.save() != null

        // test invalid parameters in update
        params.id = personalContact.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/personalContact/edit"
        assert model.personalContactInstance != null

        personalContact.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/personalContact/show/$personalContact.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        personalContact.clearErrors()

        populateValidParams(params)
        params.id = personalContact.id
        params.version = -1
        controller.update()

        assert view == "/personalContact/edit"
        assert model.personalContactInstance != null
        assert model.personalContactInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/personalContact/list'

        response.reset()

        populateValidParams(params)
        def personalContact = new PersonalContact(params)

        assert personalContact.save() != null
        assert PersonalContact.count() == 1

        params.id = personalContact.id

        controller.delete()

        assert PersonalContact.count() == 0
        assert PersonalContact.get(personalContact.id) == null
        assert response.redirectedUrl == '/personalContact/list'
    }
}
