package dev.yamh.io.data.ghome.source.datasource.authorization

expect public class AuthorizationHelper{

    public constructor()

    public suspend fun isAuthorized(): Boolean

    public suspend fun requestAuthorization(): Boolean


}