function test() {

    var dat = '{"class":"net.followup.Patient","id":1,"firstName":"Leo","followUp":{"class":"net.followup.FollowUp","id":1,"copingOutsides":[{"class":"net.followup.CopingOutside","id":1,"followUp":{"_ref":"../..","class":"net.followup.FollowUp"},"text":"we  fwe"}],"personalContacts":[],"professionalContacts":[],"safetyTips":[{"class":"net.followup.SafetyTip","id":1,"followUp":{"_ref":"../..","class":"net.followup.FollowUp"},"text":"eeeeeeeeee"}],"selfCopings":[{"class":"net.followup.SelfCoping","id":1,"followUp":{"_ref":"../..","class":"net.followup.FollowUp"},"text":"wweee"}],"warningSigns":[{"class":"net.followup.WarningSign","id":1,"followUp":{"_ref":"../..","class":"net.followup.FollowUp"},"text":"sdfasdf"}]},"lastName":"Parker","patientId":"123","personalContacts":[{"class":"net.followup.PersonalContact","id":1,"address":{"address1":"1008 forest willow lane","address2":"","city":"morrisville","class":"net.followup.Address","country":"United States","state":"NC","zip":"72560"},"cellPhoneNumber":"6142264078","emailAddress":"brown1166@gmail.com","firstName":"Alicia","lastName":"Mathew","patient":{"_ref":"../..","class":"net.followup.Patient"},"workPhoneNumber":"6142264078"}],"professionalContacts":[{"class":"net.followup.ProfessionalContact","id":1,"address":{"address1":"1008 Forest Willow Lane","address2":"","city":"Morrisville","class":"net.followup.Address","country":"United States","state":"NC","zip":"72560"},"cellPhoneNumber":"6143094703","emailAddress":"amathewk1@gmail.com","firstName":"Anil","lastName":"Mathew","organization":null,"patient":{"_ref":"../..","class":"net.followup.Patient"},"workPhoneNumber":"6143094703"}]}';


    var dt = JSON && JSON.parse(dat) || $.parseJSON(dat);

    // alert(dt.firstName);

//    var request = new XMLHttpRequest();
//    request.open("GET", "http://followup.aam.cloudbees.net/sync?patientId=123", true);
//    request.onreadystatechange = function() {
//        if (request.readyState == 4) {
//            if (request.status == 200 || request.status == 0) {
//                var myXML = request.responseXML;
//                alert(1);
//                alert(myXML);
//            }
//        }
//    }
//    request.send();

    alert(125);
    $.ajax({
        url: "http://followup.aam.cloudbees.net/sync?patientId=123",
        crossDomain: true,
        success: function(data) {
            alert(data);
        }
    });


}

$(test())