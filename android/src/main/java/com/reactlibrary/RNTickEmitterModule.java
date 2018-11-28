
package com.reactlibrary;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

public class RNTickEmitterModule extends ReactContextBaseJavaModule {

  private static final String TAG = "RNTickEmitter";
  private final ReactApplicationContext reactContext;
  private Timer timer;
  private TimerTask timerTask;

  public RNTickEmitterModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNTickEmitter";
  }

  @ReactMethod
  public void startTimer() {
    if(timer == null) {
      timer = new Timer("TimerTask");
      timerTask = new TimerTask(){
        @Override
        public void run(){
          actionTimer();
        }
      };
      timer.schedule(timerTask,0,100);
    }
  }

  private void actionTimer() {
      try {
          ReactContext reactContext = getReactApplicationContext();
          if (reactContext != null && reactContext.hasActiveCatalystInstance()) {
              WritableMap params = Arguments.createMap();
              reactContext
                      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                      .emit("TimersTest", params);
          } else {
              Log.e(TAG, "actionTimer(): reactContext is null or not having CatalystInstance yet.");
          }
      } catch (RuntimeException e) {
          Log.e(TAG, "actionTimer(): java.lang.RuntimeException: Trying to invoke JS before CatalystInstance has been set!");
      }
  }

  @ReactMethod
  public void stopTimer() {
    if(timer != null) {
         timer.cancel();
         timer = null;
     }
  }
}
