package ru.vvdev.yamap;

import android.view.View;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ru.vvdev.yamap.view.YamapView;

public class YamapViewManager extends ViewGroupManager<YamapView> {
    public static final String REACT_CLASS = "YamapView";

    private static final int SET_CENTER = 1;
    private static final int FIT_ALL_MARKERS = 2;
    private static final int FIND_ROUTES = 3;

    YamapViewManager() {
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .build();
    }

    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
                .put("routes", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onRouteFound")))
                .build();
    }

    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(
                "setCenter",
                SET_CENTER,
                "fitAllMarkers",
                FIT_ALL_MARKERS,
                "findRoutes",
                FIND_ROUTES);
    }

    @Override
    public void receiveCommand(
            YamapView view,
            String commandType,
            @Nullable ReadableArray args) {
        Assertions.assertNotNull(view);
        Assertions.assertNotNull(args);
        switch (commandType) {
            case "setCenter":
                setCenter(view, args.getMap(0));
                return;
            case "fitAllMarkers":
                fitAllMarkers(view);
                return;
            case "findRoutes":
                if (args != null) {
                    findRoutes(view, args.getArray(0), args.getArray(1), args.getString(2));
                }
                return;
            default:
                throw new IllegalArgumentException(String.format(
                        "Unsupported command %d received by %s.",
                        commandType,
                        getClass().getSimpleName()));
        }
    }

    private YamapView castToYaMapView(View view) {
        return (YamapView) view;
    }

    @Nonnull
    @Override
    public YamapView createViewInstance(@Nonnull ThemedReactContext context) {
        YamapView view = new YamapView(context);
        MapKitFactory.getInstance().onStart();
        view.onStart();
        return view;
    }

    private void setCenter(View view, ReadableMap center) {
        if (center != null) {
            Point centerPosition = new Point(center.getDouble("lat"), center.getDouble("lon"));
            float zoom = (float) center.getDouble("zoom");
            castToYaMapView(view).setCenter(centerPosition, zoom);
        }
    }

    private void fitAllMarkers(View view) {
        castToYaMapView(view).fitAllMarkers();
    }

    private void findRoutes(View view, ReadableArray jsPoints, ReadableArray jsVehicles, String id) {
        if (jsPoints != null) {
            ArrayList<Point> points = new ArrayList<>();
            for (int i = 0; i < jsPoints.size(); ++i) {
                ReadableMap point = jsPoints.getMap(i);
                if (point != null) {
                    points.add(new Point(point.getDouble("lat"), point.getDouble("lon")));
                }
            }
            ArrayList<String> vehicles = new ArrayList<>();
            if (jsVehicles != null) {
                for (int i = 0; i < jsVehicles.size(); ++i) {
                    vehicles.add(jsVehicles.getString(i));
                }
            }
            castToYaMapView(view).findRoutes(points, vehicles, id);
        }
    }

    // props
    @ReactProp(name = "userLocationIcon")
    public void setUserLocationIcon(View view, String icon) {
        if (icon != null) {
            castToYaMapView(view).setUserLocationIcon(icon);
        }
    }
   
    @ReactProp(name = "userLocationIconScale")
    public void setUserLocationIconScale(View view, float userLocationIconScale) {
        castToMarkerView(view).setUserLocationIconScale(userLocationIconScale);
    }

    @Override
    public void addView(YamapView parent, View child, int index) {
        parent.addFeature(child, index);
        super.addView(parent, child, index);
    }

    @Override
    public void removeViewAt(YamapView parent, int index) {
        parent.removeChild(index);
        super.removeViewAt(parent, index);
    }
}
