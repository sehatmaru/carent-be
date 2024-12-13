package xcode.biz.domain.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import xcode.biz.domain.model.Order
import xcode.biz.domain.request.customer.CustomerFilterRequest
import xcode.biz.domain.response.customer.TenantCustomerResponse
import xcode.biz.domain.response.order.OrderHistoryResponse

@Mapper
interface OrderMapper : BaseMapper<Order> {

    @Select(
        """
        SELECT COUNT(o) FROM t_order o
        JOIN t_product p ON p.id = o.product_id
        WHERE p.company_id = #{companyId}
    """,
    )
    fun countOrder(@Param("companyId") companyId: Int): Int?

    @Select(
        """
        SELECT COUNT(o) FROM t_order o
        JOIN t_product p ON p.id = o.product_id
        WHERE p.company_id = #{companyId}
        AND EXTRACT(MONTH FROM o.created_date) = EXTRACT(MONTH FROM CURRENT_DATE)
        AND EXTRACT(YEAR FROM o.created_date) = EXTRACT(YEAR FROM CURRENT_DATE)
    """,
    )
    fun countCurrentMonthOrder(@Param("companyId") companyId: Int): Int

    @Select(
        """
        SELECT COUNT(o) FROM t_order o
        JOIN t_product p ON p.id = o.product_id
        WHERE p.company_id = #{companyId}
        AND EXTRACT(MONTH FROM o.created_date) = #{month}
        AND EXTRACT(YEAR FROM o.created_date) = #{year}
    """,
    )
    fun countOrderHistory(
        @Param("companyId") companyId: Int,
        @Param("month") month: Int,
        @Param("year") year: Int,
    ): Int

    @Select(
        """
        SELECT COUNT(DISTINCT o.customer_id) FROM t_order o
        JOIN t_product p ON p.id = o.product_id
        WHERE p.company_id = #{companyId}
    """,
    )
    fun countUniqueCustomer(@Param("companyId") companyId: Int): Int?

    @Select(
        """
        SELECT COUNT(DISTINCT o.customer_id) FROM t_order o
        JOIN t_product p ON p.id = o.product_id
        WHERE p.company_id = #{companyId}
        AND EXTRACT(MONTH FROM o.created_date) = EXTRACT(MONTH FROM CURRENT_DATE)
        AND EXTRACT(YEAR FROM o.created_date) = EXTRACT(YEAR FROM CURRENT_DATE)
    """,
    )
    fun countCurrentMonthUniqueCustomer(@Param("companyId") companyId: Int): Int

    @Select(
        """
        SELECT COUNT(DISTINCT o.customer_id) FROM t_order o
        JOIN t_product p ON p.id = o.product_id
        WHERE p.company_id = #{companyId}
        AND EXTRACT(MONTH FROM o.created_date) = #{month}
        AND EXTRACT(YEAR FROM o.created_date) = #{year}
    """,
    )
    fun countUniqueCustomerHistory(
        @Param("companyId") companyId: Int,
        @Param("month") month: Int,
        @Param("year") year: Int,
    ): Int

    @Select(
        """
        SELECT o.*, o.status AS "order_status", o.created_date AS "ordered_date", p.name AS "product_name", u.full_name AS "customer_name", p.brand,
        p.transmission, bill.total_payment, bill.payment_status AS "payment_status", book.start_date, book.end_date,
        EXTRACT(DAY FROM (book.end_date - book.start_date)) AS "duration"
        FROM t_order o
        JOIN t_product p ON p.id = o.product_id
        JOIN t_user u ON u.id = o.customer_id
        LEFT JOIN t_bill bill ON bill.order_id = o.id
        LEFT JOIN t_booking book ON book.order_id = o.id
        WHERE p.company_id = #{companyId}
        ORDER BY o.created_date DESC
        LIMIT 5
    """,
    )
    fun getDashboardOrderHistory(@Param("companyId") companyId: Int): List<OrderHistoryResponse>

    @Select(
        """
        <script>
            SELECT u.full_name as "name", u.username, u.mobile, 
                (SELECT created_date FROM t_order WHERE customer_id = u.id ORDER BY created_date DESC LIMIT 1) AS "last_order",
                (SELECT COUNT(id) FROM t_order WHERE customer_id = u.id AND status IN ('IN_PROGRESS', 'COMPLETED')) AS "total_order"
            FROM t_user u
            WHERE u.id IN (
                SELECT DISTINCT o.customer_id
                FROM t_order o
                JOIN t_product p ON p.id = o.product_id
                WHERE p.company_id = #{companyId}
                AND o.status IN ('IN_PROGRESS', 'COMPLETED')
            )
            <if test="request.name != null">
                AND u.full_name ILIKE CONCAT('%', #{request.name}, '%')
            </if>
            <if test="request.mobile != null">
                AND u.mobile ILIKE CONCAT('%', #{request.mobile}, '%')
            </if>
            <if test="request.username != null">
                AND u.username ILIKE CONCAT('%', #{request.username}, '%')
            </if>
        </script>
    """,
    )
    fun getTenantCustomerList(
        @Param("companyId") companyId: Int,
        @Param("request") request: CustomerFilterRequest,
    ): List<TenantCustomerResponse>
}
