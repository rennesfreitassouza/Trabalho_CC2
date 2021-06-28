from django.http import HttpResponse
from rest_framework import viewsets
from rest_framework.viewsets import ModelViewSet
from rest_framework.response import Response
from rest_framework import authentication, permissions
from .models import *
from .serializers import *
import json

class HotelViewSet(viewsets.ModelViewSet):
	queryset = Hotel.objects.all()
	serializer_class = HotelSerializer
	permission_classes = [permissions.IsAuthenticated]

class QuartoViewSet(viewsets.ModelViewSet):
	queryset = Quarto.objects.all()
	serializer_class = QuartoSerializer
	permission_classes = [permissions.IsAuthenticated]

class ReservaViewSet(viewsets.ModelViewSet):
	queryset = Reserva.objects.all()
	serializer_class = ReservaSerializer
	permission_classes = [permissions.IsAuthenticated]

