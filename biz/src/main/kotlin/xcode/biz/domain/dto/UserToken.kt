package xcode.biz.domain.dto

import xcode.biz.enums.CredentialType
import xcode.biz.enums.UserRole
import java.util.Date

class UserToken {

    var id: Int = 0
    var companyId: Int = 0
    var createdBy: Int = 0
    var fullName = ""
    var mobile = ""
    var username = ""
    var email = ""
    var credentialNo = ""
    var credentialType: CredentialType? = null
    var password = ""
    var role: UserRole = UserRole.CUSTOMER
    var rating: Int? = null
    var createdAt: Date? = null
    var updatedAt: Date? = null
    var verifiedAt: Date? = null
    var deletedAt: Date? = null
    var token = ""

    fun isVerified(): Boolean {
        return this.verifiedAt != null
    }

    fun isManager(): Boolean {
        return this.role == UserRole.TENANT_MANAGER
    }
}
