package com.example.nagwatask.framework.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.example.nagwatask.business.entities.Content
import com.example.nagwatask.business.entities.ContentType
import com.example.nagwatask.databinding.ItemContentBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import java.io.File

class ContentHolder(private val binding: ItemContentBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(content: Content) {
        binding.content = content
        if (content.type == ContentType.VIDEO) {
            SimpleExoPlayer.Builder(binding.root.context)
                .build()
                .also { exoPlayer ->
                    binding.videoPlayer.player = exoPlayer
                    exoPlayer.setMediaItem(MediaItem.fromUri(content.url))
                }
        } else {
            val pdfFilePath =
                binding.root.context.applicationInfo.dataDir + "/Nagwa Files" + "/${content.id}.pdf"
            val file = File(pdfFilePath)
            if (file.exists()) {
                loadPdf(file)
            } else {
                buildPdfDownloader(content.url, pdfFilePath)
            }
        }
    }

    private fun buildPdfDownloader(url: String, pdfFilePath: String) {
        PRDownloader.download(
            url,
            binding.root.context.applicationInfo.dataDir + "/Nagwa Files",
            "${binding.content?.id}.pdf"
        ).build().start(object : OnDownloadListener {
            override fun onDownloadComplete() {
                loadPdf(File(pdfFilePath))
            }

            override fun onError(error: com.downloader.Error?) {
                Toast.makeText(
                    binding.root.context,
                    "Error in downloading file : ${error?.connectionException}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun loadPdf(file: File) {
        binding.pdfView.fromFile(file)
            .enableSwipe(true)
            .swipeHorizontal(true)
            .enableDoubletap(true)
            .defaultPage(0)
            .load()
    }


    companion object {
        fun from(
            parent: ViewGroup,
        ): ContentHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemContentBinding.inflate(layoutInflater, parent, false)
            return ContentHolder(binding)
        }
    }
}