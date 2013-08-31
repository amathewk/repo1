package app.businessConnect

class GlobalPreferencesController {

	def scaffold = GlobalPreferences
    def index = { }
	def showGlobalPreferences = {
		redirect(action:'show', id:'1')
	}
}
