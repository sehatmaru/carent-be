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
import xcode.biz.domain.response.product.ProductOptionListResponse
import xcode.biz.domain.response.product.TenantProductListResponse
import xcode.biz.enums.EngineType
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand

@Mapper
interface ProductMapper : BaseMapper<Product> {

    @Insert(
        """
        INSERT INTO t_product(name, company_id, price, province_id, province_name, regency_id, regency_name, district_id, district_name, deliverable, transmission, seat, engine_type, brand, status) 
        VALUES (#{data.name}, #{data.companyId}, #{data.price}, #{data.provinceId}, #{data.provinceName}, #{data.regencyId}, #{data.regencyName}, #{data.districtId}, #{data.districtName}, #{data.deliverable}, #{data.transmission}::transmission, #{data.seat}, #{data.engineType}::engine_type, #{data.brand}::vehicle_brand, 'AVAILABLE'::product_status)
    """,
    )
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    fun insertProduct(@Param("data") data: ProductRegisterRequest)

    @Update(
        """
        UPDATE t_product SET name = #{data.name}, price = #{data.price}, deliverable = #{data.deliverable}, transmission = #{data.transmission}::transmission,
        province_id = #{data.provinceId}, province_name = #{data.provinceName}, regency_id = #{data.regencyId}, regency_name = #{data.regencyName},
        district_id = #{data.districtId}, district_name = #{data.districtName}, seat = #{data.seat}, engine_type = #{data.engineType}::engine_type,
        brand = #{data.brand}::vehicle_brand, updated_date = NOW()
        WHERE id = #{id}
    """,
    )
    fun updateProduct(@Param("id") productId: Int, @Param("data") data: ProductUpdateRequest)

    @Update(
        """
        UPDATE t_product SET deleted_date = NOW() WHERE id = #{id}
    """,
    )
    fun deleteProduct(@Param("id") productId: Int)

    @Select(
        """
        <script>
            SELECT p.*, p.brand, p.seat, p.transmission FROM t_product p
            LEFT JOIN t_booking b ON b.product_id = p.id
            WHERE (
                (b.start_date IS NULL OR b.start_date NOT BETWEEN #{request.startDate} AND #{request.endDate})
                AND
                (b.end_date IS NULL OR b.end_date NOT BETWEEN #{request.startDate} AND #{request.endDate})
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
            LIMIT #{limit}
        </script>
    """,
    )
    fun searchProductList(
        @Param("request") request: ProductSearchRequest,
        @Param("limit") limit: Int
    ): List<ProductListResponse>

