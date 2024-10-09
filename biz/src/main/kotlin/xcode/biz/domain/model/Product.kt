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
import xcode.biz.enums.VehicleStatus
import java.util.Date

@Entity
@Table(name = "t_product")
@DynamicUpdate
class Product {

    @Id
    @Column(name = "id", length = 36)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id = 0

    @Column(name = "vehicle_id")
    var vehicleId: Int? = null

    @Column(name = "name")
    var name = ""

    @Column(name = "price")
    var price: Int? = null

    @Column(name = "seat")
    var seat: Int? = null

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: VehicleStatus? = null

    @Column(name = "created_at")
    var createdAt: Date? = null

    @Column(name = "updated_at")
    var updatedAt: Date? = null

    @Column(name = "deleted_at")
    var deletedAt: Date? = null
}
