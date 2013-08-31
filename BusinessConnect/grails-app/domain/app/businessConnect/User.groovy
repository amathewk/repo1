package app.businessConnect

class User {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	MembershipLevel membershipLevel
	static hasMany = [entries : Entry]
	
	static constraints = {
		username blank: false, unique: true
		password blank: false
		membershipLevel nullable: true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
	
	boolean atMembershipLevelMax () {
		int entriesCount = Entry.findAllByMember(this)?.size() ?: 0
		return membershipLevel?.entriesLimit == entriesCount 
	}
	
	static List<User> nonAdminUsers() {
		// TODO: RETURN BY ROLE != ADMIN
		return User.findAllByUsernameNotEqual('admin')
	}
}
