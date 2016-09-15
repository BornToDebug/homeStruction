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
