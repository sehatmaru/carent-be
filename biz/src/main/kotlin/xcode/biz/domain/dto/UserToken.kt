package xcode.biz.domain.dto

import xcode.biz.enums.UserRole
import java.util.Date

class UserToken {

    var id: Int? = null
    var companyId: Int? = null
    var createdBy: Int? = null
    var fullName = ""
    var mobile = ""
    var username = ""
    var email = ""
    var password = ""
    var role: UserRole = UserRole.CUSTOMER
    var rating: Int? = null
    var createdDate: Date? = null
    var updatedDate: Date? = null
    var verifiedDate: Date? = null
    var deletedDate: Date? = null
    var token = ""

    fun isVerified(): Boolean {
        return this.verifiedDate != null
    }

    fun isManager(): Boolean {
        return this.role == UserRole.TENANT_MANAGER
    }
}
