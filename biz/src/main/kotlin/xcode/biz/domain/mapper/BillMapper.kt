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
        SELECT SUM(b.application_fee) FROM t_bill b
        JOIN t_order o ON o.id = b.order_id
        JOIN t_product p ON p.id = o.product_id
        JOIN t_vehicle v ON v.id = p.vehicle_id
        WHERE v.company_id = #{companyId}
    """,
    )
    fun getTotalApplicationFee(@Param("companyId") companyId: Int): Double?
}
