from __future__ import unicode_literals

from django.db import models
from django.utils import timezone


def valueAndTimeToStr(value, time):
    return str(value) + ' at ' + str(time)

class Temperature(models.Model):
    time_recorded = models.DateTimeField('Time recorded', default=timezone.now)
    value = models.FloatField()

    def __str__(self):
        return valueAndTimeToStr(self.value, self.time_recorded + timezone.timedelta(hours=3))


class Lamp(models.Model):
    time_recorded = models.DateTimeField('Time recorded', default=timezone.now)
    value = models.CharField(max_length=10)

    def __str__(self):
        return valueAndTimeToStr(self.value, self.time_recorded + timezone.timedelta(hours=3))

class Light(models.Model):
    time_recorded = models.DateTimeField('Time recorded', default=timezone.now)
    value = models.FloatField()

    def __str__(self):
        return valueAndTimeToStr(self.value, self.time_recorded + timezone.timedelta(hours=3))

class Door(models.Model):
    time_recorded = models.DateTimeField('Time recorded', default=timezone.now)
    value = models.CharField(max_length=10)

    def __str__(self):
        return valueAndTimeToStr(self.value, self.time_recorded + timezone.timedelta(hours=3))

class Humidity(models.Model):
    time_recorded = models.DateTimeField('Time recorded', default=timezone.now)
    value = models.FloatField()

    def __str__(self):
        return valueAndTimeToStr(self.value, self.time_recorded + timezone.timedelta(hours=3))


class Window(models.Model):
    time_recorded = models.DateTimeField('Time recorded', default=timezone.now)
    value = models.CharField(max_length=10)

    def __str__(self):
        return valueAndTimeToStr(self.value, self.time_recorded + timezone.timedelta(hours=3))

class Alarm(models.Model):
    time_recorded = models.DateTimeField('Time recorded', default=timezone.now)
    alarm_time = models.TimeField('Alarm time', default=timezone.now)
    monday = models.BooleanField('Monday', default=False)
    tuesday = models.BooleanField('Tuesday', default=False)
    wednesday = models.BooleanField('Wednesday', default=False)
    thursday = models.BooleanField('Thursday', default=False)
    friday = models.BooleanField('Friday', default=False)
    saturday = models.BooleanField('Saturday', default=False)
    sunday = models.BooleanField('Sunday', default=False)

    def __str__(self):
        return str(self.alarm_time)

