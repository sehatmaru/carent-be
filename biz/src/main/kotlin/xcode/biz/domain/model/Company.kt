package xcode.biz.domain.model

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import xcode.biz.enums.UserRole
import java.util.*

@Entity
@Table(name = "t_company")
@DynamicUpdate
class Company {

    @Id
    @Column(name = "id", length = 36)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id = 0

    @Column(name = "firm_name")
    var firmName = ""

    @Column(name = "alias_name")
    var aliasName = ""

    @Column(name = "address")
    var address = ""

    @Column(name = "mobile")
    var mobile = ""

    @Column(name = "role")
    var role: UserRole = UserRole.CUSTOMER

    @Column(name = "founding_date")
    var foundingDate: Date? = null

}