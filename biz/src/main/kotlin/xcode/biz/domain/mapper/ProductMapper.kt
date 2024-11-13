package xcode.biz.domain.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import xcode.biz.domain.model.Product
import xcode.biz.domain.request.product.ProductRegisterRequest
import xcode.biz.domain.request.product.ProductSearchRequest
import xcode.biz.domain.request.product.ProductUpdateRequest
import xcode.biz.domain.response.product.ProductListResponse
import xcode.biz.domain.response.product.TenantProductListResponse

@Mapper
interface ProductMapper : BaseMapper<Product> {

    @Insert(
        """
        INSERT INTO t_product(company_id, name, price, quantity, available, province_id, province_name, regency_id, regency_name, district_id, district_name, deliverable, transmission, seat, engine_type, brand, status) 
        VALUES (#{data.companyId}, #{data.name}, #{data.price}, #{data.quantity}, #{data.available}, #{data.provinceId}, #{data.provinceName}, #{data.regencyId}, #{data.regencyName}, #{data.districtId}, #{data.districtName}, #{data.deliverable}, #{data.transmission}::transmission, #{data.seat}, #{data.engineType}::engine_type, #{data.brand}::vehicle_brand, 'AVAILABLE'::product_status)
    """,
    )
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    fun insertProduct(@Param("data") data: ProductRegisterRequest)

    @Update(
        """
        UPDATE t_product SET name = #{data.name}, price = #{data.price}, quantity = #{data.quantity}, available = #{data.available}, deliverable = #{data.deliverable}, transmission = #{data.transmission}::transmission, seat = #{data.seat}, engine_type = #{data.engineType}::engine_type, brand = #{data.brand}::vehicle_brand
        WHERE id = #{id}
    """,
    )
    fun updateProduct(@Param("id") productId: Int, @Param("data") data: ProductUpdateRequest)

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
                AND p.transmission = #{request.transmission}::transmission
            </if>
            <if test="request.engineType != null">
                AND p.engine_type = #{request.engineType}::engine_type
            </if>
            <if test="request.brand != null">
                AND p.brand = #{request.brand}::vehicle_brand
            </if>
        </script>
    """,
    )
    fun searchProductList(@Param("request") request: ProductSearchRequest): List<ProductListResponse>

    @Select(
        """
        <script>
            SELECT p.id, p.name, p.price, p.deliverable, p.status, p.quantity, p.available, p.brand, p.seat, p.transmission, p.engine_type, p.seat FROM t_product p
            WHERE p.company_id = #{companyId}
            <if test="request.id != null">
                AND p.id = #{request.id}
            </if>
            <if test="request.name != null">
                AND p.name ILIKE CONCAT('%', #{request.name}, '%')
            </if>
            <if test="request.deliverable != null">
                AND p.deliverable = #{request.deliverable}
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
            <if test="request.status != null">
                AND p.status = #{request.status}::product_status
            </if>
            ORDER BY p.created_date DESC
        </script>
    """,
    )
    fun getTenantProductList(
        @Param("companyId") companyId: Int,
        @Param("request") request: ProductSearchRequest
    ): List<TenantProductListResponse>?
}
