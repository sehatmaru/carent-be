package xcode.biz.domain.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import xcode.biz.domain.model.Order

@Mapper
interface OrderMapper : BaseMapper<Order> {

    @Select(
        """
        SELECT COUNT(o) FROM t_order o
        JOIN t_product p ON p.id = o.product_id
        JOIN t_vehicle v ON v.id = p.vehicle_id
        WHERE v.company_id = #{companyId}
    """,
    )
    fun countOrder(@Param("companyId") companyId: Int): Int?

    @Select(
        """
        SELECT COUNT(DISTINCT o.customer_id) FROM t_order o
        JOIN t_product p ON p.id = o.product_id
        JOIN t_vehicle v ON v.id = p.vehicle_id
        WHERE v.company_id = #{companyId}
    """,
    )
    fun countUniqueCustomer(@Param("companyId") companyId: Int): Int?
}
