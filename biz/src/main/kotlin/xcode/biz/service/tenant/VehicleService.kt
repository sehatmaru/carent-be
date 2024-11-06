package xcode.biz.service.tenant

import com.github.pagehelper.PageInfo
import java.util.Date
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.dto.CurrentUser
import xcode.biz.domain.mapper.VehicleMapper
import xcode.biz.domain.model.Vehicle
import xcode.biz.domain.request.vehicle.VehicleFilterRequest
import xcode.biz.domain.request.vehicle.VehicleRegisterRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.auth.LoginResponse
import xcode.biz.domain.response.vehicle.VehicleResponse
import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode.UNAUTHORIZED

@Service
class VehicleService @Autowired constructor(
    private val vehicleMapper: VehicleMapper,
) {

    fun registerVehicle(request: VehicleRegisterRequest): BaseResponse<LoginResponse> {
        checkPermission()
        request.validate()

        val vehicle = Vehicle()
        BeanUtils.copyProperties(request, vehicle)
//        vehicle.companyId = CurrentUser.get().companyId
        vehicle.createdBy = CurrentUser.get().id
        vehicle.createdDate = Date()

        vehicleMapper.insertVehicle(vehicle)

        return BaseResponse()
    }

    fun getVehicleList(
        request: VehicleFilterRequest,
        pageNum: Int,
        pageSize: Int
    ): BaseResponse<PageInfo<VehicleResponse>> {
        val result = BaseResponse<PageInfo<VehicleResponse>>()

        checkPermission()

        result.setSuccess(PageInfo(vehicleMapper.getVehicleList(CurrentUser.get().companyId!!, request)))

        return result
    }

    fun checkPermission() {
        if (!CurrentUser.get().isManager()) {
            throw AppException(UNAUTHORIZED)
        }
    }
}
