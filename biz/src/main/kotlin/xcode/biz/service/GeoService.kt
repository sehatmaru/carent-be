package xcode.biz.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.springframework.stereotype.Service
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.GeoResponse
import xcode.biz.exception.AppException

@Service
class GeoService {

    private var baseUrl = "https://emsifa.github.io/api-wilayah-indonesia/api"

    fun getProvinces(name: String): BaseResponse<List<GeoResponse>> {
        val result = BaseResponse<List<GeoResponse>>()
        val url = "$baseUrl/provinces.json"

        result.setSuccess(fetchData(url, name))

        return result
    }

    fun getRegencies(provinceId: Int, name: String): BaseResponse<List<GeoResponse>> {
        val result = BaseResponse<List<GeoResponse>>()
        val url = "$baseUrl/regencies/$provinceId.json"

        result.setSuccess(fetchData(url, name))

        return result
    }

    fun getDistricts(regencyId: Int, name: String): BaseResponse<List<GeoResponse>> {
        val result = BaseResponse<List<GeoResponse>>()
        val url = "$baseUrl/districts/$regencyId.json"

        result.setSuccess(fetchData(url, name))

        return result
    }

    fun fetchData(url: String, name: String): List<GeoResponse> {
        val httpClient: CloseableHttpClient = HttpClients.createDefault()
        val objectMapper = jacksonObjectMapper()

        return try {
            val httpGet = HttpGet(url)
            val response = httpClient.execute(httpGet)

            response.entity?.let { entity ->
                val responseBody = EntityUtils.toString(entity)
                val result: List<GeoResponse> = objectMapper.readValue(responseBody)

                if (name.isNotEmpty()) {
                    result.filter { it.name.contains(name, ignoreCase = true) }
                } else {
                    result
                }
            } ?: emptyList()
        } catch (e: Exception) {
            throw AppException(e.message)
        } finally {
            httpClient.close()
        }
    }
}
