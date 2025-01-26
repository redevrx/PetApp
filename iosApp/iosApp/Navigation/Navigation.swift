import SwiftUI


@available(iOS 16.0, *)
class NavigationViewModel : ObservableObject {
    @Published var path: NavigationPath = NavigationPath()

    @ViewBuilder func view(for route: Route) -> some View {
        switch route {
        case .homeView:
            HomeView()
        case .detailView(let data):
            DetailView(data: data)
        }
    }

    func navigateTo(_ appRoute: Route) {
        path.append(appRoute)
    }

    func navigateBack() {
        path.removeLast()
    }

    func popToRoot() {
        path.removeLast(path.count)
    }
}