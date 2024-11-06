package xcode.biz.service.tenant

import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.dto.CurrentUser
import xcode.biz.domain.mapper.BookingMapper
import xcode.biz.domain.request.booking.BookingFilterRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.booking.TenantBookingListResponse

@Service
class TenantBookingService @Autowired constructor(
    private val bookingMapper: BookingMapper,
) {

    fun getBookingList(
        request: BookingFilterRequest,
        pageNum: Int,
        pageSize: Int
    ): BaseResponse<PageInfo<TenantBookingListResponse>> {
        val result = BaseResponse<PageInfo<TenantBookingListResponse>>()

        result.setSuccess(PageInfo(bookingMapper.getTenantBookingList(CurrentUser.get().companyId!!, request)))

        return result
    }
}
