package app.businessConnect


class AdminSystemPreferencesWebTests extends grails.util.WebTest {

    // Unlike unit tests, functional tests are sometimes sequence dependent.
    // Methods starting with 'test' will be run automatically in alphabetical order.
    // If you require a specific sequence, prefix the method name (following 'test') with a sequence
    // e.g. test001XclassNameXListNewDelete

   void test01AdminGlobalPreferencePageShows() {
	   	invoke '/login'
   		setInputField(value:'admin',name:'j_username')
   		setInputField(value:'admin',name:'j_password')
		clickButton(label:'Login')
		verifyText(text:'Welcome')
		verifyText(text:'Global Preferences')
   }
   
   void test02AdminGlobalPreferencePageShows() {
	   invoke '/login'
	   setInputField(value:'admin',name:'j_username')
	   setInputField(value:'admin',name:'j_password')
	   clickButton(label:'Login')
	   verifyText(text:'Welcome')
	   verifyText(text:'Global Preferences')
	   clickButton(label:'Global Preferences')
	   verifyText(text:'Ad Display Count')
   }
   
}