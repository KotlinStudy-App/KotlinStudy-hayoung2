package with.dee2.myapplicationdfsd

import com.google.gson.annotations.SerializedName

data class LoginData(
    @field:SerializedName("userEmail") private val userName: String,
    @field:SerializedName("userPwd") private val userPwd: String
)
data class UserData(
    @field:SerializedName("useruid") private val useruid: Int

)
class LoginResponse {
    @SerializedName("code")
    val code = 0

    @SerializedName("message")
    val message: String? = null

    @SerializedName("userId")
    val userId = 0

}

class  friendrelation(
    @field:SerializedName("User1Uid") private val user1uid: Int,
    @field:SerializedName("User1Name") private val user1name: String,
    @field:SerializedName("User2Uid") private val user2uid: Int,
    @field:SerializedName("User2Name") private val user2name: String
)
class FriendlistResponse {
    @SerializedName("userList")
    val user1uid : Array<friendrelation>? =null

}