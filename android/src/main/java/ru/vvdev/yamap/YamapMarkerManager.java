package ru.vvdev.yamap;

import android.view.View;
import android.graphics.PointF;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.yandex.mapkit.geometry.Point;

import java.util.Map;

import javax.annotation.Nonnull;

import ru.vvdev.yamap.view.YamapMarker;
import ru.vvdev.yamap.view.YamapView;

public class YamapMarkerManager extends ViewGroupManager<YamapMarker> {
    public static final String REACT_CLASS = "YamapMarker";

    YamapMarkerManager() { }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put("onPress", MapBuilder.of("registrationName", "onPress"))
                .build();
    }

    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
                .build();
    }

    private YamapMarker castToMarkerView(View view) {
        return (YamapMarker) view;
    }

    @Nonnull
    @Override
    public YamapMarker createViewInstance(@Nonnull ThemedReactContext context) {
        return new YamapMarker(context);
    }

    // props
    @ReactProp(name = "point")
    public void setPoint(View view, ReadableMap markerPoint) {
        if (markerPoint != null) {
            double lon = markerPoint.getDouble("lon");
            double lat = markerPoint.getDouble("lat");
            Point point = new Point(lat, lon);
            castToMarkerView(view).setPoint(point);
        }
    }

    @ReactProp(name = "zIndex")
    public void setZIndex(View view, int zIndex) {
        castToMarkerView(view).setZIndex(zIndex);
    }

    @ReactProp(name = "scale")
    public void setScale(View view, float scale) {
        castToMarkerView(view).setScale(scale);
    }

    @ReactProp(name = "source")
    public void setSource(View view, String source) {
        if (source != null) {
            castToMarkerView(view).setIconSource(source);
        }
    }

    @ReactProp(name = "anchor")
    public void setAnchor(View view, ReadableMap anchor) {
        if (anchor != null) {
            float x = anchor.getDouble("x").floatValue();
            float y = anchor.getDouble("y").floatValue();
            PointF anchorPoint = new PointF(x, y);
            castToMarkerView(view).setAnchor(anchorPoint);
        }
    }

    @Override
    public void addView(YamapMarker parent, View child, int index) {
        parent.addChildView(child, index);
        super.addView(parent, child, index);
    }

    @Override
    public void removeViewAt(YamapMarker parent, int index) {
        parent.removeChildView(index);
        super.removeViewAt(parent, index);
    }
}
