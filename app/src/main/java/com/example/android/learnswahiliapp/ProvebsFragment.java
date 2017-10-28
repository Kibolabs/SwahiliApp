package com.example.android.learnswahiliapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;



public class ProvebsFragment extends Fragment {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private final AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };


    public ProvebsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("Those who dont work, they should not eat", "Asiye fanya kazi, na asile", R.raw.asiyefanyakaziasile));
        words.add(new Word("The poison for a word is a word", "Sumu ya neno ni neno", R.raw.sumeneno));
        words.add(new Word("One who denies me reduces me pain", "Anikataaye kanipunguzia simanzi.", R.raw.anikaayekanipu));
        words.add(new Word("Do not provoke a crocodile before you cross the river", "Usitukane mamba kabla hujavuka mto", R.raw.usitukanemamba));
        words.add(new Word("An evil Indian but his bussiness is good.", "Baniani mbaya kiatu chake dawa.", R.raw.banianimbaya));
        words.add(new Word("Hate burns its preserver.", "Chuki humchoma anayeifadhi", R.raw.chukihifadhi));
        words.add(new Word("Seven", "Fitina ikidhihiri ubaya hukithiri", R.raw.fitinaikidhihiri));
        words.add(new Word("Even a toothless lion does not please and remains hostile", "Hata akose meno, simba hachujuki", R.raw.simbahachujuki));
        words.add(new Word("The heart has no patience", "Moyo hauna subira. ", R.raw.moyohaunasubira));
        words.add(new Word("You know the sweetness of a flower, why then give me poison?", "Wajua tamu ya ua sumu umenipiani.", R.raw.tamuaua));
        words.add(new Word("Great wit drives away wisdom", "Akili nyingi huondowa maarifa", R.raw.akilimaarifa));
        words.add(new Word(" The  touture of the grave is only known by the corpse", "Adhabu ya kaburi aijua maiti", R.raw.adhabuakaburi));
        words.add(new Word("Shark is the famous one in sea the but they many others", "Avumaye baharini papa kumbe wengi wapo.", R.raw.papaavumaye));
        words.add(new Word("A handsome finger gets the ring", "Chanda chema huvikwa pete", R.raw.chandachema));
        words.add(new Word("the curse of the fowl does not bother the kite", "Dua la kuku halimpati mwewe", R.raw.dualakuku));
        words.add(new Word("It is better to lose your eyes than to lose your heart", "Heri kufa macho kuliko kufa moyo", R.raw.herikufamacho));
        words.add(new Word("If you don't know death look at the grave", "Ikiwa hujui kufa,tazama kaburi", R.raw.ikiwahujuikufa));
        words.add(new Word("That is fashionable in town is never prohibited.", "Kingiacho mjini si haramu", R.raw.kingiachomjini));
        words.add(new Word("He is inhuma he who he is not humane", "Asiye jua utu si mtu", R.raw.asiejuautu));
        words.add(new Word("Those who hide private parts wont bear children", "Mficha uchi hazai", R.raw.mfichauchi));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words);
        ListView listView = (ListView) getActivity().findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int Position, long l) {

                releaseMediaPlayer();

                Word word = words.get(Position);
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getMaudioResourceId());

                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
