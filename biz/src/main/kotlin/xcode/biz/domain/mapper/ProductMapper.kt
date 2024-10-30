package xcode.biz.domain.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import xcode.biz.domain.model.Product
import xcode.biz.domain.request.product.ProductSearchRequest
import xcode.biz.domain.response.product.ProductListResponse
import xcode.biz.domain.response.product.TenantProductListResponse

@Mapper
interface ProductMapper : BaseMapper<Product> {

    @Insert(
        """
        INSERT INTO t_product (name, price, status, vehicle_id, district_id, district_name, province_id, province_name, regency_id, regency_name, deliverable)
        VALUES (#{data.name}, #{data.price}, #{data.status}, #{data.vehicleId}, #{data.districtId}, #{data.districtName}, #{data.provinceId}, #{data.provinceName}, #{data.regencyId}, #{data.regencyName}, #{data.deliverable})
    """,
    )
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    fun insertProduct(@Param("data") data: Product)

    @Select(
        """
        <script>
            SELECT p.*, p.brand, p.seat, p.transmission FROM t_product p
            LEFT JOIN t_booking b ON b.product_id = p.id
            WHERE (
                (b.start_date IS NULL OR b.start_date NOT BETWEEN #{request.startAt} AND #{request.endAt})
                AND
                (b.end_date IS NULL OR b.end_date NOT BETWEEN #{request.startAt} AND #{request.endAt})
            )
            <if test="request.priceStart != null and request.priceEnd != null">
                AND p.price BETWEEN #{request.priceStart} AND #{request.priceEnd}
            </if>
            <if test="request.provinceId != null">
                AND p.province_id = #{request.provinceId}
            </if>
            <if test="request.regencyId != null">
                AND p.regency_id = #{request.regencyId}
            </if>
            <if test="request.districtId != null">
                AND p.district_id = #{request.districtId}
            </if>
            <if test="request.deliverable != null">
                AND p.deliverable = #{request.deliverable}
            </if>
            <if test="request.transmission != null">
                AND p.transmission = #{request.transmission}
            </if>
            <if test="request.engineType != null">
                AND p.engine_type = #{request.engineType}
            </if>
            <if test="request.brand != null">
                AND p.brand = #{request.brand}
            </if>
        </script>
    """,
    )
    fun searchProductList(@Param("request") request: ProductSearchRequest): List<ProductListResponse>?

    @Select(
        """
        <script>
            SELECT p.id, p.name, p.price, p.deliverable, p.status, p.brand, p.seat, p.transmission, p.seat FROM t_product p
            WHERE p.company_id = #{companyId}
            <if test="request.id != null">
                AND p.id = #{request.id}
            </if>
            <if test="request.name != null">
                AND p.name LIKE CONCAT('%', #{request.name}, '%')
            </if>
            <if test="request.deliverable != null">
                AND p.deliverable = #{request.deliverable}
            </if>
            <if test="request.transmission != null">
                AND p.transmission = #{request.transmission}
            </if>
            <if test="request.engineType != null">
                AND p.engine_type = #{request.engineType}
            </if>
            <if test="request.brand != null">
                AND p.brand = #{request.brand}
            </if>
            <if test="request.status != null">
                AND p.status = #{request.status}
            </if>
        </script>
    """,
    )
    fun getTenantProductList(@Param("companyId") companyId: Int, @Param("request") request: ProductSearchRequest): List<TenantProductListResponse>?
}
