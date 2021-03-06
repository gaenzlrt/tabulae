package de.emdete.tabulae.map;

import java.io.File;
import java.net.HttpURLConnection;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.util.AndroidUtil;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.layer.TileLayer;
import org.mapsforge.map.layer.cache.FileSystemTileCache;
import org.mapsforge.map.layer.cache.InMemoryTileCache;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.cache.TwoLevelTileCache;
import org.mapsforge.map.layer.download.TileDownloadLayer;
import de.emdete.tabulae.Tabulae;
import static de.emdete.tabulae.map.Constants.*;

/**
 * Base of the layers, adds features like proper hide, force zoom limits, ...
 */
abstract class LayerBase {
	protected MapView mapView;
	protected TileLayer tileLayer;
	protected TileCache tileCache;
	protected InMemoryTileCache memCache;
	static {
		HttpURLConnection.setFollowRedirects(false);
		// TODO: use con.setInstanceFollowRedirects(false);
	}

	LayerBase(Tabulae activity, MapView mapView, boolean persistant) {
		this.mapView = mapView;
		if (persistant) {
			int size = AndroidUtil.getMinimumCacheSize(activity, mapView.getModel().displayModel.getTileSize(), mapView.getModel().frameBufferModel.getOverdrawFactor(), 1f);
			//if (DEBUG) Log.d(TAG, "LayerBase.LayerBase minmal cache size=" + size);
			memCache = new InMemoryTileCache(size);
			tileCache = new TwoLevelTileCache(
					memCache,
					new FileSystemTileCache(99999, new File(activity.getTilesDir(), getId()), AndroidGraphicFactory.INSTANCE, true)
			);
		} else {
			tileCache = AndroidUtil.createTileCache(activity, getId(), mapView.getModel().displayModel.getTileSize(),
					1f, mapView.getModel().frameBufferModel.getOverdrawFactor());
		}
	}

	byte getZoomLevelMin() {
		return MIN_ZOOM;
	}

	byte getZoomLevelMax() {
		return MAX_ZOOM;
	}

	abstract String getId();

	void setVisible(boolean visible) {
		tileLayer.setVisible(visible);
		if (visible) {
			mapView.getModel().mapViewPosition.setZoomLevelMin(getZoomLevelMin());
			mapView.getModel().mapViewPosition.setZoomLevelMax(getZoomLevelMax());
		} else {
			if (memCache != null) memCache.purge();
		}
	}

	void onDestroy() {
		mapView.getLayerManager().getLayers().remove(tileLayer);
		tileLayer.onDestroy();
		tileCache.destroy();
	}

	public void onPause() {
		if (tileLayer instanceof TileDownloadLayer)
			((TileDownloadLayer) tileLayer).onPause();
	}

	public void onResume() {
		if (tileLayer instanceof TileDownloadLayer)
			((TileDownloadLayer) tileLayer).onResume();
	}

	static boolean isAvaiable(Tabulae activity) {
		return true;
	}
}
