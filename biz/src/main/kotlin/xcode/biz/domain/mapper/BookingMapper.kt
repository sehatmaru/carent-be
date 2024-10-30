package xcode.biz.domain.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import xcode.biz.domain.model.Booking
import xcode.biz.domain.request.booking.BookingFilterRequest
import xcode.biz.domain.response.booking.TenantBookingListResponse

@Mapper
interface BookingMapper : BaseMapper<Booking> {

    @Select(
        """
        <script>
            SELECT EXTRACT(DAY FROM (book.end_date - book.start_date)) AS "duration",
            u.id AS "customer_id", u.full_name AS "customer_name", bill.invoice_number, p.name AS "product_name",
            o.status AS "order_status", o.rating,
            book.* 
            FROM t_booking book
            LEFT JOIN t_order o ON o.id = book.order_id
            LEFT JOIN t_product p ON p.id = book.product_id
            LEFT JOIN t_user u ON u.id = o.customer_id
            LEFT JOIN t_bill bill ON bill.id = book.bill_id
            WHERE p.company_id = #{companyId}
            <if test="request.id != null">
                AND book.id = #{request.id}
            </if>
            <if test="request.customerId != null">
                AND u.id = #{request.customerId}
            </if>
            <if test="request.invoiceNumber != null">
                AND bill.invoice_number = #{request.invoiceNumber}
            </if>
            <if test="request.pickupType != null">
                AND book.pickup_type = #{request.pickupType}
            </if>
            ORDER BY o.created_date DESC
        </script>
    """,
    )
    fun getTenantBookingList(
        @Param("companyId") companyId: Int,
        @Param("request") request: BookingFilterRequest,
    ): List<TenantBookingListResponse>?
}
