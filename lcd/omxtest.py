from omxplayer import OMXPlayer
from time import sleep

player = OMXPlayer('01. Crystals.mp3')
player.play()
sleep(5)
player.pause()
player.quit()
