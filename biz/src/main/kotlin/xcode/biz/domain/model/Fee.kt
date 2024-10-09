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
import xcode.biz.enums.FeeType

@Entity
@Table(name = "t_fee")
@DynamicUpdate
class Fee {

    @Id
    @Column(name = "id", length = 36)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id = 0

    @Column(name = "percentage")
    var percentage: Double? = null

    @Column(name = "price")
    var price: Int? = null

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    var type: FeeType? = null
}
