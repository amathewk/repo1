<div style="text-align: center;">
    <h3>Coping Outside</h3>
    <g:include view="/followUp/menu/copingOutsidesMenu.gsp"/>
</div>

<div id="copingOutsidesContent">
    <ul style="padding-left: 5em">
        <g:each in="${copingOutsides}" var="copingOutside">
            <li>${copingOutside.text?.replace("\n","<br/>")}&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="font-size: .7em">
                    <g:remoteLink update="layoutBody" controller="copingOutsides" action="edit"
                                  params="[id: copingOutside.id]">edit</g:remoteLink>
                    <g:remoteLink update="layoutBody" controller="copingOutsides" action="delete"
                                  params="[copingOutsideId: copingOutside.id]">delete</g:remoteLink>
                </span>
            </li>
        </g:each>
    </ul>
</div>
