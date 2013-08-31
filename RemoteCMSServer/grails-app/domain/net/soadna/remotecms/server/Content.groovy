package net.soadna.remotecms.server

class Content {
	
	String divId
	String text
	Site site
	
	static hasMany = [ tag : Tag ]
	
	static belongsTo = Site
	
    static constraints = {
		site ()
		divId ()
		text (nullable: true)
    }
	
	static mapping = {
		text type:"text"
	}

	String toString()  {
		return  "${site}:${divId}";
	}
}
