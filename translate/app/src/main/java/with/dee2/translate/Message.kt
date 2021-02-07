package with.dee2.translate

data class Message(
    val service: String,
    val type: String,
    val version: String,
    val result: Result
)