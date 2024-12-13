package xcode.tenant.api

import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.request.bill.BillFilterRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.bill.BillListResponse
import xcode.biz.service.tenant.TenantBillService

@RestController
@RequestMapping(value = ["billing"])
class BillApi @Autowired constructor(
    private val billService: TenantBillService,
) {

    @PostMapping("/list")
    fun getVehicleList(
        @RequestBody @Validated request: BillFilterRequest,
        @RequestParam("pageNum") pageNumber: Int,
        @RequestParam("pageSize") pageSize: Int
    ): BaseResponse<PageInfo<BillListResponse>> {
        return billService.getBillList(request, pageNumber, pageSize)
    }
}
