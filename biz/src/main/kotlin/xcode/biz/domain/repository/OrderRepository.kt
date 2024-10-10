package xcode.biz.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import xcode.biz.domain.model.Order

@Repository
interface OrderRepository : JpaRepository<Order?, String?> {

    @Query(
        value = "SELECT COUNT(o.*) FROM t_order o " +
            "JOIN t_product p ON p.id = o.product_id " +
            "JOIN t_vehicle v ON v.id = p.vehicle_id " +
            "WHERE v.company_id = :companyId",
        nativeQuery = true,
    )
    fun countOrder(@Param("companyId") companyId: Int): Int?

    @Query(
        value = "SELECT COUNT(DISTINCT o.customer_id) FROM t_order o " +
            "JOIN t_product p ON p.id = o.product_id " +
            "JOIN t_vehicle v ON v.id = p.vehicle_id " +
            "WHERE v.company_id = :companyId",
        nativeQuery = true,
    )
    fun countUniqueCustomer(@Param("companyId") companyId: Int): Int?
}
