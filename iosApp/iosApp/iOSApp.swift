import Shared
import SwiftUI

@available(iOS 16.0, *)
@main
struct iOSApp: App {

    init() {
        InitKoinKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}