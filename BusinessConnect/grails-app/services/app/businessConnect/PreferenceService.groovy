package app.businessConnect

class PreferenceService {

    static transactional = true

    def getGlobalConfigValue(String configKey) {
		GlobalPreferences.get(1)."$configKey" 
    }
}
