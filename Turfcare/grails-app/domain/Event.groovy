class Event {

	String title
	String description
	Date eventDate
	
    static constraints = {
	    title()
	    description(widget:'textarea')
	    eventDate()
    }
}
