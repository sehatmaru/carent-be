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
@Table(name = "t_token")
@DynamicUpdate
class Token(@Column(name = "token") var token: String, id: Int, expireAt: Date?) {
    constructor() : this("", 0, null)

    @Id
    @Column(name = "id", length = 36)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private val id = 0

    @Column(name = "user_id")
    var userId = id

    @Column(name = "created_at")
    var createdAt: Date? = null

    @Column(name = "expire_at")
    private var expireAt: Date? = expireAt

    fun isValid(): Boolean {
        return !expireAt!!.before(Date())
    }
}
