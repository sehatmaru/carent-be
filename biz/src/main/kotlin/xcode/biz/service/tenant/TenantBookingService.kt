package xcode.biz.service.tenant

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

    fun getBookingList(request: BookingFilterRequest): BaseResponse<List<TenantBookingListResponse>> {
        val result = BaseResponse<List<TenantBookingListResponse>>()

        result.setSuccess(bookingMapper.getTenantBookingList(CurrentUser.get().companyId!!, request))

        return result
    }
}
