package net.soadna.remotecms.server

class UserController extends BaseController {

	def scaffold = User
	
	def login = {}
	
	def authenticate = {
		def user = User.findByUsernameAndPassword(params.username, params.password, [fetch:[customer:'eager']])
		if (user)
		{
			session.user = user
			redirect(controller:"site")
		}
		else
		{
			flash.message="Sorry, please try again."
			redirect(action:login)
		}
	}
	
	
	def logout = {
		flash.message = "Goodbye $session.user.username"
		session.invalidate()
		
		redirect(action:"login")
	}
	
}
