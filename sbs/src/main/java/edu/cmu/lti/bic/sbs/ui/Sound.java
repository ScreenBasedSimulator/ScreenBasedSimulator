package edu.cmu.lti.bic.sbs.ui;

import java.io.*;

import sun.audio.*;

public class Sound {
	static AudioStream audioStream = null;

	public static void play(String id) throws Exception {

		if (id.equals("1")) {
			// open the sound file as a Java input stream
			String gongFile = "src/test/resources/media/1233.wav";
			InputStream in = new FileInputStream(gongFile);

			// create an audiostream from the inputstream
			audioStream = new AudioStream(in);

			// play the audio clip with the audioplayer class
			AudioPlayer.player.start(audioStream);
			audioStream.close();
		}
	}

	public static void stop() throws IOException {
		audioStream.close();
	}
}