    @Select(
        """
        <script>
            SELECT COUNT(p.id) FROM t_product p
            LEFT JOIN t_booking b ON b.product_id = p.id
            WHERE (
                (b.start_date IS NULL OR b.start_date NOT BETWEEN #{request.startDate} AND #{request.endDate})
                AND
                (b.end_date IS NULL OR b.end_date NOT BETWEEN #{request.startDate} AND #{request.endDate})
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
    fun searchProductListTotal(
        @Param("request") request: ProductSearchRequest
    ): Int

    @Select(
        """
        <script>
            SELECT p.* FROM t_product p
            WHERE p.company_id = #{companyId}
            AND p.deleted_date IS NULL
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
            <if test="request.provinceId != null">
                AND p.province_id = #{request.provinceId}
            </if>
            <if test="request.regencyId != null">
                AND p.regency_id = #{request.regencyId}
            </if>
            <if test="request.districtId != null">
                AND p.district_id = #{request.districtId}
            </if>
            ORDER BY p.updated_date DESC
        </script>
    """,
    )
    fun getTenantProductList(
        @Param("companyId") companyId: Int,
        @Param("request") request: ProductSearchRequest
    ): List<TenantProductListResponse>?

    @Select(
        """
        <script>
            SELECT p.id, p.name FROM t_product p
            WHERE p.company_id = #{companyId}
            AND p.deleted_date IS NULL
            <if test="name != null">
                AND p.name ILIKE CONCAT('%', #{name}, '%')
            </if>
            ORDER BY p.name
        </script>
    """,
    )
    fun getTenantProductOptionList(
        @Param("companyId") companyId: Int,
        @Param("name") name: String
    ): List<ProductOptionListResponse>?

    @Select("""SELECT p.* FROM t_product p WHERE p.id = #{id} AND p.deleted_date IS NULL""")
    fun getProductById(
        @Param("id") id: Int
    ): Product?

    @Update("""UPDATE t_product SET quantity = quantity+1, available = available+1, updated_date = NOW() WHERE id = #{id}""")
    fun increaseProductQuantity(@Param("id") productId: Int)

    @Update("""UPDATE t_product SET quantity = quantity-1, available = available-1, updated_date = NOW() WHERE id = #{id}""")
    fun decreaseProductQuantity(@Param("id") productId: Int)

    @Select(
        """
            SELECT p.* FROM t_product p
            WHERE p.deleted_date IS NULL
            ORDER BY p.rating DESC
            LIMIT 5
    """,
    )
    fun getPopularProductList(): List<ProductListResponse>

    @Select(
        """
            SELECT p.* FROM t_product p
            WHERE p.deleted_date IS NULL
            ORDER BY p.rating DESC
            LIMIT #{limit}
    """,
    )
    fun getRecommendationProductList(@Param("limit") limit: Int): List<ProductListResponse>

    @Select("""SELECT COUNT(p.id) FROM t_product p WHERE p.deleted_date IS NULL""")
    fun getTotalProduct(): Int

    @Select(
        """
        <script>
            SELECT COUNT(p.id) FROM t_product p WHERE transmission = #{transmission}::transmission AND p.deleted_date IS NULL
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
            <if test="request.provinceId != null">
                AND p.province_id = #{request.provinceId}
            </if>
            <if test="request.regencyId != null">
                AND p.regency_id = #{request.regencyId}
            </if>
            <if test="request.districtId != null">
                AND p.district_id = #{request.districtId}
            </if>
        </script>
        """
    )
    fun getTotalProductTransmission(
        @Param("request") request: ProductSearchRequest,
        @Param("transmission") transmission: Transmission
    ): Int

    @Select(
        """
        <script>
            SELECT COUNT(p.id) FROM t_product p WHERE deliverable = #{deliverable} AND p.deleted_date IS NULL
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
            <if test="request.provinceId != null">
                AND p.province_id = #{request.provinceId}
            </if>
            <if test="request.regencyId != null">
                AND p.regency_id = #{request.regencyId}
            </if>
            <if test="request.districtId != null">
                AND p.district_id = #{request.districtId}
            </if>
        </script>"""
    )
    fun getTotalProductDeliverable(
        @Param("request") request: ProductSearchRequest,
        @Param("deliverable") deliverable: Boolean
    ): Int

    @Select(
        """
        <script>
            SELECT COUNT(p.id) FROM t_product p WHERE brand = #{brand}::vehicle_brand AND p.deleted_date IS NULL
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
            <if test="request.provinceId != null">
                AND p.province_id = #{request.provinceId}
            </if>
            <if test="request.regencyId != null">
                AND p.regency_id = #{request.regencyId}
            </if>
            <if test="request.districtId != null">
                AND p.district_id = #{request.districtId}
            </if>
        </script>
        """
    )
    fun getTotalProductBrand(@Param("request") request: ProductSearchRequest, @Param("brand") brand: VehicleBrand): Int

    @Select(
        """
        <script>
            SELECT COUNT(p.id) FROM t_product p WHERE engine_type = #{engine}::engine_type AND p.deleted_date IS NULL
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
            <if test="request.provinceId != null">
                AND p.province_id = #{request.provinceId}
            </if>
            <if test="request.regencyId != null">
                AND p.regency_id = #{request.regencyId}
            </if>
            <if test="request.districtId != null">
                AND p.district_id = #{request.districtId}
            </if>
        </script>
        """
    )
    fun getTotalProductEngine(@Param("request") request: ProductSearchRequest, @Param("engine") engine: EngineType): Int

    @Select(
        """
        <script>
            SELECT COUNT(p.id) FROM t_product p WHERE seat = #{seat} AND p.deleted_date IS NULL
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
            <if test="request.provinceId != null">
                AND p.province_id = #{request.provinceId}
            </if>
            <if test="request.regencyId != null">
                AND p.regency_id = #{request.regencyId}
            </if>
            <if test="request.districtId != null">
                AND p.district_id = #{request.districtId}
            </if>
        </script>
        """
    )
    fun getTotalProductSeat(@Param("request") request: ProductSearchRequest, @Param("seat") seat: Int): Int
}
