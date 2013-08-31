package net.soadna.remotecms.server

class Customer {
	
	String name
	String address
	String phoneNumber
	
	static hasMany = [site:Site, user:User]
	
    static constraints = { 
		name()
		address()
		phoneNumber()
    }
	
	String toString()  {
		return name;
	}
}
