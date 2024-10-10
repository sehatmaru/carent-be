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
import xcode.biz.enums.PaymentStatus
import xcode.biz.enums.PaymentType
import java.util.Date

@Entity
@Table(name = "t_bill")
@DynamicUpdate
class Bill {

    @Id
    @Column(name = "id", length = 36)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id = 0

    @Column(name = "customer_id")
    var customerId: Int? = null

    @Column(name = "order_id")
    var orderId: Int? = null

    @Column(name = "total_payment")
    var totalPayment: Int? = null

    @Column(name = "total_paid")
    var totalPaid: Int? = null

    @Column(name = "application_fee")
    var applicationFee: Double? = null

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    var paymentType: PaymentType? = null

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    var paymentStatus: PaymentStatus? = null

    @Column(name = "created_at")
    var createdAt: Date? = null

    @Column(name = "updated_at")
    var updatedAt: Date? = null

    @Column(name = "verified_at")
    var verifiedAt: Date? = null

    @Column(name = "deleted_at")
    var deletedAt: Date? = null
}
