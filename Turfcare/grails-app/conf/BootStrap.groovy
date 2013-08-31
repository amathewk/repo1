class BootStrap {

     def init = { servletContext ->

	     new Event(title:"Trade Show", description:"Trade Show in New York", eventDate: new Date()).save()
	     
     	     new Location(name:"Lowes", streetAddress:'2345 Silver Drive', city: 'Columbus', state: 'OH',zip: '', phone: "123234234").save()
	     
	     new Location(name:"Lowes", streetAddress:'3616 East Broad Street', city: 'Columbus', state: 'OH',zip: '', phone: "123234234").save()
	     
     	     new Location(name:"Lowes", streetAddress:'2888 Brice Road', city: 'Columbus', state: 'OH',zip: '', phone: "123234234").save()
	     
     	     new Location(name:"Lowes", streetAddress:'24500 Miles Road', city: 'Cleveland', state: 'OH',zip: '', phone: "123234234").save()
	     
     	     new Location(name:"Lowes", streetAddress:'9149 Pearl Road', city: 'Cleveland', state: 'OH',zip: '', phone: "123234234").save()
	     
     	     new Location(name:"Lowes", streetAddress:'8224 Golden Link Boulevard', city: 'Northfield', state: 'OH',zip: '', phone: "123234234").save()
	     
     	     new Location(name:"Lowes", streetAddress:'7034 Charlotte Pike', city: 'Nashville', state: 'TN',zip: '', phone: "123234234").save()
	     
     	     new Location(name:"Lowes", streetAddress:'4531 Nolensville Pike', city: 'Nashville', state: 'TN',zip: '', phone: "123234234").save()
     }
     def destroy = {
     }
} 