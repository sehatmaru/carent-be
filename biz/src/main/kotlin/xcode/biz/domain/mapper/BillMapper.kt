package xcode.biz.domain.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import xcode.biz.domain.model.Bill

@Mapper
interface BillMapper : BaseMapper<Bill> {

    @Select(
        """
        SELECT SUM(b.total_paid) FROM t_bill b
        JOIN t_order o ON o.id = b.order_id
        JOIN t_product p ON p.id = o.product_id
        JOIN t_vehicle v ON v.id = p.vehicle_id
        WHERE v.company_id = #{companyId}
    """,
    )
    fun getTotalIncome(@Param("companyId") companyId: Int): Double?

    @Select(
        """
        SELECT SUM(b.total_paid) FROM t_bill b
        JOIN t_order o ON o.id = b.order_id
        JOIN t_product p ON p.id = o.product_id
        JOIN t_vehicle v ON v.id = p.vehicle_id
        WHERE v.company_id = #{companyId}
        AND EXTRACT(MONTH FROM o.created_at) = EXTRACT(MONTH FROM CURRENT_DATE)
        AND EXTRACT(YEAR FROM o.created_at) = EXTRACT(YEAR FROM CURRENT_DATE)
    """,
    )
    fun getCurrentMonthTotalIncome(@Param("companyId") companyId: Int): Double?

    @Select(
        """
        SELECT SUM(b.total_paid) FROM t_bill b
        JOIN t_order o ON o.id = b.order_id
        JOIN t_product p ON p.id = o.product_id
        JOIN t_vehicle v ON v.id = p.vehicle_id
        WHERE v.company_id = #{companyId}
        AND EXTRACT(MONTH FROM o.created_at) = #{month}
        AND EXTRACT(YEAR FROM o.created_at) = #{year}
    """,
    )
    fun getTotalIncomeHistory(
        @Param("companyId") companyId: Int,
        @Param("month") month: Int,
        @Param("year") year: Int,
    ): Double?

    @Select(
        """
        SELECT SUM(b.application_fee) FROM t_bill b
        JOIN t_order o ON o.id = b.order_id
        JOIN t_product p ON p.id = o.product_id
        JOIN t_vehicle v ON v.id = p.vehicle_id
        WHERE v.company_id = #{companyId}
    """,
    )
    fun getTotalApplicationFee(@Param("companyId") companyId: Int): Double?

    @Select(
        """
        SELECT SUM(b.application_fee) FROM t_bill b
        JOIN t_order o ON o.id = b.order_id
        JOIN t_product p ON p.id = o.product_id
        JOIN t_vehicle v ON v.id = p.vehicle_id
        WHERE v.company_id = #{companyId}
        AND EXTRACT(MONTH FROM o.created_at) = EXTRACT(MONTH FROM CURRENT_DATE)
        AND EXTRACT(YEAR FROM o.created_at) = EXTRACT(YEAR FROM CURRENT_DATE)
    """,
    )
    fun getCurrentMonthTotalApplicationFee(@Param("companyId") companyId: Int): Double?

    @Select(
        """
        SELECT SUM(b.application_fee) FROM t_bill b
        JOIN t_order o ON o.id = b.order_id
        JOIN t_product p ON p.id = o.product_id
        JOIN t_vehicle v ON v.id = p.vehicle_id
        WHERE v.company_id = #{companyId}
        AND EXTRACT(MONTH FROM o.created_at) = #{month}
        AND EXTRACT(YEAR FROM o.created_at) = #{year}
    """,
    )
    fun getTotalApplicationFeeHistory(
        @Param("companyId") companyId: Int,
        @Param("month") month: Int,
        @Param("year") year: Int,
    ): Double?
}
