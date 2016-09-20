from django.contrib import admin

from .models import Temperature, Door, Lamp, Light

admin.site.register(Temperature)
admin.site.register(Door)
admin.site.register(Lamp)
admin.site.register(Light)

