#import <React/RCTViewManager.h>
#import <MapKit/MapKit.h>
#import <math.h>
#import <RNYamap-Swift.h>

#import "YamapCircle.h"
#import "RNYamap.h"
#import "View/RNYMView.h"
#import "Converter/RCTConvert+Yamap.m"



#ifndef MAX
#import <NSObjCRuntime.h>
#endif

@implementation YamapCircle

RCT_EXPORT_MODULE()

- (instancetype)init {
  self = [super init];
  return self;
}

- (UIView *_Nullable)view {
  return [[YamapCircleView alloc] init];
}

// props
RCT_CUSTOM_VIEW_PROPERTY (point, YMKPoint, YamapCircleView) {
  if (json != nil) {
    [view setPoint: [RCTConvert YMKPoint:json]];
  }
}

RCT_CUSTOM_VIEW_PROPERTY(fillColor, NSNumber, YamapCircleView) {
  [view setFillColor: [RCTConvert UIColor:json]];
}

RCT_CUSTOM_VIEW_PROPERTY(strokeColor, NSNumber, YamapCircleView) {
  [view setStrokeColor: [RCTConvert UIColor:json]];
}

RCT_CUSTOM_VIEW_PROPERTY(strokeWidth, NSNumber, YamapCircleView) {
  [view setStrokeWidth: [RCTConvert float:json]];
}

RCT_CUSTOM_VIEW_PROPERTY(zIndex, NSNumber, YamapCircleView) {
  [view setZIndex: [RCTConvert float:json]];
}

@end
