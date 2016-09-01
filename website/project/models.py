from __future__ import unicode_literals

from django.db import models


class Temperature(models.Model):
    time_recorded = models.DateTimeField('Time recorded')
    value = models.FloatField()

    def __str__(self):
        return str(self.value)


class Lamp(models.Model):
    time_switched = models.DateTimeField('Time switched')
    status = models.CharField(max_length=10)


class Light(models.Model):
    time_recorded = models.DateTimeField('Time recorded')
    value = models.FloatField()


class Door(models.Model):
    time_recorded = models.DateTimeField('Time opened')
    status = models.CharField(max_length=10)
