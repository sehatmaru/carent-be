package xcode.biz.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import xcode.biz.domain.model.Product

@Repository
interface ProductRepository : JpaRepository<Product?, String?> {

    @Query(
        value = "SELECT p.* FROM t_product p " +
                "JOIN t_vehicle v ON v.id = p.vehicle_id " +
                "WHERE v.company_id = :companyId",
        nativeQuery = true,
    )
    fun getProductList(@Param("companyId") companyId: Int): List<Product>?
}
