package com.github.sikv.photos.ui.fragment

import androidx.fragment.app.Fragment

class PhotosRootFragment : RootFragment() {

    override fun provideRootFragment(): Fragment {
        return PhotosFragment()
    }
}