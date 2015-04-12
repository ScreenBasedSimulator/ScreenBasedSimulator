package edu.cmu.lti.bic.sbs.ui;

import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Sound {
  static AudioStream audioStream;

  public static void play(String s) throws Exception {
    String gongFile = null;
    // open the sound file as a Java input stream
    if (s.equals("alarm")) {
      gongFile = "src/test/resources/media/alarm.wav";
    }
    InputStream in = new FileInputStream(gongFile);

    // create an audiostream from the inputstream
    audioStream = new AudioStream(in);

    // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);
    // return audioStream;
    // audioStream.close();
  }

  public static void stop() throws Exception {
    // AudioStream audioStream = Sound.play();
    audioStream.close();
  }
}
