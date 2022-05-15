package com.github.Diosa34.DMS_HelloWorld

interface AbstractCommand {
    fun add(vehicle: Vehicle)
    fun clear()
    fun remove(vehicle: Vehicle)
    fun update(vehicle: Vehicle)
}