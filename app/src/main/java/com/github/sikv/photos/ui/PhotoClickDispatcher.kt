package com.github.sikv.photos.ui

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.github.sikv.photos.R
import com.github.sikv.photos.enumeration.PhotoItemClickSource
import com.github.sikv.photos.model.Photo
import com.github.sikv.photos.ui.activity.BaseActivity
import com.github.sikv.photos.ui.activity.PhotoActivity
import com.github.sikv.photos.ui.dialog.OptionsBottomSheetDialogFragment
import com.github.sikv.photos.ui.popup.PhotoPreviewPopup
import com.github.sikv.photos.util.downloadPhotoAndSaveToPictures

class PhotoClickDispatcher(
        private val fragment: Fragment,
        @IdRes private val rootLayoutId: Int,
        private val invertFavorite: (Photo) -> Unit
) {

    private fun getActivity(): Activity {
        return fragment.requireActivity()
    }

    private fun getRootLayout(): ViewGroup {
        return fragment.view!!.findViewById(rootLayoutId)
    }

    fun handlePhotoClick(clickSource: PhotoItemClickSource, photo: Photo, view: View) {
        when (clickSource) {
            PhotoItemClickSource.OPTIONS -> {
                showOptionsDialog(photo)
            }

            PhotoItemClickSource.CLICK -> {
                PhotoActivity.startActivity(getActivity(), view, photo)
            }

            PhotoItemClickSource.LONG_CLICK -> {
                PhotoPreviewPopup().show(getActivity(), getRootLayout(), photo)
            }

            PhotoItemClickSource.FAVORITE -> {
                invertFavorite(photo)
            }

            PhotoItemClickSource.DOWNLOAD -> {
                (getActivity() as? BaseActivity)?.requestWriteExternalStoragePermission {
                    getActivity().downloadPhotoAndSaveToPictures(photo.getPhotoWallpaperUrl())
                }
            }

            else -> { }
        }
    }

    private fun showOptionsDialog(photo: Photo) {
        val options = listOf(getActivity().getString(R.string.copy_link))

        val dialog = OptionsBottomSheetDialogFragment.newInstance(options, null) { index ->
            when (index) {
                0 -> {
                    // TODO Implement
                }
            }
        }

        dialog.show(fragment.childFragmentManager)
    }
}