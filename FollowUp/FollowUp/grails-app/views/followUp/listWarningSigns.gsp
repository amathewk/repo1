<div style="text-align: center;">
    <h3>Warning Signs</h3>
    <g:include view="/followUp/menu/warningSignsMenu.gsp"/>
</div>

<div id="warningSignsContent">
    <ul style="padding-left: 5em">
        <g:each in="${warningSigns}" var="warningSign">
            <li>${warningSign.text?.replace("\n","<br/>")}&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="font-size: .7em">
                    <g:remoteLink update="layoutBody" controller="warningSigns" action="edit"
                                  params="[id: warningSign.id]">edit</g:remoteLink>
                    <g:remoteLink update="layoutBody" controller="warningSigns" action="delete"
                                  params="[warningSignId: warningSign.id]">delete</g:remoteLink>
                </span>
            </li>
        </g:each>
    </ul>
</div>
