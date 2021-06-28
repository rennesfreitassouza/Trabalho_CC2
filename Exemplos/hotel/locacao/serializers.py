from rest_framework import serializers
from .models import *

class HotelSerializer(serializers.ModelSerializer):
	class Meta:
		model = Hotel
		fields = '__all__'

class QuartoSerializer(serializers.ModelSerializer):
	class Meta:
		model = Quarto
		fields = '__all__'

class ReservaSerializer(serializers.ModelSerializer):
	class Meta:
		model = Reserva
		fields = '__all__'

