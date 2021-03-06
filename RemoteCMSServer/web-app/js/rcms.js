RCMS = {
			gElems : new Array
		,
		 	// "main" method
		 	main : function(document)
			{
				var className = "rcms";
				RCMS.displayElements(RCMS.getElementsByClass(className, document.documentElement));
			}
 		
 		,
			// Finds all DOM elements with ids starting with the specified prefix.
			getElementsByClass : function(className,inRoot)
			{
				var elem_array = new Array;
				if(typeof inRoot.firstChild!= 'undefined')
				{
					var elem = inRoot.firstChild;
					while (elem!= null)
					{
						if(typeof elem.firstChild!= 'undefined')
						{
							elem_array = elem_array.concat(RCMS.getElementsByClass(className, elem));
						}
						if(typeof elem.className != 'undefined' && elem.className && typeof elem.id != 'undefined' )
						{
							var reg = new RegExp ( '.*'+className+'.*' );
							if(elem.className.match(reg))
							{
								elem_array.push(elem);
							}
						}
						elem = elem.nextSibling;
					}
				}
				gElems = elem_array;
				return elem_array;
			} 
		
		,
			// Assigns content to the elements in the array.
			displayElements : function(in_elem_array)
			{
				if(in_elem_array.length)
				{
					var params = ""
					
					for(var c=0; c<in_elem_array.length; c++)
					{
						RCMS.gId=in_elem_array[c].id;
						// RCMS.getContent(in_elem_array[c].id, c);
						params += "divId=" + in_elem_array[c].id + "&index=" + c + "&"
					}
					RCMS.getContent(params.substring(0, params.length-1));
				}
			}
		,
			getContent : function (params)
			{
				document.write("<s"+"cript s"+"rc=\"" + "http://cms.soa-dna.com/contentJs" + "?" + params + "\"></s"+"cript>")
			}
			
		,
			// Gets browser dependent ajax request object
			getXmlHttpObject : function ()
			{
				if (window.XMLHttpRequest)
				  {
				  // code for IE7+, Firefox, Chrome, Opera, Safari
				  return new XMLHttpRequest();
				  }
				if (window.ActiveXObject)
				  {
				  // code for IE6, IE5
				  return new ActiveXObject("Microsoft.XMLHTTP");
				  }
				return null;
			}
	} ;

	// Holder for callbacks tied to an ajax request
	function CallBackHolder(elemId,xmlhttp) {
	this.elemId=elemId;
	this.xmlhttp=xmlhttp;
	}

	function view() {
		with (this) 
		{
			if (xmlhttp.readyState==4) 
			{
				document.getElementById(elemId).innerHTML=xmlhttp.responseText ;
			}
		}
	}

	CallBackHolder.prototype.view=view;
	
	try {
		 RCMS.main(document);
		//RCMS.getContent("welcome");
	} catch(err) { 
		// TODO: send error log to server?
	}

