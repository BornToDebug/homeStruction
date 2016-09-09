import RPi.GPIO as GPIO
GPIO.setmode(GPIO.BCM)

GPIO.setup(23, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(24, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)

#now we'll define the threaded callback function
#this will run in another threadwhen our event is detected

def my_callback(channel):
	print "Rising edge detected on port 24 - even though, in the main thread,"
	print "we are still waiting for a falling edge - how cool?\n"

raw_input("Press Enter when ready\n>")

GPIO.add_event_detect(24, GPIO.RISING, callback=my_callback, bouncetime=300)

try:
	print "Waiting for falling edge on port 23"
	GPIO.wait_for_edge(23, GPIO.FALLING)
	print "Falling edge detected. Here endeth the second lesson."
except KetboardInterrupt:
	GPIO.cleanup()
GPIO.cleanup()
