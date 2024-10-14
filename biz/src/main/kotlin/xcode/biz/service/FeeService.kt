package xcode.biz.service

import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.mapper.FeeMapper
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.FeeResponse

@Service
class FeeService @Autowired constructor(
    private val feeMapper: FeeMapper,
) {

    fun getFeeList(): BaseResponse<List<FeeResponse>> {
        val result = BaseResponse<List<FeeResponse>>()

        val feeList = feeMapper.getFeeList() ?: emptyList()
        val responseList = feeList.map { user ->
            FeeResponse().also { response ->
                BeanUtils.copyProperties(user, response)
            }
        }

        result.setSuccess(responseList)

        return result
    }
}
