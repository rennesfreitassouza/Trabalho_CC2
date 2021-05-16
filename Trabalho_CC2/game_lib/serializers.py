from rest_framework import serializers
from .models import *

class GamesSerializer(serializers.ModelSerializer):
    class Meta:
        model = Games
        fields = '__all__'

class ReleasesSerializer(serializers.ModelSerializer):
    class Meta:
        model = Releases
        fields = '__all__'

class PlatformsSerializer(serializers.ModelSerializer):
    class Meta:
        model = Platforms
        fields = '__all__'