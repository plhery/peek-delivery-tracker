package com.plhery.peekdeliverytracker.core

// From DeviceParcels.swift, the error the localizer explains. The device's parcels come with their own port.

/** Lookups are counted for each network; the day's count is spent. */
class DeviceLookupsSpent : Exception() {
    override fun equals(other: Any?): Boolean = other is DeviceLookupsSpent
    override fun hashCode(): Int = DeviceLookupsSpent::class.hashCode()
}
