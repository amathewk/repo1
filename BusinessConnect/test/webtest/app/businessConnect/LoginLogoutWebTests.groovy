package app.businessConnect


class LoginLogoutWebTests extends grails.util.WebTest {

    // Unlike unit tests, functional tests are sometimes sequence dependent.
    // Methods starting with 'test' will be run automatically in alphabetical order.
    // If you require a specific sequence, prefix the method name (following 'test') with a sequence
    // e.g. test001XclassNameXListNewDelete

   void test01LoginPageShows() {
        invoke '/'
        clickLink(htmlId:"login")
		verifyText(text:'Username:')
        verifyText(text:'Password:')
    }

   void test02LoginWorks() {
	   	invoke '/login'
   		setInputField(value:'mason',name:'j_username')
   		setInputField(value:'mason',name:'j_password')
		clickButton(label:'Login')
		verifyText(text:'Welcome')
   }
   
   void test03LogoutWorks() {
	   invoke '/login'
	   setInputField(value:'mason',name:'j_username')
	   setInputField(value:'mason',name:'j_password')
	   clickButton(label:'Login')
	   clickLink(htmlId:'logoff')
	   verifyText(text:'Log In')
   }
   
}