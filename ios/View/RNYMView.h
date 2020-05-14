#ifndef RNYMView_h
#define RNYMView_h
#import <React/RCTComponent.h>
#import <YandexMapKit/YMKMapView.h>

@class RCTBridge;

@interface RNYMView: YMKMapView<YMKUserLocationObjectListener, RCTComponent>

@property (nonatomic, copy) RCTBubblingEventBlock onRouteFound;

@property (nonatomic) YMKUserLocationView* _Nullable userLocationView;
@property (nonatomic) UIImage* _Nullable userLocationImage;

// ref
-(void) setCenter:(YMKPoint*) center withZoom:(float) zoom;
-(void) fitAllMarkers;
-(void) findRoutes:(NSArray<YMKRequestPoint*>*) points vehicles:(NSArray<NSString*>*) vehicles withId:(NSString*)_id;

// props
-(void) setUserLocationIcon:(NSString*) iconSource;
-(void) setUserLocationIconScale:(NSNumber*) _userLocationIconScale;

@end

#endif /* RNYMView_h */
