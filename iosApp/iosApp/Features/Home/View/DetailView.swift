//
//  DetailView.swift
//  iosApp
//
//  Created by  เกษม ศรีเครือดง on 22/1/2568 BE.
//  Copyright © 2568 BE orgName. All rights reserved.
//

import SwiftUI

struct DetailView: View {
    private var data:String

    init(data:String){
        self.data = data
    }

    var body: some View {
        VStack {
            Text("Detail View: \(self.data)")
        }
            .navigationBarBackButtonHidden(false)

    }
}

