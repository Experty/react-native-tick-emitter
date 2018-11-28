using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Tick.Emitter.RNTickEmitter
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNTickEmitterModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNTickEmitterModule"/>.
        /// </summary>
        internal RNTickEmitterModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNTickEmitter";
            }
        }
    }
}
