from time import sleep
import alsaaudio
from subprocess import call
import Adafruit_CharLCD as LCD
import signal

sleep(3)
f = open('/home/projekt/homeStruction/newmp3/logfile', 'r')

def gettitle():
	global f
	content = f.read()
	for item in content.split("\n"):
		if "Title" in item:
			return item

def getpercent():
	global f
	content = f.read()
	for item in content.split("\n"):
		if "ANS_PERCENT_POS" in item:
			return item
#LCD pins setup using BCM GPIO numbering
lcd_rs = 20
lcd_en = 5
lcd_d4 = 6
lcd_d5 = 13
lcd_d6 = 19
lcd_d7 = 26
lcd_backlight = 21

lcd_columns = 16
lcd_rows = 2

#Initializing LCD
lcd = LCD.Adafruit_CharLCD(lcd_rs, lcd_en, lcd_d4, lcd_d5, lcd_d6, lcd_d7, lcd_columns, lcd_rows, lcd_backlight)

def handler(signal, frame):
	lcd.clear()
	lcd.set_backlight(1)

signal.signal(signal.SIGTERM, handler)

lcd.set_backlight(0)
lcd.clear()
#back = True
while True:
	#lcd.set_backlight(back)
	#back = not(back)
	title1 = gettitle()
	if title1 != None:
		title2 = title1
		
	#print title2
	lcd.clear()
	lcd.message(title2)
	#m = alsaaudio.Mixer('PCM')
	#vol = m.getvolume()
	#vol = int(vol[0])
	#call('echo "get_percent_pos" > /home/projekt/homeStruction/newmp3/mplayer.pipe', shell=True)
	#percent1 = getpercent()
	#if percent1 != None:
	#	percent2 = percent1
	#print percent2
	#print "volume: %d\n" % vol
	#del vol
	#del m
	sleep(1)
