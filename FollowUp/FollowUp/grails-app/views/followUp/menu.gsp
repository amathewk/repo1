<g:if test="${session['patientId']}">
    <div style="padding: 10px">
        <span class="menuButton"><g:remoteLink name="warningSigns" update="layoutBody" controller="warningSigns" action="list"><button>Warning Signs</button></g:remoteLink> </span>
        <span class="menuButton"><g:remoteLink name="selfCoping" update="layoutBody" controller="selfCopings" action="list"><button>Self Coping</button></g:remoteLink> </span>
        <span class="menuButton"><g:remoteLink name="copingOutside" update="layoutBody" controller="copingOutsides" action="list"><button>Coping Outside</button></g:remoteLink> </span>
        <span class="menuButton"><g:remoteLink name="friendsAndFamily" update="layoutBody" controller="personalContacts" action="list"><button>Friends And Family</button></g:remoteLink></span>
        <span class="menuButton"><g:remoteLink name="professionalContacts" update="layoutBody" controller="professionalContacts" action="list"><button>Professional Contacts</button></g:remoteLink> </span>
        <span class="menuButton"><g:remoteLink name="safetyTips" update="layoutBody" controller="safetyTips" action="list"><button>Safety Tips</button></g:remoteLink> </span>
    </div>

</g:if>