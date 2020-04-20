package ru.vvdev.yamap;

import android.view.View;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.yandex.mapkit.geometry.Circle;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Nonnull;

import ru.vvdev.yamap.view.YamapCircle;

public class YamapCircleManager extends ViewGroupManager<YamapCircle> {
    public static final String REACT_CLASS = "YamapCircle";

    YamapCircleManager() { }

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

    private YamapCircle castToCircleView(View view) {
        return (YamapCircle) view;
    }

    @Nonnull
    @Override
    public YamapCircle createViewInstance(@Nonnull ThemedReactContext context) {
        return new YamapCircle(context);
    }

    // props
    @ReactProp(name = "point")
    public void setCircle(View view, ReadableMap circlePoint) {
        if (circlePoint != null) {
            double lon = circlePoint.getDouble("lon");
            double lat = circlePoint.getDouble("lat");
            Circle circle = new Circle(new Point(lat, lon), 100);
            castToCircleView(view).setCircle(circle);
        }
    }

    @ReactProp(name = "strokeWidth")
    public void setStrokeWidth(View view, float width) {
        castToCircleView(view).setStrokeWidth(width);
    }

    @ReactProp(name = "strokeColor")
    public void setStrokeColor(View view, int color) {
        castToCircleView(view).setStrokeColor(color);
    }
    @ReactProp(name = "fillColor")
    public void setFillColor(View view, int color) {
        castToCircleView(view).setFillColor(color);
    }
    @ReactProp(name = "zIndex")
    public void setZIndex(View view, int zIndex) {
        castToCircleView(view).setZIndex(zIndex);
    }
}
