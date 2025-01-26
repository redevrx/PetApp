//
//  HomeView.swift
//  iosApp
//
//  Created by  เกษม ศรีเครือดง on 22/1/2568 BE.
//  Copyright © 2568 BE orgName. All rights reserved.
//

import SwiftUICore
import SwiftUI
import Shared

@available(iOS 16.0, *)
struct HomeView: View {
    @State private var showContent = false
    @EnvironmentObject var router: NavigationViewModel
    @State private var txtSearch = ""
    private var homeViewModel: HomeViewModel = KoinInject.init().homeViewModel
    @State private var items = [CategoryModel]()
    @State private var petItems = [PetModel]()
    private var closerCategory: FlowC<NSArray>?
    private var petFlow: FlowC<NSArray>

    init() {
        closerCategory = homeViewModel.categories
        petFlow = homeViewModel.pets
        homeViewModel.fetchCategories()
        homeViewModel.fetchDetail()
    }

    var body: some View {
        ZStack {
            VStack(alignment: .leading) {
                //search box
                searchBox()

                ///Categories title
                Text("Categories")
                    .padding(.top)
                    .font(.title3)

                ///list categories
                ScrollView(.horizontal, showsIndicators: false) {
                    LazyHStack {
                        ForEach(items, id: \.self) { it in
                            categoriesCard(data: it)
                        }
                    }
                    .frame(width: .infinity, height: 100)
                }

                ///list content card
                petList()
                ///next
            }

            /// nav
            Nav()
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
        .onAppear {

            ///observer categories
            closerCategory?.listen { data in
                guard let items = data as? [CategoryModel] else {
                    return
                }

                self.items = items
            }

            ///observer pet
            petFlow.listen { it in
                guard let pets = it as? [PetModel] else {
                    return
                }

                self.petItems = pets
            }
        }
        .onDisappear {
            self.closerCategory?.close()
            self.petFlow.close()
        }

    }

    @ViewBuilder
    func Nav() -> some View {
        VStack {
            Spacer()

            HStack {
                Button(action: {}) {
                    Image(systemName: "house")
                        .resizable()
                        .padding(16)
                        .foregroundColor(.white)
                }
                .frame(width: 50, height: 50)
                .background(.orange)
                .clipShape(.circle)

                Button(action: {}) {
                    Image(systemName: "heart")
                        .resizable()
                        .padding(16)
                        .foregroundColor(.gray)
                }
                .frame(width: 50, height: 50)

                Button(action: {}) {
                    Image(systemName: "calendar")
                        .resizable()
                        .padding(16)
                        .foregroundColor(.gray)
                }
                .frame(width: 50, height: 50)

                Button(action: {}) {
                    Image(systemName: "person.fill")
                        .resizable()
                        .padding(16)
                        .foregroundColor(.gray)
                }
                .frame(width: 50, height: 50)
            }
            .frame(width: 300, height: 70, alignment: .center)
            .background(.white)
            .cornerRadius(50)
            .shadow(
                color: Color.black.opacity(0.1),
                radius: 8,
                x: 0,
                y: 0
            )
        }
    }

    @ViewBuilder
    func petList() -> some View {
        TabView {
            ForEach(petItems, id: \.self) { it in
                VStack {
                    VStack {
                        AsyncImage(url: URL(string: it.image)) { result in
                            result.image?.resizable()
                        }
                        .aspectRatio(1.0, contentMode: .fit)
                    }
                    .frame(width: 340, height: 280)
                    .background(Color(hex: it.color).opacity(0.2))
                    .cornerRadius(16)
                    .padding(6)


                    HStack {
                        VStack(alignment: .leading) {
                            Text("\(it.title)")
                                .font(.body)
                                .foregroundColor(.gray)
                            Text("\(it.name)")
                                .font(.title3)
                        }

                        Spacer()
                        Button(action: {}) {
                            Image(systemName: "magnifyingglass")
                                .resizable()
                                .aspectRatio(contentMode: .fit)
                                .padding(12)
                                .foregroundColor(.white)
                        }
                        .background(.orange)
                        .clipShape(.circle)
                        .frame(width: 40, height: 40)
                    }

                    HStack {
                        Text("\(it.localtion)")
                            .font(.body)
                            .foregroundColor(.gray)
                        Spacer()
                        Text("\(it.age)")
                            .font(.body)
                            .foregroundColor(.gray)
                    }
                }
                .padding()
                .background(Color.white)
                .frame(width: 380)
                .cornerRadius(20)
                .shadow(
                    color: Color.black.opacity(0.1),
                    radius: 8,
                    x: 0,
                    y: 0
                )
                .tag(it.id)
            }

        }
        .tabViewStyle(PageTabViewStyle())
    }

    @ViewBuilder
    func categoriesCard(data: CategoryModel) -> some View {
        VStack {
            ZStack {
                Circle()
                    .fill(Color(hex: data.color).opacity(0.23))
                    .frame(width: 34 * 2, height: 34 * 2)

                AsyncImage(url: URL(string: data.image)) { result in
                    result.image?
                        .resizable()
                }

                .aspectRatio(1.0, contentMode: .fit)
                .frame(width: 30, height: 30)

            }

            Text("\(data.name)").font(.body)
                .foregroundColor(data.name == "Dogs" ? .black : .gray)
        }
        .padding(.horizontal, 12)
    }

    @ViewBuilder
    func searchBox() -> some View {
        HStack {
            HStack {
                Image(systemName: "magnifyingglass")
                    .foregroundColor(.gray.opacity(0.5))
                TextField("Search", text: $txtSearch)
            }
            .padding()
            .overlay(RoundedRectangle(cornerRadius: 19.0)
                         .strokeBorder(Color.gray.opacity(0.5)))
            ///button setting
            Button(action: {}) {
                Image(systemName: "slider.horizontal.3")
                    .padding()
                    .foregroundColor(.white)
            }
            .background(.orange)
            .clipShape(.circle)
        }
    }

}


extension Color {
    static func random() -> Color {
        let red = Double.random(in: 0...1)
        let green = Double.random(in: 0...1)
        let blue = Double.random(in: 0...1)
        return Color(red: red, green: green, blue: blue)
    }

    init(hex: String) {
        // Remove the "0x" prefix if it exists
        let hexString = hex.replacingOccurrences(of: "0x", with: "")

        // Ensure the hex string is exactly 6 or 8 characters (RGBA or RGB format)
        let scanner = Scanner(string: hexString)
        var hexNumber: UInt64 = 0
        scanner.scanHexInt64(&hexNumber)

        // Extract the RGB components
        let red = Double((hexNumber >> 16) & 0xFF) / 255.0
        let green = Double((hexNumber >> 8) & 0xFF) / 255.0
        let blue = Double(hexNumber & 0xFF) / 255.0

        // If there's an alpha component, it will be in the highest byte
        let alpha = Double((hexNumber >> 24) & 0xFF) / 255.0

        self.init(red: red, green: green, blue: blue, opacity: alpha)
    }
}
