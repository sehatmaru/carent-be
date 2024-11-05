package xcode.biz.domain.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import xcode.biz.domain.model.Vehicle
import xcode.biz.domain.request.vehicle.VehicleFilterRequest
import xcode.biz.domain.response.vehicle.VehicleResponse

@Mapper
interface VehicleMapper : BaseMapper<Vehicle> {

    @Insert(
        """
        INSERT INTO t_vehicle (status, product_id, created_by, license_number, name, rating, year)
        VALUES (#{data.status}, #{data.productId}, #{data.createdBy}, #{data.licenseNumber}, #{data.name}, #{data.rating}, #{data.year})
    """,
    )
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    fun insertVehicle(@Param("data") data: Vehicle)

    @Select(
        """
        <script>
            SELECT v.*, p.id AS "product_id", p.name AS "product_name", p.brand, p.engine_type, p.transmission FROM t_product p
            JOIN t_vehicle v ON v.product_id = p.id
            WHERE p.company_id = #{companyId}
            <if test="request.id != null">
                AND v.id = #{request.id}
            </if>
            <if test="request.name != null">
                AND v.name ILIKE CONCAT('%', #{request.name}, '%')
            </if>
            <if test="request.productId != null">
                AND p.id = #{request.productId}
            </if>
            <if test="request.year != null">
                AND v.year = #{request.year}
            </if>
            <if test="request.licenseNumber != null">
                AND v.license_number ILIKE CONCAT('%', #{request.licenseNumber}, '%')
            </if>
            <if test="request.transmission != null">
                AND p.transmission = #{request.transmission}::transmission
            </if>
            <if test="request.engineType != null">
                AND p.engine_type = #{request.engineType}::engine_type
            </if>
            <if test="request.brand != null">
                AND p.brand = #{request.brand}::vehicle_brand
            </if>
            ORDER BY v.created_date DESC
        </script>
    """,
    )
    fun getVehicleList(
        @Param("companyId") companyId: Int,
        @Param("request") request: VehicleFilterRequest,
    ): List<VehicleResponse>?

    @Select("""SELECT * FROM t_vehicle WHERE id = #{vehicleId}""")
    fun getVehicle(@Param("vehicleId") vehicleId: Int): Vehicle?
}
