package com.kdyzm.entity.resp;

import com.kdyzm.entity.resp.base.BaseResponseMessage;

public class MusicMessage extends BaseResponseMessage{
	// 音乐  
    private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}  
    
}
