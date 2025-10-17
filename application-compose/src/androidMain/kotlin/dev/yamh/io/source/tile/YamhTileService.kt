package dev.yamh.io.source.tile

import android.content.Intent
import android.os.Build
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import androidx.annotation.RequiresApi
import dev.yamh.io.MainActivity

@RequiresApi(Build.VERSION_CODES.N)
public class YamhTileService : TileService() {

    override fun onStartListening() {
        qsTile?.let {
            it.state = Tile.STATE_ACTIVE
            it.label = "YAMH"
            it.updateTile()
        }
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onClick() {
      val intent = Intent(this, MainActivity::class.java).apply {
          addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      }
      val pending = android.app.PendingIntent.getActivity(
          this, 0, intent,
          android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_IMMUTABLE
      )
      startActivityAndCollapse(pending)
    }
}
