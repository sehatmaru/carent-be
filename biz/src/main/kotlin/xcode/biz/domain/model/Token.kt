package xcode.biz.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate
import xcode.biz.enums.TokenType
import xcode.biz.enums.UserRole
import java.util.Date

@Entity
@Table(name = "t_token")
@DynamicUpdate
class Token {

    @Id
    @Column(name = "id", length = 36)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private val id = 0

    @Column(name = "user_id")
    var userId = id

    @Column(name = "code")
    var code = ""

    @Column(name = "created_at")
    var createdAt: Date? = null

    @Column(name = "expire_at")
    var expireAt: Date? = null

    @Column(name = "is_active")
    var isActive = false

    @Column(name = "user_role")
    var userRole: UserRole? = UserRole.CUSTOMER

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    var type: TokenType? = TokenType.NON_OTP

    fun isValid(): Boolean {
        return !expireAt!!.before(Date())
    }

    fun isManager(): Boolean {
        return this.userRole == UserRole.TENANT_MANAGER
    }

    fun isAdmin(): Boolean {
        return this.userRole == UserRole.TENANT_ADMIN
    }

    fun isCustomer(): Boolean {
        return this.userRole == UserRole.CUSTOMER
    }
}
