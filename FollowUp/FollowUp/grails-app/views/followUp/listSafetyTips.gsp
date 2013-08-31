<div style="text-align: center;">
    <h3>Safety Tip</h3>
    <g:include view="/followUp/menu/safetyTipsMenu.gsp"/>
</div>

<div id="safetyTipsContent">
    <ul style="padding-left: 5em">
        <g:each in="${safetyTips}" var="safetyTip">
            <li>${safetyTip.text}&nbsp;&nbsp;&nbsp;&nbsp;
                <span style="font-size: .7em">
                    <g:remoteLink update="layoutBody" controller="safetyTips" action="edit"
                                  params="[id: safetyTip.id]">edit</g:remoteLink>
                    <g:remoteLink update="layoutBody" controller="safetyTips" action="delete"
                                  params="[safetyTipId: safetyTip.id]">delete</g:remoteLink>
                </span>
            </li>
        </g:each>
    </ul>
</div>
