package xcode.tenant.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.request.booking.BookingFilterRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.booking.TenantBookingListResponse
import xcode.biz.service.tenant.TenantBookingService

@RestController
@RequestMapping(value = ["booking"])
class BookingApi @Autowired constructor(
    private val bookingService: TenantBookingService,
) {

    @PostMapping("/list")
    fun getVehicleList(@RequestBody @Validated request: BookingFilterRequest): BaseResponse<List<TenantBookingListResponse>> {
        return bookingService.getBookingList(request)
    }
}
