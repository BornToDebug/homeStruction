from time import sleep

f = open('logfile', 'r')

def gettitle():
	global f
	content = f.read()
	for item in content.split("\n"):
		if "Title" in item:
			print item.strip()

while True:
	gettitle()
	sleep(1)
