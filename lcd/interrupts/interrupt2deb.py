import RPi.GPIO as GPIO
import time
GPIO.setmode(GPIO.BCM)

GPIO.setup(23, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(24, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)
time_stamp = time.time()

#now we'll define the threaded callback function
#this will run in another threadwhen our event is detected

def my_callback(channel):
	global time_stamp
	time_now = time.time()
	if (time_now - time_stamp) >= 0.3:
		print "Rising edge detected on port 24 - even though, in the main thread,"
		print "we are still waiting for a falling edge - how cool?\n"
	time_stamp = time_now

raw_input("Press Enter when ready\n>")

GPIO.add_event_detect(24, GPIO.RISING, callback=my_callback)

try:
	print "Waiting for falling edge on port 23"
	GPIO.wait_for_edge(23, GPIO.FALLING)
	print "Falling edge detected. Here endeth the second lesson."
except KetboardInterrupt:
	GPIO.cleanup()
GPIO.cleanup()
