package com.example.lifestyleapplication.ui.home

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.lifestyleapplication.R
import com.example.lifestyleapplication.databinding.FragmentViewDevotionsBinding
import com.example.lifestyleapplication.ui.constants.constants
import com.example.lifestyleapplication.ui.interfaces.devotionalInterface
import com.example.lifestyleapplication.ui.models.allDevotions
import com.example.lifestyleapplication.ui.models.devotions
import com.example.lifestyleapplication.ui.retrofit.devotionalRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class ViewDevotionsFragment : Fragment() {

    private lateinit var binding: FragmentViewDevotionsBinding
    private lateinit var devotional: devotionalInterface
    private lateinit var mediaPlayer: MediaPlayer
    private var audioUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewDevotionsBinding.inflate(inflater, container, false)
        binding.imgBackDevotionals.setOnClickListener {
            findNavController().navigate(R.id.action_viewDevotionsFragment_to_selectedDevotionTopicFragment)
        }
        mediaPlayer = MediaPlayer().apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
            }
        }

        binding.progressDevotion.visibility = View.VISIBLE
        binding.linearDevotion.visibility = View.GONE
        binding.txtTitleDev.text = arguments?.getString("TITLE")
        val string: String = arguments?.getString("TITLE").toString()
        devotional = devotionalRetrofit.getRetrofit().create(devotionalInterface::class.java)
        val call: Call<allDevotions> = devotional.getTitles(string)
        call.enqueue(object: Callback<allDevotions>{
            override fun onResponse(
                call: Call<allDevotions>,
                response: Response<allDevotions>
            ) {
                if (response.isSuccessful){
                    binding.progressDevotion.visibility = View.GONE
                    binding.linearDevotion.visibility = View.VISIBLE
                    getData(response.body())
                }
            }

            override fun onFailure(call: Call<allDevotions>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
                binding.progressDevotion.visibility = View.VISIBLE
                binding.linearDevotion.visibility = View.GONE
            }
        })

        binding.seekDevotion.max = mediaPlayer.duration
        binding.seekDevotion.progress = mediaPlayer.currentPosition

        binding.imgPLayPause.setOnClickListener {
            playPauseAudio()
            /**if (mediaPlayer.isPlaying){
                mediaPlayer.stop()
                mediaPlayer.reset()
                mediaPlayer.release()
                binding.imgPLayPause.setImageResource(R.drawable.ic_baseline_play_arrow_24)

            }
            else{
                playPauseAudio()
            }**/

        }

        return binding.root
    }

    private fun playPauseAudio() {
        try {
            binding.imgPLayPause.setImageResource(R.drawable.ic_baseline_pause_24)
            mediaPlayer.setDataSource(constants.DEVOTIONALS + audioUrl)
            mediaPlayer.prepare()
            mediaPlayer.start()

        }
        catch (e: IOException){

        }
    }

    private fun getData(body: allDevotions?) {
        binding.txtAuthor.text = body!!.data[0].author
        binding.txtContent.text = body.data[0].body
        binding.txtReading.text = body.data[0].bibleReading
        binding.txtVerse.text = body.data[0].verseOne
        audioUrl = body.data[0].audioUrl.toString()

    }

}