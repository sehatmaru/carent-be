package xcode.biz.domain.response.admin

import xcode.biz.enums.UserRole
import java.util.Date

class AdminResponse {
    var id: Int? = null
    var companyId: Int? = null
    var fullName = ""
    var mobile = ""
    var username = ""
    var email = ""
    var role: UserRole = UserRole.TENANT_ADMIN
    var createdAt: Date? = null
    var updatedAt: Date? = null
    var verifiedAt: Date? = null
    var deletedAt: Date? = null
}
