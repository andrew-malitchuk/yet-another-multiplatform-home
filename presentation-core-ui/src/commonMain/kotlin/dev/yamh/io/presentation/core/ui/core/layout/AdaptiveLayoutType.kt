package dev.yamh.io.presentation.core.ui.core.layout

import dev.yamh.io.presentation.core.platform.source.device.DeviceOrientation
import dev.yamh.io.presentation.core.platform.source.device.DevicePosture
import dev.yamh.io.presentation.core.ui.core.window.WindowSize


/**
 * Represents all adaptive layout variants supported across devices,
 * combining three dimensions:
 *
 *  ```
 *  ┌────────────────────────────────────────────────────────────────────┐
 *  │ WIDTH-SIZE-CLASS × ORIENTATION × DEVICE-POSTURE                    │
 *  └────────────────────────────────────────────────────────────────────┘
 *  ````
 *  Below is the full mapping table for real, physically possible layouts:
 *
 * | **Layout Type**          | **Width Size Class**                                  | **Orientation**           | **Device Posture** | **Typical Device / Scenario**                               |
 * | ------------------------ | ----------------------------------------------------- | ------------------------- | ------------------ | ----------------------------------------------------------- |
 * | **CompactLayout**          | `COMPACT`                                             | `PORTRAIT`                | `FLAT (Normal)`    | Standard phone (Pixel 6, Galaxy S23)                        |
 * | **ExpandedLayout**         | `EXPANDED`                                            | `PORTRAIT` or `LANDSCAPE` | `FLAT (Normal)`    | Tablet or desktop window (Pixel Tablet, Chromebook)         |
 * | **FoldHorizontalLayout** | `MEDIUM`                                              | `LANDSCAPE`               | `HALF_OPENED`      | Book-style foldables (hinge splits left/right)              |
 * | **FoldVerticalLayout**   | `MEDIUM`                                              | `PORTRAIT`                | `HALF_OPENED`      | Flip-style foldables (hinge splits top/bottom)              |
 *
 */
public enum class AdaptiveLayoutType(
    public val width: WindowSize,
    public val orientation: DeviceOrientation,
    public val posture: DevicePosture
) {
    // --- Flat (Normal) states ---
    CompactLayout(
        width = WindowSize.COMPACT,
        orientation = DeviceOrientation.PORTRAIT,
        posture = DevicePosture.NORMAL
    ),

    ExpandedLayout(
        width = WindowSize.EXPANDED,
        orientation = DeviceOrientation.LANDSCAPE,
        posture = DevicePosture.NORMAL
    ),

    // --- Foldable (Half-Opened) states ---
    FoldHorizontalLayout(
        width = WindowSize.MEDIUM,
        orientation = DeviceOrientation.LANDSCAPE,
        posture = DevicePosture.HALF_OPENED
    ),

    FoldVerticalLayout(
        width = WindowSize.MEDIUM,
        orientation = DeviceOrientation.PORTRAIT,
        posture = DevicePosture.HALF_OPENED
    );

    public companion object {
        public fun from(
            width: WindowSize,
            orientation: DeviceOrientation,
            posture: DevicePosture
        ): AdaptiveLayoutType {

            println("AdaptiveLayoutType: $width | $orientation | $posture")


            return when {


                posture == DevicePosture.HALF_OPENED && orientation == DeviceOrientation.PORTRAIT ->
                    FoldVerticalLayout

                posture == DevicePosture.HALF_OPENED && orientation == DeviceOrientation.LANDSCAPE ->
                    FoldHorizontalLayout

                //
                width == WindowSize.EXPANDED && posture == DevicePosture.HALF_OPENED && orientation == DeviceOrientation.PORTRAIT ->
                    FoldVerticalLayout

                width == WindowSize.EXPANDED && posture == DevicePosture.HALF_OPENED && orientation == DeviceOrientation.LANDSCAPE ->
                    FoldHorizontalLayout

                width == WindowSize.MEDIUM && posture == DevicePosture.HALF_OPENED && orientation == DeviceOrientation.PORTRAIT ->
                    FoldVerticalLayout

                width == WindowSize.MEDIUM && posture == DevicePosture.HALF_OPENED && orientation == DeviceOrientation.LANDSCAPE ->
                    FoldHorizontalLayout
                //


                //
                width == WindowSize.EXPANDED  && posture == DevicePosture.NORMAL && orientation == DeviceOrientation.PORTRAIT ->
                    CompactLayout
                width == WindowSize.EXPANDED  && posture == DevicePosture.NORMAL && orientation == DeviceOrientation.LANDSCAPE ->
                    ExpandedLayout
                //
                width == WindowSize.EXPANDED ->
                    ExpandedLayout

                else -> CompactLayout
            }
        }
    }
}
