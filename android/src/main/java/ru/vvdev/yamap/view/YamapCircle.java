package ru.vvdev.yamap.view;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.view.ReactViewGroup;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.geometry.Circle;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.CircleMapObject;

import java.util.ArrayList;

import ru.vvdev.yamap.models.ReactMapObject;
import ru.vvdev.yamap.utils.Callback;
import ru.vvdev.yamap.utils.ImageLoader;

public class YamapCircle extends ReactViewGroup implements MapObjectTapListener, ReactMapObject {
    private final Point CIRCLE_CENTER = new Point(55.753215, 37.622504);

    public Point point;
    private CircleMapObject mapObject;
    private CircleMapObject circle = mapObject.addCircle(new Circle(CIRCLE_CENTER, 100f), Color.GREEN, 2, Color.RED);
    private int fillColor = Color.BLACK;
    private int strokeColor = Color.BLACK;
    private int zIndex = 1;
    private float strokeWidth = 1.f;

    public YamapCircle(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    // props
    public void setCircle(Point _point) {
        point = _point;
        updateCircle();
    }

    public void setStrokeColor(int _color) {
        strokeColor = _color;
        updateCircle();
    }

    public void setFillColor(int _color) {
        fillColor = _color;
        updateCircle();
    }

    public void setStrokeWidth(float width) {
        strokeWidth = width;
        updateCircle();
    }

    public void setZIndex(int _zIndex) {
        zIndex = _zIndex;
        updateCircle();
    }

    private void updateCircle() {
        if (mapObject != null) {
            mapObject.setGeometry(circle);
            mapObject.setStrokeWidth(strokeWidth);
            mapObject.setStrokeColor(strokeColor);
            mapObject.setFillColor(fillColor);
            mapObject.setZIndex(zIndex);
        }
    }

    public void setMapObject(MapObject obj) {
        mapObject = (CircleMapObject) obj;
        mapObject.addTapListener(this);
        updateCircle();
    }

    public MapObject getMapObject() {
        return mapObject;
    }

    // @Override
    // public boolean onMapObjectTap(@NonNull MapObject mapObject, @NonNull Point point) {
    //     WritableMap e = Arguments.createMap();
    //     ((ReactContext) getContext()).getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onPress", e);
    //     return false;
    // }
}
