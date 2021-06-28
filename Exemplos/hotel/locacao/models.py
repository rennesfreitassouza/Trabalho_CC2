from django.db import models

class Hotel(models.Model):
	name = models.CharField(max_length=255)

class Quarto(models.Model):
	capacidade = models.IntegerField()
	hotel = models.ForeignKey(Hotel, on_delete=models.CASCADE)

class Reserva(models.Model):
	quarto = models.ForeignKey(Quarto, on_delete=models.CASCADE)
	inicio = models.DateTimeField()
	fim = models.DateTimeField()

