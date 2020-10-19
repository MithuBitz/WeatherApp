package com.example.weatherforcast.data.provider

import com.example.weatherforcast.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}