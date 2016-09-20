from __future__ import unicode_literals

from django.db import models
from django.utils import timezone


def valueAndTimeToStr(value, time):
    return str(value) + ' at ' + str(time)

class Temperature(models.Model):
    time_recorded = models.DateTimeField('Time recorded', default=timezone.now)
    value = models.FloatField()

    def __str__(self):
        return valueAndTimeToStr(self.value, self.time_recorded) 


class Lamp(models.Model):
    time_recorded = models.DateTimeField('Time recorded', default=timezone.now)
    value = models.CharField(max_length=10)

    def __str__(self):
        return valueAndTimeToStr(self.value, self.time_recorded) 

class Light(models.Model):
    time_recorded = models.DateTimeField('Time recorded', default=timezone.now)
    value = models.FloatField()
    
    def __str__(self):
        return valueAndTimeToStr(self.value, self.time_recorded) 

class Door(models.Model):
    time_recorded = models.DateTimeField('Time recorded', default=timezone.now)
    value = models.CharField(max_length=10)

    def __str__(self):
        return valueAndTimeToStr(self.value, self.time_recorded) 


