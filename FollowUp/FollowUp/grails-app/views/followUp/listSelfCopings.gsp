<div style="text-align: center;">
    <h3>Self Coping</h3>
    <g:include view="/followUp/menu/selfCopingsMenu.gsp"/>
</div>

<div id="selfCopingsContent">
    <ul style="padding-left: 5em">
        <g:each in="${selfCopings}" var="selfCoping">
            <li>${selfCoping.text?.replace("\n","<br/>")}&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="font-size: .7em">
                    <g:remoteLink update="layoutBody" controller="selfCopings" action="edit"
                                  params="[id: selfCoping.id]">edit</g:remoteLink>
                    <g:remoteLink update="layoutBody" controller="selfCopings" action="delete"
                                  params="[selfCopingId: selfCoping.id]">delete</g:remoteLink>
                </span>
            </li>
        </g:each>
    </ul>
</div>
