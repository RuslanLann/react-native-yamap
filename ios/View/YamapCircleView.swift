import UIKit
import YandexMapKit

@objc(YamapCircleView)
class YamapCircleView: UIView {
  
  private var mapObject: YMKCircleMapObject!
  private var point: YMKPoint
  private var circle: YMKCircle
  private let radius: Float
  private var strokeWidth: Float
  private var strokeColor: UIColor
  private var fillColor: UIColor
  private var zIndex: Float
  
  override init(frame: CGRect) {
    
    point = YMKPoint(latitude: 55.667823, longitude: 37.548258)
    radius = 200
    strokeWidth = 1
    strokeColor = UIColor.black
    fillColor = UIColor.black
    zIndex = 1
    circle = YMKCircle(center: point, radius: radius)
    
    super.init(frame: frame)
  }
  
  required init?(coder: NSCoder) {
    fatalError("init(coder:) has not been implemented")
  }
  
  func updateCircle() {
    if (mapObject != nil) {
      mapObject.geometry = circle
      mapObject.strokeWidth = strokeWidth
      mapObject.strokeColor = strokeColor
      mapObject.fillColor = fillColor
      mapObject.zIndex = zIndex
    }
  }
  
  @objc(setPoint:) func setPoint(_point: YMKPoint) {
    point = _point;
    circle = YMKCircle(center: point, radius: radius)
    updateCircle()
  }
  
  @objc(setFillColor:) func setFillColor(_fillColor: UIColor) {
    fillColor = _fillColor;
    updateCircle()
  }
  
  @objc(setStrokeColor:) func setStrokeColor(_strokeColor: UIColor) {
    strokeColor = _strokeColor;
    updateCircle()
  }
  
  @objc(setStrokeWidth:) func setStrokeWidth(_strokeWidth: Float) {
    strokeWidth = _strokeWidth;
    updateCircle()
  }
  
  @objc(setZIndex:) func setZIndex(_zIndex: Float) {
    zIndex = _zIndex;
    updateCircle()
  }
  
  @objc func getCircle() -> YMKCircle {
    return circle
  }
  
  @objc func getMapObject() -> YMKCircleMapObject {
    return mapObject
  }
  
  @objc(setMapObject:) func setMapObject(_mapObject: YMKCircleMapObject) {
    mapObject = _mapObject;
    updateCircle()
  }
  
  @objc(addCircleToMapObjects:) func addCircleToMapObjects(_mapObjectsCollection: YMKMapObjectCollection) -> YMKCircleMapObject {
    let circleObject = _mapObjectsCollection.addCircle(
      with: circle,
      stroke: strokeColor,
      strokeWidth: strokeWidth,
      fill: fillColor
    )
    
    return circleObject
  }
  
}

