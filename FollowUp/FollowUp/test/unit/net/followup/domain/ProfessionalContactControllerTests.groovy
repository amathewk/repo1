package net.followup.domain

import net.followup.ProfessionalContact
import grails.test.mixin.*
import net.followup.ProfessionalContactController

@TestFor(ProfessionalContactController)
@Mock(ProfessionalContact)
class ProfessionalContactControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/professionalContact/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.professionalContactInstanceList.size() == 0
        assert model.professionalContactInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.professionalContactInstance != null
    }

    void testSave() {
        controller.save()

        assert model.professionalContactInstance != null
        assert view == '/professionalContact/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/professionalContact/show/1'
        assert controller.flash.message != null
        assert ProfessionalContact.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/professionalContact/list'

        populateValidParams(params)
        def professionalContact = new ProfessionalContact(params)

        assert professionalContact.save() != null

        params.id = professionalContact.id

        def model = controller.show()

        assert model.professionalContactInstance == professionalContact
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/professionalContact/list'

        populateValidParams(params)
        def professionalContact = new ProfessionalContact(params)

        assert professionalContact.save() != null

        params.id = professionalContact.id

        def model = controller.edit()

        assert model.professionalContactInstance == professionalContact
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/professionalContact/list'

        response.reset()

        populateValidParams(params)
        def professionalContact = new ProfessionalContact(params)

        assert professionalContact.save() != null

        // test invalid parameters in update
        params.id = professionalContact.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/professionalContact/edit"
        assert model.professionalContactInstance != null

        professionalContact.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/professionalContact/show/$professionalContact.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        professionalContact.clearErrors()

        populateValidParams(params)
        params.id = professionalContact.id
        params.version = -1
        controller.update()

        assert view == "/professionalContact/edit"
        assert model.professionalContactInstance != null
        assert model.professionalContactInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/professionalContact/list'

        response.reset()

        populateValidParams(params)
        def professionalContact = new ProfessionalContact(params)

        assert professionalContact.save() != null
        assert ProfessionalContact.count() == 1

        params.id = professionalContact.id

        controller.delete()

        assert ProfessionalContact.count() == 0
        assert ProfessionalContact.get(professionalContact.id) == null
        assert response.redirectedUrl == '/professionalContact/list'
    }
}
