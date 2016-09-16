from django.shortcuts import render
from django.views import generic
from .models import Temperature
from django.contrib.auth.mixins import LoginRequiredMixin


class TemperatureView(LoginRequiredMixin, generic.ListView):
    template_name = 'project/temperature.html'
    context_object_name = 'query_results'

    login_url = '/login/'

    def get_queryset(self):
        """
        Should return the latest 10 temperature records
        """
        return Temperature.objects.order_by('-time_recorded')[:10]

class StartView(generic.TemplateView):
    template_name = 'project/base.html'

class HomeView(generic.TemplateView):
    template_name = 'project/home.html'

class AlarmView(generic.TemplateView):
    template_name = 'project/alarm.html'

class DoorAndWindowView(generic.TemplateView):
    template_name = 'project/doorwind.html'

class MultimediaView(generic.TemplateView):
    template_name = 'project/mult.html'

class LightView(generic.TemplateView):
    template_name = 'project/light.html'

class LiveView(generic.TemplateView):
    template_name = 'project/live.html'

class ContactView(generic.TemplateView):
    template_name = 'project/contact.html'

