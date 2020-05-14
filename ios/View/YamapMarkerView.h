#ifndef YamapMarkerView_h
#define YamapMarkerView_h
#import <React/RCTComponent.h>
#import <YandexMapKit/YMKPolygonMapObject.h>
#import <YandexMapKit/YMKPoint.h>

@class RCTBridge;

@interface YamapMarkerView: UIView<YMKMapObjectTapListener, RCTComponent>

@property (nonatomic, copy) RCTBubblingEventBlock onPress;

// props
-(void) setZIndex:(NSNumber*) _zIndex;
-(void) setScale:(NSNumber*) _scale;
-(void) setAnchor:(NSValue*) _anchor;
-(void) setSource:(NSString*) _source;
-(void) setPoint:(YMKPoint*) _points;

-(YMKPoint*) getPoint;
-(YMKPlacemarkMapObject*) getMapObject;
-(void) setMapObject:(YMKPlacemarkMapObject*) mapObject;

@end

#endif /* YamapMarkerView_h */
