from time import sleep, strftime
import Adafruit_CharLCD as LCD
import signal
from subprocess import call
import string
import alsaaudio

sleep(2)
f = open('/home/projekt/homeStruction/newmp3/logfile', 'r')
#f2 = open('/home/projekt/homeStruction/newmp3/titlelog', 'w')

lcd_rs = 20
lcd_en = 5
lcd_d4 = 6
lcd_d5 = 13
lcd_d6 = 19
lcd_d7 = 26
lcd_backlight = 21

lcd_columns = 16
lcd_rows = 2

lcd = LCD.Adafruit_CharLCD(lcd_rs, lcd_en, lcd_d4, lcd_d5, lcd_d6, lcd_d7, lcd_columns, lcd_rows, lcd_backlight)
class SongInfo(object):

	movements = 0
	i = 0

	def __init__(self):
		self.title = None
		self.prevtitle = None
		self.position = None
		self.volume = None
		self.pause = None
		self.time = None

	@staticmethod
	def requests():
		call('echo get_percent_pos > /home/projekt/homeStruction/newmp3/mplayer.pipe', shell=True)
		sleep(.2)
		call('echo get_property pause > /home/projekt/homeStruction/newmp3/mplayer.pipe', shell=True)
		sleep(.2)

	def update(self, fileobj):
		SongInfo.requests()		
		content = fileobj.read()
		for item in content.split("\n"):
			if "Title" in item:
                                temp = item.split("Title: ")[-1].strip("\n")
                                self.title = "".join(filter(lambda x: x in string.printable, temp))
			if "ANS_PERCENT_POS" in item:
				temp = item.split("ANS_PERCENT_POSITION=")[-1].strip("\r\n")
				self.position = int(temp)
			if "ANS_pause" in item:
				self.pause = True if (item.split("ANS_pause=")[-1].strip("\r\n")) == 'yes' else False
		m = alsaaudio.Mixer('PCM')
		vol = m.getvolume()
		self.volume = int(vol[0])
		del m
		self.time = strftime("%H:%M")	
	
	def first_line(self):
		if self.prevtitle != self.title:
			self.prevtitle = self.title
			print "LENGTH = %d" % len(self.prevtitle)
			if len(self.prevtitle) <= lcd_columns:
				SongInfo.movements = 0
			else:
				SongInfo.movements = len(self.prevtitle) - lcd_columns + 1
				SongInfo.i = 0
		if SongInfo.movements == 0:
			return self.prevtitle
		else:
			if SongInfo.i == SongInfo.movements:
				SongInfo.i = 0
			SongInfo.i += 1
			return self.prevtitle[(SongInfo.i-1):(SongInfo.i - 1 + lcd_columns)]
		
def handler(signal, frame):
#	global f2
#	f2.close()
	lcd.clear()
	lcd.set_backlight(1)
	

signal.signal(signal.SIGTERM, handler)

#back = True
lcd.set_backlight(0)
info = SongInfo()
#f2.write('test\n')
#i = 0
while True:
#	lcd.set_backlight(back)
#	back = not(back)
#	info.updatetitle(f)
#	f2.write(info.title)
#	f2.write('\n')
	info.update(f)
	lcd.clear()
	lcd.message("%s" % info.first_line())
	lcd.set_cursor(0,1)
	lcd.message("p=%d" % info.position)
	lcd.set_cursor(5,1)
	lcd.message("v=%d" % info.volume)
	if info.pause:
		lcd.set_cursor(10,1)
		lcd.message('P')
	lcd.set_cursor(11,1)
	lcd.message(info.time)
	sleep(.5)
