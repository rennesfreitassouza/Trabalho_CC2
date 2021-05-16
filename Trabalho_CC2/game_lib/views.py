from django.http import HttpResponse
from rest_framework import viewsets
from rest_framework.viewsets import ModelViewSet
from rest_framework.response import Response
from rest_framework.decorators import api_view, renderer_classes, authentication_classes
from rest_framework import authentication, permissions
#from .main import main
from .models import *
from .serializers import *
import json

class GamesViewSet(viewsets.ModelViewSet):
    queryset = Games.objects.all()
    serializer_class = GamesSerializer
    permission_classes = [permissions.IsAuthenticated]

class ReleasesViewSet(viewsets.ModelViewSet):
    queryset = Releases.objects.all()
    serializer_class = ReleasesSerializer
    permission_classes = [permissions.IsAuthenticated]


class PlatformsViewSet(viewsets.ModelViewSet):
    queryset = Platforms.objects.all()
    serializer_class = PlatformsSerializer
    permission_classes = [permissions.IsAuthenticated]
