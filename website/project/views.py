from django.http import HttpResponse
from django.shortcuts import render
from django.views import generic
from .models import Temperature, Light, Lamp, Door, Window, Humidity
from django.contrib.auth.mixins import LoginRequiredMixin
from rest_framework import viewsets, generics, response, views
from project.serializers import TemperatureSerializer, LightSerializer, LampSerializer, DoorSerializer, WindowSerializer, HumiditySerializer
from django.contrib.auth.decorators import login_required
from rest_framework.response import Response

# REST framework viewset
class HomeDataViewSet(views.APIView):

    def get(self, request, format=None):
        resp = []
        Models = [Temperature, Light, Lamp, Door, Window, Humidity]
        for model in Models:
            if model == Lamp:
                resp.append(model.objects.filter(value__startswith='1').last().value)
                resp.append(model.objects.filter(value__startswith='2').last().value)
                resp.append(model.objects.filter(value__startswith='3').last().value)
                resp.append(model.objects.filter(value__startswith='d').last().value)

            resp.append(model.objects.order_by('time_recorded').last().value)
        return Response(resp)

class TemperatureViewSet(viewsets.ModelViewSet):

    queryset = Temperature.objects.all().order_by('-time_recorded')
    serializer_class = TemperatureSerializer

class LightViewSet(viewsets.ModelViewSet):

    queryset = Light.objects.all().order_by('-time_recorded')
    serializer_class = LightSerializer

class LampViewSet(viewsets.ModelViewSet):

    serializer_class = LampSerializer
    queryset = Lamp.objects.all().order_by('-time_recorded')

class LampStartList(generics.ListAPIView):
    serializer_class = LampSerializer

    def get_queryset(self):
        startswith = self.kwargs['startswith']
        return Lamp.objects.filter(value__startswith=startswith).order_by('-time_recorded')

class DoorViewSet(viewsets.ModelViewSet):

    queryset = Door.objects.all().order_by('-time_recorded')
    serializer_class = DoorSerializer

class HumidityViewSet(viewsets.ModelViewSet):

    queryset = Humidity.objects.all().order_by('-time_recorded')
    serializer_class = HumiditySerializer


class WindowViewSet(viewsets.ModelViewSet):

    queryset = Window.objects.all().order_by('-time_recorded')
    serializer_class = WindowSerializer


# Website views
class TemperatureView(LoginRequiredMixin, generic.ListView):
    template_name = 'project/temp.html'
    context_object_name = 'query_results'

    def get_queryset(self):
        """
        Should return the latest 10 temperature records
        """
        return Temperature.objects.order_by('-time_recorded')[:10]

class StartView(LoginRequiredMixin, generic.TemplateView):
    template_name = 'project/base.html'

class HomeView(LoginRequiredMixin, generic.TemplateView):
    template_name = 'project/home.html'

class AlarmView(LoginRequiredMixin, generic.TemplateView):
    template_name = 'project/alarm.html'

class DoorAndWindowView(LoginRequiredMixin, generic.TemplateView):
    template_name = 'project/doorwind.html'

class MultimediaView(LoginRequiredMixin, generic.TemplateView):
    template_name = 'project/mult.html'

class LightView(LoginRequiredMixin, generic.TemplateView):
    template_name = 'project/light.html'

class LiveView(LoginRequiredMixin, generic.TemplateView):
    template_name = 'project/live.html'

class ContactView(LoginRequiredMixin, generic.TemplateView):
    template_name = 'project/contact.html'

class HumidityView(LoginRequiredMixin, generic.TemplateView):
    template_name = 'project/humid.html'

