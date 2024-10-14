package xcode.biz.domain.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import xcode.biz.domain.model.Vehicle

@Mapper
interface VehicleMapper : BaseMapper<Vehicle> {

    @Insert(
        """
        INSERT INTO t_vehicle (brand, company_id, created_by, engine_type, license_number, name, rating, transmission, vehicle_type, year, seat)
        VALUES (#{data.brand}, #{data.companyId}, #{data.createdBy}, #{data.engineType}, #{data.licenseNumber}, #{data.name}, #{data.rating}, #{data.transmission}, #{data.vehicleType}, #{data.year}, #{data.seat})
    """,
    )
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    fun insertVehicle(@Param("data") data: Vehicle)

    @Select("""SELECT * FROM t_vehicle WHERE company_id = #{companyId}""")
    fun getVehicleList(@Param("companyId") companyId: Int): List<Vehicle>?

    @Select("""SELECT * FROM t_vehicle WHERE id = #{vehicleId}""")
    fun getVehicle(@Param("vehicleId") vehicleId: Int): Vehicle?
}
