import lirc
import os
import subprocess
from time import sleep

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
sockid = lirc.init("myprogram")
prompt = "> "

working = False
song = 0
print prompt
code = lirc.nextcode()
while code != ["exit"]:
	print "in while"
	if code == ["equal"]:
		print "equal"
		if working == False:
			print "working false"
			p = subprocess.Popen(["omxplayer", buffer[song]], stdin=subprocess.PIPE)
			printtitle(buffer[song])
			working = True
		else:
			print "working true"
			playing = p.poll()
			if playing != 0:
				p.stdin.write('q')
				p.kill()
			working = False
	if code == ["playpause"]:
		playing = p.poll()
		if playing != 0:
			p.stdin.write('p')
		else:
			p = subprocess.Popen(["omxplayer", buffer[song]], stdin=subprocess.PIPE)
	if code == ["next"]:
		playing = p.poll()
		if playing != 0:
			p.stdin.write('q')
			p.kill()
			if song == listlength:
				song = 0
				p = subprocess.Popen(["omxplayer", buffer[song]], stdin=subprocess.PIPE)
				printtitle(buffer[song])
				working = True
			else:
				song = song + 1
				p = subprocess.Popen(["omxplayer", buffer[song]], stdin=subprocess.PIPE)
				printtitle(buffer[song])
				working = True


	if code == ["one"]:
		playing = p.poll()
		print playing
	print prompt
	code = lirc.nextcode()
print p.poll()
if working == True:
	p.stdin.write("q")
	p.kill()
	working = False	
print p.poll()		
