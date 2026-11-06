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

package dev.decoupled.cabin.foundation

/**
 * Marks Experimental scaffold APIs generated from `components/cabin.components.yaml`.
 *
 * These APIs render, expose actions, and honor the Restriction Engine. Craft,
 * size-class layouts, and Stable contracts land in later passes. Do not treat
 * scaffold types as production-complete.
 */
@RequiresOptIn(
    message = "Experimental Cabin scaffold API. May change without notice.",
)
@Retention(AnnotationRetention.BINARY)
annotation class CabinScaffold
