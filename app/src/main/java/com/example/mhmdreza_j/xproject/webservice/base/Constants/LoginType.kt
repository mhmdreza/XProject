package ir.sharif.vamdeh.webservices.base.Constants

enum class LoginType(val type: String) {
    GUEST("guest"),
    GOOGLE("google-oauth2"),
    FACEBOOK("facebook")
}