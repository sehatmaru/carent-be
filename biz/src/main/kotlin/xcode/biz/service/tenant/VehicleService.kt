package xcode.biz.service.tenant

import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.dto.CurrentUser
import xcode.biz.domain.mapper.VehicleMapper
import xcode.biz.domain.model.Vehicle
import xcode.biz.domain.request.vehicle.VehicleRegisterRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.auth.LoginResponse
import xcode.biz.domain.response.vehicle.VehicleResponse
import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode.UNAUTHORIZED
import java.util.Date

@Service
class VehicleService @Autowired constructor(
    private val vehicleMapper: VehicleMapper,
) {

    fun registerVehicle(request: VehicleRegisterRequest): BaseResponse<LoginResponse> {
        checkPermission()
        request.validate()

        val vehicle = Vehicle()
        BeanUtils.copyProperties(request, vehicle)
        vehicle.companyId = CurrentUser.get().companyId
        vehicle.createdBy = CurrentUser.get().id
        vehicle.createdAt = Date()

        vehicleMapper.insertVehicle(vehicle)

        return BaseResponse()
    }

    fun getVehicleList(): BaseResponse<List<VehicleResponse>> {
        val result = BaseResponse<List<VehicleResponse>>()

        checkPermission()

        val vehicleList = vehicleMapper.getVehicleList(CurrentUser.get().companyId!!) ?: emptyList()
        val responseList = vehicleList.map { vehicle ->
            VehicleResponse().also { response ->
                BeanUtils.copyProperties(vehicle, response)
            }
        }

        result.setSuccess(responseList)

        return result
    }

    fun checkPermission() {
        if (!CurrentUser.get().isManager()) {
            throw AppException(UNAUTHORIZED)
        }
    }
}
