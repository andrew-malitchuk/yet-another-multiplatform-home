package dev.yamh.io.source.widget

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.GeneralDeviceType
import dev.yamh.domain.core.source.model.device.attribute.ContactAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.OnOffAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.TemperatureAttributeEntity
import dev.yamh.domain.usecase.source.device.GetSelectedDeviceUseCase
import dev.yamh.io.MainActivity
import dev.yamh.io.R
import dev.yamh.io.presentation.feature.room.core.util.toDeviceType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.mp.KoinPlatform.getKoin

public class YamhWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        val getSelectedDeviceUseCase: GetSelectedDeviceUseCase = getKoin().get()


        val state = getSelectedDeviceUseCase().getOrNull()

        provideContent {
            MyContent(state)
        }
    }

    @Composable
    private fun MyContent(state: List<DeviceEntity>?) {
        val accent3 = Color(0xFF6C7FF7)
        val accent2 = Color(0xFF71C0DA)
        LazyColumn(
            modifier = GlanceModifier
                .fillMaxSize()
                .cornerRadius(16.dp)
                .background(accent3)
                .padding(16.dp),
        ) {
            state?.forEach { device ->
                item {
                    DeviceCard(
                        modifier = GlanceModifier
                            .clickable(
                                actionRunCallback<MyClickAction>(
                                    parameters = actionParametersOf(
                                        ActionParameters.Key<String>("device") to (Json.encodeToString(
                                            device
                                        ))
                                    )
                                )
                            ),
                        title = device.name.value,
                        background = accent2,
                    ) {
                        when (device.generalType()) {
                            GeneralDeviceType.Light -> PushButton(
                                modifier = GlanceModifier
                                    .fillMaxSize(),
                                isSelected = (device.attribute as? OnOffAttributeEntity)?.isOn
                                    ?: false,
                                onClick = {}
                            )

                            GeneralDeviceType.Switch -> PushButton(
                                modifier = GlanceModifier
                                    .fillMaxSize(),
                                isSelected = (device.attribute as? OnOffAttributeEntity)?.isOn
                                    ?: false,
                                onClick = {}
                            )

                            GeneralDeviceType.Temperature -> TemperatureDevice(
                                modifier = GlanceModifier
                                    .fillMaxSize(),
                                temperature = (device.attribute as? TemperatureAttributeEntity)?.temperature
                                    ?: "--"
                            )

                            GeneralDeviceType.Contact -> ContactDevice(
                                modifier = GlanceModifier
                                    .fillMaxSize(),
                                type = device.customType?.toDeviceType(),
                                cancelled = (device.attribute as? ContactAttributeEntity)?.isOpen
                                    ?: false
                            )

                            else -> Unit

                        }
                    }
                }
            }

        }
    }
}

@Composable
public fun DeviceCard(
    modifier: GlanceModifier = GlanceModifier,
    title: String,
    background: Color,
    content: @Composable () -> Unit
) {
    val primary0 = Color(0xFF000000)

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = GlanceModifier
                .fillMaxWidth()
                .cornerRadius(24.dp)
                .background(primary0) // border
                .padding(4.dp) // border width
        ) {

            Column(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .cornerRadius(24.dp)
                    .background(
                        background,

                        )
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Box(
                    modifier = GlanceModifier
                        .fillMaxWidth()
                        .cornerRadius(24.dp)
                        .background(primary0)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    content()
                }

                Spacer(GlanceModifier.height(6.dp))

                Text(
                    modifier = GlanceModifier
                        .padding(
                            bottom = 8.dp,
                            start = 16.dp,
                            end = 16.dp,
                        ),
                    text = title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    maxLines = 1,
                )
            }
        }
        Spacer(GlanceModifier.height(8.dp))
    }
}


@Composable
public fun ContactDevice(
    modifier: GlanceModifier = GlanceModifier,
    cancelled: Boolean,
    type: dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.DeviceType?
) {

    val iconRes = when (type) {
        dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.DeviceType.Water -> R.drawable.ic_droplet32
        dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.DeviceType.Door -> R.drawable.ic_lock32
        dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.DeviceType.Move -> R.drawable.ic_radio32
        else -> R.drawable.ic_droplet32
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        // background morph
        Image(
            provider = ImageProvider(R.drawable.ic_morph),
            contentDescription = null,
            modifier = GlanceModifier.fillMaxSize()
        )

        // main state icon
        Image(
            provider = ImageProvider(iconRes),
            contentDescription = null,
            modifier = GlanceModifier.size(24.dp)
        )

        if (cancelled) {
            Image(
                provider = ImageProvider(R.drawable.ic_x32),
                contentDescription = null,
                modifier = GlanceModifier.size(24.dp)
            )
        }
    }
}

@Composable
public fun TemperatureDevice(
    modifier: GlanceModifier = GlanceModifier,
    temperature: String
) {
    val primary0 = Color(0xFF000000)
    val primary1 = Color(0xFFFFFFFF)
    Box(
        modifier = modifier
            .cornerRadius(999.dp)
            .size(128.dp)
            .background(primary1)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${temperature}°",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = ColorProvider(primary0)
            )
        )
    }
}


@Composable
public fun PushButton(
    modifier: GlanceModifier = GlanceModifier,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val accent0 = Color(0xFFFEFC54)
    val primary1 = Color(0xFFFFFFFF)
    val backgroundColor = if (isSelected) accent0 else primary1
    val cornerRadius = if (isSelected) 8.dp else 999.dp

    Box(
        modifier = modifier
            .cornerRadius(cornerRadius)
            .size(128.dp)
            .background(backgroundColor)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            provider = ImageProvider(R.drawable.ic_lightning32),
            contentDescription = null,
            modifier = GlanceModifier.size(24.dp)
        )
    }
}


public class MyClickAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {

        val id = parameters[ActionParameters.Key<String>("device")] ?: return

        println("MyClickAction onAction called for device: ${id}")


        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra("device", id)
        }
        context.startActivity(intent)
    }
}