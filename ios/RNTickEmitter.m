
#import "RNTickEmitter.h"

#import <React/RCTEventDispatcher.h>

@implementation RNTickEmitter
{
    BOOL hasListeners;
    NSTimer *timer;
}

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

- (NSArray<NSString *> *)supportedEvents
{
  return @[@"TimersTest"];
}

// Will be called when this module's first listener is added.
-(void)startObserving {
    hasListeners = YES;
}

// Will be called when this module's last listener is removed, or on dealloc.
-(void)stopObserving {
    hasListeners = NO;
}

RCT_EXPORT_METHOD(startTimer)
{
    dispatch_async(dispatch_get_main_queue(), ^{
        timer = [NSTimer scheduledTimerWithTimeInterval:0.1  target:self selector:@selector(actionTimer) userInfo:nil repeats:YES];
   });
}

-(void)actionTimer
{
  if (hasListeners) {
    [self sendEventWithName:@"TimersTest" body:@[@{@"thisworks": @YES}]];
  }
}

RCT_EXPORT_METHOD(stopTimer)
{
    if(timer) {
        NSLog(@"RNTickEmitter stop native timer");
        [timer invalidate];
        timer = nil;
    }
}


@end
