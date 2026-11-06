/*
 * Copyright (C) 2026 The Cabin Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.decoupled.cabin.compliance.car

import android.car.Car
import android.car.drivingstate.CarUxRestrictions
import android.car.drivingstate.CarUxRestrictionsManager
import android.content.Context
import dev.decoupled.cabin.compliance.VehicleUiState

/**
 * Listens to `CarUxRestrictionsManager` and reports [VehicleUiState].
 *
 * Compiled against the optional `android.car` library, which is not packaged
 * into Cabin. Only construct this on devices that have it
 * (`PackageManager.FEATURE_AUTOMOTIVE`). If the car service is unavailable,
 * the source reports [VehicleUiState.unknown], which the Restriction Engine
 * treats as Restricted (fail-closed).
 *
 * Call [close] when the host goes away.
 */
class CarUxRestrictionsSource(
    context: Context,
    private val onChange: (VehicleUiState) -> Unit,
) : AutoCloseable {

    private val car: Car? = runCatching { Car.createCar(context.applicationContext) }.getOrNull()
    private val manager: CarUxRestrictionsManager? = runCatching {
        car?.getCarManager(Car.CAR_UX_RESTRICTION_SERVICE) as? CarUxRestrictionsManager
    }.getOrNull()

    private val listener = CarUxRestrictionsManager.OnUxRestrictionsChangedListener { restrictions ->
        onChange(restrictions.toVehicleUiState())
    }

    /** Latest state; [VehicleUiState.unknown] when the car service is missing. */
    val current: VehicleUiState
        get() = manager?.currentCarUxRestrictions?.toVehicleUiState() ?: VehicleUiState.unknown()

    init {
        manager?.registerListener(listener)
        onChange(current)
    }

    override fun close() {
        runCatching { manager?.unregisterListener() }
        runCatching { car?.disconnect() }
    }

    private fun CarUxRestrictions.toVehicleUiState(): VehicleUiState =
        CarUxRestrictionsMapping.toVehicleUiState(
            requiresDistractionOptimization = isRequiresDistractionOptimization,
            activeRestrictions = activeRestrictions,
        )
}
