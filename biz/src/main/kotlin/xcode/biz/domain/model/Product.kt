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
import xcode.biz.enums.ProductStatus
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

    @Column(name = "province_id")
    var provinceId: Int? = null

    @Column(name = "province_name")
    var provinceName = ""

    @Column(name = "regency_id")
    var regencyId: Int? = null

    @Column(name = "regency_name")
    var regencyName = ""

    @Column(name = "district_id")
    var districtId: Int? = null

    @Column(name = "district_name")
    var districtName = ""

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: ProductStatus? = null

    @Column(name = "created_at")
    var createdAt: Date? = null

    @Column(name = "updated_at")
    var updatedAt: Date? = null

    @Column(name = "deleted_at")
    var deletedAt: Date? = null
}
