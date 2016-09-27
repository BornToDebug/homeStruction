from rest_framework import serializers
from models import Temperature, Lamp, Light, Door, Window


class TemperatureSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Temperature
        fields = ('time_recorded', 'value')

class LampSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Lamp
        fields = ('time_recorded', 'value')
 
class LightSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Light
        fields = ('time_recorded', 'value')
 
class DoorSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Door
        fields = ('time_recorded', 'value')
 
class WindowSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Window
        fields = ('time_recorded', 'value')
                             
