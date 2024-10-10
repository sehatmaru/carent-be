package xcode.biz.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import xcode.biz.domain.model.Bill

@Repository
interface BillRepository : JpaRepository<Bill?, String?> {

    @Query(
        value = "SELECT SUM(b.total_paid) FROM t_bill b " +
            "JOIN t_order o ON o.id = b.order_id " +
            "JOIN t_product p ON p.id = o.product_id " +
            "JOIN t_vehicle v ON v.id = p.vehicle_id " +
            "WHERE v.company_id = :companyId",
        nativeQuery = true,
    )
    fun getTotalIncome(@Param("companyId") companyId: Int): Double?

    @Query(
        value = "SELECT SUM(b.application_fee) FROM t_bill b " +
            "JOIN t_order o ON o.id = b.order_id " +
            "JOIN t_product p ON p.id = o.product_id " +
            "JOIN t_vehicle v ON v.id = p.vehicle_id " +
            "WHERE v.company_id = :companyId",
        nativeQuery = true,
    )
    fun getTotalApplicationFee(@Param("companyId") companyId: Int): Double?
}
