package net.soadna.remotecms.server.tag

class LoginTagLib {
	
	def loginControl = {
		if (session && session.user)
		{
			out << "$session.user.customer : $session.user.username"
			out << "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
			out << """[${link(action:"logout", controller:"user"){"LOGOUT"}}]"""
		}
		else
		{
			out << """[${link(action:"login", controller:"user"){"LOGIN"}}]"""
		}
	}
}
