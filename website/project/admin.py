from django.contrib import admin

from .models import Temperature, Door, Lamp, Light, Window, Humidity, Alarm

admin.site.register(Temperature)
admin.site.register(Door)
admin.site.register(Lamp)
admin.site.register(Light)
admin.site.register(Window)
admin.site.register(Humidity)
admin.site.register(Alarm)

