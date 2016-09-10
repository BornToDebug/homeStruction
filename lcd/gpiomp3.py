import os
import subprocess
from time import sleep
import RPi.GPIO as GPIO
import alsaaudio

def get_files(root):
	files = []
	
	def scan_dir(dir):
		for f in os.listdir(dir):
			f = os.path.join(dir, f)
			if os.path.isdir(f):
				scan_dir(f)
			elif os.path.splitext(f)[1] == ".mp3":
				files.append(f)
	scan_dir(root)
	return files

def printtitle(string):
	info = string.split('/')
	title = info[len(info) - 1]
	print title


buffer = get_files("/mnt/Music")
listlength = len(buffer)
print "listlength: %d" % listlength
print buffer	

#BUTTON DECLARATIONS
b_onoff = 22
b_playpause = 17
b_previous = 18
b_next = 27
b_voldown = 23
b_volup = 24

GPIO.setmode(GPIO.BCM)
GPIO.setup(b_onoff, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(b_playpause, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(b_previous, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(b_next, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(b_voldown, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(b_volup, GPIO.IN, pull_up_down=GPIO.PUD_UP)
song = 0
enterwhile = 0
playing = 0
m = alsaaudio.Mixer('PCM')
vol = m.getvolume()
vol = int(vol[0])
print "volume: %d\n" % vol
#FUNCTION DECLARATIONS
def switch_while(channel):
	global enterwhile
	if enterwhile == 0:
		enterwhile = 1
	else:
		enterwhile = 0

def pause(channel):
	if 'p' in vars() or 'p' in globals():
		global p
		p.stdin.write('p')
def previoussong(channel):
	if 'p' in vars() or 'p' in globals():
		global p
		global song
		global playing
		p.stdin.write('q')
		subprocess.call("pkill omx", shell=True)
		del p
		if song == 0:
			song = listlength - 1
		else:
			song = song - 1
		playing = 0
def nextsong(channel):
	if 'p' in vars() or 'p' in globals():
		global p
		global song
		global playing
		p.stdin.write('q')
		subprocess.call("pkill omx", shell=True)
		del p
		if song == listlength - 1:
			song = 0
		else:
			song = song + 1
		playing = 0
def volumeup(channel):
	global vol
	vol = m.getvolume()
	vol = int(vol[0])
	if vol == 0:
		m.setvolume(65)
		print "volume: 65\n"
	else:
		if vol < 100:
			vol = vol + 5
			m.setvolume(vol)
			print "volume: %d\n" % vol
		else:
			print "volume: MAX\n"

def volumedown(channel):
	global vol
	vol = m.getvolume()
	vol = int(vol[0])
	if vol > 65:
		vol = vol - 5
		m.setvolume(vol)
		print "volume: %d\n" % vol
	else:
		if vol != 0:
			m.setvolume(0)
		print "volume: MUTED\n"
	
GPIO.add_event_detect(b_onoff, GPIO.FALLING, callback=switch_while, bouncetime=300)
GPIO.add_event_detect(b_playpause, GPIO.FALLING, callback=pause, bouncetime=300)
GPIO.add_event_detect(b_previous, GPIO.FALLING, callback=previoussong, bouncetime=300)
GPIO.add_event_detect(b_next, GPIO.FALLING, callback=nextsong, bouncetime=300)
GPIO.add_event_detect(b_voldown, GPIO.FALLING, callback=volumedown, bouncetime=300)
GPIO.add_event_detect(b_volup, GPIO.FALLING, callback=volumeup, bouncetime=300)
while True:
	print playing
	if enterwhile == 1:
		printtitle(buffer[song])
		print "entered while"
		if playing == 0:
			p = subprocess.Popen(["mplayer", buffer[song]], stdin=subprocess.PIPE)
		print p.poll()
		if p.poll() != 0:
			print "ppoll None"
			playing = 1
		else:
#			subprocess.call("pkill omx", shell=True)
			print "on else"
			playing = 0
			if song == listlength - 1:
				song = 0
			else:
				song = song + 1
	else:
		print "did not enter while"
		if 'p' in vars() or 'p' in globals():
			if p.poll() != 0:
				p.stdin.write('q')
				playing = 0
				subprocess.call("pkill omx", shell=True)
				del p
	sleep(1)
GPIO.cleanup()
