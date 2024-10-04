package xcode.biz.domain.model

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import xcode.biz.enums.UserRole
import java.util.*

@Entity
@Table(name = "t_user")
@DynamicUpdate
class User {

    @Id
    @Column(name = "id", length = 36)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id = 0

    @Column(name = "company_id")
    var companyId = ""

    @Column(name = "full_name")
    var fullName = ""

    @Column(name = "mobile")
    var mobile = ""

    @Column(name = "username")
    var username = ""

    @Column(name = "email")
    var email = ""

    @Column(name = "password")
    var password = ""

    @Column(name = "role")
    var role: UserRole = UserRole.CUSTOMER

    @Column(name = "created_at")
    var createdAt: Date? = null

    @Column(name = "updated_at")
    var updatedAt: Date? = null

    @Column(name = "verified_at")
    var verifiedAt: Date? = null

    @Column(name = "deleted_at")
    var deletedAt: Date? = null

}