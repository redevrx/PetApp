import SwiftUI


@available(iOS 16.0, *)
struct ContentView: View {

    var body: some View {
        RouterView {
          HomeView()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        if #available(iOS 16.0, *) {
            ContentView()
        } else {
           Text("Not support your phone.")
        }
    }
}
