package xcode.admin.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.FeeResponse
import xcode.biz.service.tenant.FeeService

@RestController
@RequestMapping(value = ["fee"])
class FeeApi @Autowired constructor(
    private val feeService: FeeService,
) {

    @GetMapping("/list")
    fun getFeeList(): BaseResponse<List<FeeResponse>> {
        return feeService.getFeeList()
    }
}
