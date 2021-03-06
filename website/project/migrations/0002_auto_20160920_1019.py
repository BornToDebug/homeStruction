# -*- coding: utf-8 -*-
# Generated by Django 1.10 on 2016-09-20 07:19
from __future__ import unicode_literals

from django.db import migrations, models
import django.utils.timezone


class Migration(migrations.Migration):

    dependencies = [
        ('project', '0001_initial'),
    ]

    operations = [
        migrations.RenameField(
            model_name='door',
            old_name='status',
            new_name='value',
        ),
        migrations.RenameField(
            model_name='lamp',
            old_name='status',
            new_name='value',
        ),
        migrations.RemoveField(
            model_name='lamp',
            name='time_switched',
        ),
        migrations.AddField(
            model_name='lamp',
            name='time_recorded',
            field=models.DateTimeField(default=django.utils.timezone.now, verbose_name='Time recorded'),
        ),
        migrations.AlterField(
            model_name='door',
            name='time_recorded',
            field=models.DateTimeField(default=django.utils.timezone.now, verbose_name='Time recorded'),
        ),
        migrations.AlterField(
            model_name='light',
            name='time_recorded',
            field=models.DateTimeField(default=django.utils.timezone.now, verbose_name='Time recorded'),
        ),
        migrations.AlterField(
            model_name='temperature',
            name='time_recorded',
            field=models.DateTimeField(default=django.utils.timezone.now, verbose_name='Time recorded'),
        ),
    ]
