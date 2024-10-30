package xcode.biz.service.tenant

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.dto.CurrentUser
import xcode.biz.domain.mapper.BillMapper
import xcode.biz.domain.request.bill.BillFilterRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.bill.BillListResponse
import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode.UNAUTHORIZED

@Service
class TenantBillService @Autowired constructor(
    private val billMapper: BillMapper,
) {

    fun getBillList(request: BillFilterRequest): BaseResponse<List<BillListResponse>> {
        val result = BaseResponse<List<BillListResponse>>()

        checkPermission()

        result.setSuccess(billMapper.getTenantBillList(CurrentUser.get().companyId!!, request))

        return result
    }

    fun checkPermission() {
        if (!CurrentUser.get().isManager()) {
            throw AppException(UNAUTHORIZED)
        }
    }
}
