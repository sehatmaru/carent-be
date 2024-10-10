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
import xcode.biz.enums.EngineType
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand
import xcode.biz.enums.VehicleType
import java.util.Date

@Entity
@Table(name = "t_vehicle")
@DynamicUpdate
class Vehicle {

    @Id
    @Column(name = "id", length = 36)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id = 0

    @Column(name = "company_id")
    var companyId: Int? = null

    @Column(name = "name")
    var name = ""

    @Column(name = "vehicle_type")
    @Enumerated(EnumType.STRING)
    var vehicleType: VehicleType? = null

    @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    var transmission: Transmission? = null

    @Column(name = "year")
    var year = ""

    @Column(name = "license_number")
    var licenseNumber = ""

    @Column(name = "seat")
    var seat: Int? = null

    @Column(name = "rating")
    var rating: Int? = null

    @Column(name = "engine_type")
    @Enumerated(EnumType.STRING)
    var engine: EngineType? = null

    @Column(name = "brand")
    @Enumerated(EnumType.STRING)
    var brand: VehicleBrand? = null

    @Column(name = "created_by")
    var createdBy: Int? = null

    @Column(name = "created_at")
    var createdAt: Date? = null

    @Column(name = "updated_at")
    var updatedAt: Date? = null

    @Column(name = "deleted_at")
    var deletedAt: Date? = null
}
