package xcode.biz.domain.request.auth

import xcode.biz.enums.CredentialType

class CredentialRegisterRequest {
    var credentialNo = ""
    var credentialType: CredentialType? = null
}
