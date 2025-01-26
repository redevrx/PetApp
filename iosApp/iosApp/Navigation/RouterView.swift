import SwiftUI

@available(iOS 16.0, *)
struct RouterView<Content: View>: View {
    @StateObject var router: NavigationViewModel = NavigationViewModel()
    // Our root view content
    private let content: Content

    init(@ViewBuilder content: @escaping () -> Content) {
        self.content = content()
    }

    var body: some View {
        NavigationStack(path: $router.path) {
            content
                .navigationDestination(for: Route.self) { route in
                    router.view(for: route)
                }
        }
        .environmentObject(router)
    }
}

