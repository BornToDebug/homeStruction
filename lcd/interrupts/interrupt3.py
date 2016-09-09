import RPi.GPIO as GPIO
GPIO.setmode(GPIO.BCM)

GPIO.setup(23, GPIO.IN, pull_up_down=GPIO.PUD_UP)
GPIO.setup(18, GPIO.IN, pull_up_down=GPIO.PUD_UP)

GPIO.setup(24, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)

def my_callback(channel):
	print "falling edge detected on 18"

def my_callback2(channel):
	print "falling edge detected on 23"

raw_input("Press Enter when ready\n>")

GPIO.add_event_detect(18, GPIO.FALLING, callback=my_callback, bouncetime=300)
GPIO.add_event_detect(23, GPIO.FALLING, callback=my_callback2, bouncetime=300)

try:
	print "Waiting for rising edge on port 24"
	GPIO.wait_for_edge(24, GPIO.RISING)
	print "Rising edge detected on port 24. Here endeth the third lesson."
except KeyboardInterrupt:
	GPIO.cleanup()
GPIO.cleanup()
