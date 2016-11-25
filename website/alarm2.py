import django
import sys
import os
sys.path.insert(0,'/home/david/git/homeStruction/website')
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "homeStruction.settings")
django.setup()
import schedule
import time
from project.models import Alarm
from subprocess import call
from project import controlbasic3

globalCoffee = False

def wekker():
    print 'AAAAALLLLLLLAAAAAARRRRRRRRMMMMM'
    call(['killall pulseaudio'], shell=True)
    call(['python /home/projekt/homeStruction/alarm/alarmlight.py &'], shell=True)
    call(['nohup mplayer /mnt/Stick/Music/01.\ Crystals.mp3 &'], shell=True)
    if globalCoffee:
        controlconfirm('3lampon')

class AlarmExecute(object):
    alarm_hour = 0
    alarm_minute = 0
    monday = False
    tuesday = False
    wednesday = False
    thursday = False
    friday = False
    saturday = False
    sunday = False
    coffee = False


    def get_alarm_settings(self):
        new_alarm = Alarm.objects.latest('time_recorded')
        self.alarm_hour = new_alarm.alarm_time.hour
        self.alarm_minute = new_alarm.alarm_time.minute
        self.monday = new_alarm.monday
        self.tuesday = new_alarm.tuesday
        self.wednesday = new_alarm.wednesday
        self.thursday = new_alarm.thursday
        self.friday = new_alarm.friday
        self.saturday = new_alarm.saturday
        self.sunday = new_alarm.sunday
        self.coffee = new_alarm.coffee

    def timeToString(self):
        return str(self.alarm_hour) + ':' + str(self.alarm_minute)

    def set_schedule(self):
        schedule.clear()
        timeString = self.timeToString()
        if self.monday:
            schedule.every().monday.at(timeString).do(wekker)
        if self.tuesday:
            schedule.every().tuesday.at(timeString).do(wekker)
        if self.wednesday:
            schedule.every().wednesday.at(timeString).do(wekker)
        if self.thursday:
            schedule.every().thursday.at(timeString).do(wekker)
        if self.friday:
            schedule.every().friday.at(timeString).do(wekker)
        if self.saturday:
            schedule.every().saturday.at(timeString).do(wekker)
        if self.sunday:
            schedule.every().sunday.at(timeString).do(wekker)
    
    def update(self):
        self.get_alarm_settings()
        self.set_schedule()
        globalCoffee = self.coffee

myAlarm = AlarmExecute()
myAlarm.get_alarm_settings()
myAlarm.set_schedule()

while True:
    schedule.run_pending()
    myAlarm.update()
    time.sleep(1)



