package xcode.biz.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate
import java.util.Date

@Entity
@Table(name = "t_otp")
@DynamicUpdate
class Otp {

    @Id
    @Column(name = "id", length = 36)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id = 0

    @Column(name = "code")
    var code = ""

    @Column(name = "userId")
    var userId: Int? = null

    @Column(name = "verified_at")
    var verifiedAt: Date? = null

    @Column(name = "deleted_at")
    var deletedAt: Date? = null
}
