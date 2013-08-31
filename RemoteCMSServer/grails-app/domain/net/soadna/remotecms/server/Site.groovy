package net.soadna.remotecms.server

class Site {
	
	String domain
	Customer customer
	
	static belongsTo = Customer

//	static hasMany = [page:Page]
	static hasMany = [content:Content]
	
    static constraints = {
		customer()
		domain()
    }

	String toString()  {
		return "${customer}:${domain}";
	}
}
