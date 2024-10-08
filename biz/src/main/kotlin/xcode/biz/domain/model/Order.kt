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
import xcode.biz.enums.OrderStatus
import xcode.biz.enums.PickupType
import java.util.Date

@Entity
@Table(name = "t_order")
@DynamicUpdate
class Order {

    @Id
    @Column(name = "id", length = 36)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id = 0

    @Column(name = "customer_id")
    var customerId: Int? = null

    @Column(name = "product_id")
    var productId: Int? = null

    @Column(name = "price")
    var price: Int? = null

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: OrderStatus? = null

    @Column(name = "pickup_type")
    @Enumerated(EnumType.STRING)
    var pickupType: PickupType? = null

    @Column(name = "rating")
    var rating: Int? = null

    @Column(name = "created_at")
    var createdAt: Date? = null

    @Column(name = "canceled_at")
    var canceledAt: Date? = null
}
