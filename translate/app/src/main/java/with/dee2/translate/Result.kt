package with.dee2.translate

data class Result(
    val engineType: String,
    val pivot: Any,
    val srcLangType: String,
    val tarLangType: String,
    val translatedText: String
)