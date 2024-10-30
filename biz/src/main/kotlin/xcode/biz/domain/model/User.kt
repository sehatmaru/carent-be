package xcode.biz.domain.model

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import xcode.biz.enums.CredentialType
import xcode.biz.enums.UserRole
import java.util.Date

@Data
@TableName("t_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
class User {

    @TableId(type = IdType.AUTO)
    var id = 0

    var companyId: Int? = null
    var createdBy: Int? = null
    var fullName = ""
    var mobile = ""
    var username = ""
    var email = ""
    var credentialNo = ""
    var credentialType: CredentialType? = null
    var password = ""
    var role: UserRole = UserRole.CUSTOMER
    var rating: Int? = null

    @TableField(fill = FieldFill.INSERT)
    var createdDate: Date? = null

    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updatedDate: Date? = null

    var verifiedDate: Date? = null
    var deletedDate: Date? = null

    fun isVerified(): Boolean {
        return this.verifiedDate != null
    }

    fun isManager(): Boolean {
        return this.role == UserRole.TENANT_MANAGER
    }
}
