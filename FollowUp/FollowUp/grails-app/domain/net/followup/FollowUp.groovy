package net.followup

class FollowUp {

//    List<WarningSign> warningSigns = new ArrayList<WarningSign>()
//    List<SelfCoping> selfCoping = new ArrayList<SelfCoping>()
//    List<CopingOutside> copingOutside = new ArrayList<CopingOutside>()
//    List<PersonalContact> friendsAndFamily = new ArrayList<PersonalContact>()
//    List<ProfessionalContact> professionals = new ArrayList<ProfessionalContact>()
//    List<SafetyTip> safetyTips = new ArrayList<SafetyTip>()

    static belongsTo = [Patient]
    static hasMany = [warningSigns : WarningSign, selfCopings: SelfCoping, copingOutsides : CopingOutside, personalContacts : PersonalContact, professionalContacts : ProfessionalContact, safetyTips : SafetyTip]
//    static hasMany = [warningSigns : WarningSign]

    static constraints = {

    }
}
