import time

import Adafruit_CharLCD as LCD

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

printstring = "This is a long test message"
#to_print = printstring[:16] + '\n' + printstring[16:]
lcd.set_backlight(0)
lcd.message(printstring)
lcd.message("\nvolume")
volume = 80
def printmp3(printstring, volume):
	timeinfo = time.strftime("%H:%M")
	volumeinfo = "\nvolume:%d" % volume
	secondline = volumeinfo + '  ' + timeinfo
	if len(printstring) <= lcd_columns:
		lcd.message(printstring)
		lcd.message(secondline)
	else:
		movements = len(printstring) - lcd_columns + 1
	for i in range(0, movements):
		lcd.clear()
		printnow = printstring[i:(i + lcd_columns)]
		lcd.message(printnow)
		lcd.message(secondline)
		time.sleep(.5)

printmp3(printstring, volume)
#lcd.message("\nvolume")
time.sleep(5)

