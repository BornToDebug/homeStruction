# -*- coding: utf-8 -*-
# Generated by Django 1.10 on 2016-10-10 19:37
from __future__ import unicode_literals

from django.db import migrations, models
import django.utils.timezone


class Migration(migrations.Migration):

    dependencies = [
        ('project', '0005_alarm'),
    ]

    operations = [
        migrations.AlterField(
            model_name='alarm',
            name='alarm_time',
            field=models.TimeField(default=django.utils.timezone.now, verbose_name='Alarm time'),
        ),
    ]
