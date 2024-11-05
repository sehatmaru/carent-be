package xcode.biz.domain.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import xcode.biz.domain.model.Bill
import xcode.biz.domain.request.bill.BillFilterRequest
import xcode.biz.domain.response.bill.BillListResponse

@Mapper
interface BillMapper : BaseMapper<Bill> {

    @Select(
        """
        SELECT SUM(b.total_paid) FROM t_bill b
        JOIN t_order o ON o.id = b.order_id
        JOIN t_product p ON p.id = o.product_id
        WHERE p.company_id = #{companyId}
    """,
    )
    fun getTotalIncome(@Param("companyId") companyId: Int): Double?

    @Select(
        """
        SELECT SUM(b.total_paid) FROM t_bill b
        JOIN t_order o ON o.id = b.order_id
        JOIN t_product p ON p.id = o.product_id
        WHERE p.company_id = #{companyId}
        AND b.payment_status = 'PAID'
        AND EXTRACT(MONTH FROM o.created_date) = EXTRACT(MONTH FROM CURRENT_DATE)
        AND EXTRACT(YEAR FROM o.created_date) = EXTRACT(YEAR FROM CURRENT_DATE)
    """,
    )
    fun getCurrentMonthTotalIncome(@Param("companyId") companyId: Int): Double?

    @Select(
        """
        SELECT SUM(b.total_paid) FROM t_bill b
        JOIN t_order o ON o.id = b.order_id
        JOIN t_product p ON p.id = o.product_id
        WHERE p.company_id = #{companyId}
        AND b.payment_status = 'PAID'
        AND EXTRACT(MONTH FROM o.created_date) = #{month}
        AND EXTRACT(YEAR FROM o.created_date) = #{year}
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
        WHERE p.company_id = #{companyId}
    """,
    )
    fun getTotalApplicationFee(@Param("companyId") companyId: Int): Double?

    @Select(
        """
        SELECT SUM(b.application_fee) FROM t_bill b
        JOIN t_order o ON o.id = b.order_id
        JOIN t_product p ON p.id = o.product_id
        WHERE p.company_id = #{companyId}
        AND b.payment_status = 'PAID'
        AND EXTRACT(MONTH FROM o.created_date) = EXTRACT(MONTH FROM CURRENT_DATE)
        AND EXTRACT(YEAR FROM o.created_date) = EXTRACT(YEAR FROM CURRENT_DATE)
    """,
    )
    fun getCurrentMonthTotalApplicationFee(@Param("companyId") companyId: Int): Double?

    @Select(
        """
        SELECT SUM(b.application_fee) FROM t_bill b
        JOIN t_order o ON o.id = b.order_id
        JOIN t_product p ON p.id = o.product_id
        WHERE p.company_id = #{companyId}
        AND b.payment_status = 'PAID'
        AND EXTRACT(MONTH FROM o.created_date) = #{month}
        AND EXTRACT(YEAR FROM o.created_date) = #{year}
    """,
    )
    fun getTotalApplicationFeeHistory(
        @Param("companyId") companyId: Int,
        @Param("month") month: Int,
        @Param("year") year: Int,
    ): Double?

    @Select(
        """
        <script>
            SELECT u.id AS "customer_id", u.full_name AS "customer_name", o.status AS "order_status", o.rating,
            bill.* 
            FROM t_bill bill
            LEFT JOIN t_order o ON o.id = bill.order_id
            LEFT JOIN t_product p ON p.id = o.product_id
            LEFT JOIN t_user u ON u.id = o.customer_id
            WHERE p.company_id = #{companyId}
            <if test="request.id != null">
                AND bill.id = #{request.id}
            </if>
            <if test="request.customerId != null">
                AND u.id = #{request.customerId}
            </if>
            <if test="request.invoiceNumber != null">
                AND bill.invoice_number = #{request.invoiceNumber}
            </if>
            <if test="request.orderId != null">
                AND bill.order_id = #{request.orderId}
            </if>
            <if test="request.paymentType != null">
                AND bill.payment_type = #{request.paymentType}::payment_type
            </if>
            <if test="request.orderStatus != null">
                AND o.status = #{request.orderStatus}::order_status
            </if>
            <if test="request.paymentStatus != null">
                AND bill.payment_status = #{request.paymentStatus}::payment_status
            </if>
            ORDER BY bill.created_date DESC
        </script>
    """,
    )
    fun getTenantBillList(
        @Param("companyId") companyId: Int,
        @Param("request") request: BillFilterRequest,
    ): List<BillListResponse>?
}
