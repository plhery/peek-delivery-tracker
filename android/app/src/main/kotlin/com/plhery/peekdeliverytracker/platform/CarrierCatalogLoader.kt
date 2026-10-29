package com.plhery.peekdeliverytracker.platform

import com.plhery.peekdeliverytracker.core.CarrierCatalog
import com.plhery.peekdeliverytracker.core.HttpRequest
import com.plhery.peekdeliverytracker.core.HttpTransport

/**
 * The catalog's refresh as CarrierCatalog.swift makes it: a GET through the app's transport, past any cache
 * (`reloadIgnoringLocalCacheData`; the app's OkHttp client has none), so a 304 is the server's answer to the catalog's
 * own `If-None-Match`, and the timeout the catalog asks for.
 */
class CarrierCatalogLoader(private val transport: HttpTransport) : CarrierCatalog.Loader {
    override suspend fun load(request: CarrierCatalog.Request): CarrierCatalog.Response {
        val response = transport.data(HttpRequest(request.url, headers = request.headers, timeout = request.timeoutSeconds.toDouble()))
        return CarrierCatalog.Response(response.statusCode, response.headers, response.body)
    }
}
