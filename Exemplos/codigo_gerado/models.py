from django.db import models
class Games(models.Model):
	name = models.CharField()
	publisher = models.CharField()

class Platforms(models.Model):
	name = models.CharField(max_length=255,unique=True,)

class Releases(models.Model):
	game = models.ForeignKey(Games, on_delete=models.CASCADE)
	platform = models.ForeignKey(Platforms, on_delete=models.CASCADE)
	releasedate = models.DateTimeField()
	version = models.IntegerField()

