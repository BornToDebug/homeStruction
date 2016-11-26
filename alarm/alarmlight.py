from time import sleep, strftime
import Adafruit_CharLCD as LCD
import signal
from subprocess import call
from sys import exit

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

def handler(signal, frame):
    lcd.clear()
    lcd.set_backlight(1)
    exit()

signal.signal(signal.SIGTERM, handler)

lcd.set_backlight(0)

while True:
    lcd.clear()
    lcd.set_cursor(5,0)
    lcd.message('ALARM\n')
    lcd.set_cursor(5,1)
    time = strftime("%H:%M")
    lcd.message(time)
    sleep(1)
    
