package xcode.biz.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import xcode.biz.domain.model.Vehicle

@Repository
interface VehicleRepository : JpaRepository<Vehicle?, String?> {

    @Query(
        value = "SELECT * FROM t_vehicle WHERE company_id = :companyId",
        nativeQuery = true,
    )
    fun getVehicleList(@Param("companyId") companyId: Int): List<Vehicle>?

    @Query(
        value = "SELECT * FROM t_vehicle WHERE id = :vehicleId",
        nativeQuery = true,
    )
    fun getVehicle(@Param("vehicleId") vehicleId: Int): Vehicle?
}
