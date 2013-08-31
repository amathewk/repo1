package net.soadna.remotecms.server

class User {

	String username
	String password
	String name
	boolean admin
	Customer customer
	
	static belongsTo = Customer
	
    static constraints = {
		customer(nullable:false)
		username(unique:true)
		password(password:true)
		name()
		admin()
    }
	
	String toString() {
		name
	}
}
