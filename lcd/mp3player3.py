from time import sleep
import vlc

instance = vlc.Instance()
player = instance.media_player_new()
media = instance.media_new('01. Crystals.mp3')
player.set_media(media)
player.play()
#sleep(10)
#player.audio_set_volume(50)
#player.play()
