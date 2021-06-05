from django.db import models
class Games(models.Model):
	name = models.CharField()
	publisher = models.CharField()

class Platforms(models.Model):
	name = models.CharField()

class Releases(models.Model):
	game = models.ForeignKey(Games, on_delete=models.CASCADE)
	platform = models.ForeignKey(Platforms, on_delete=models.CASCADE)
	releasedate = models.DateTimeField()
	version = models.IntegerField()