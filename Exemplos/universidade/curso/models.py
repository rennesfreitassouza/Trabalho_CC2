from django.db import models

class Curso(models.Model):
	codigo = models.CharField(max_length=255)
	nome = models.CharField(max_length=255)
	duracao = models.IntegerField()

class Aluno(models.Model):
	RA = models.IntegerField()
	nome = models.CharField(max_length=255)
	endereco = models.CharField(max_length=255)
	curso = models.ForeignKey(Curso, on_delete=models.CASCADE)

class Disciplina(models.Model):
	codigo = models.CharField(max_length=255)
	nome = models.CharField(max_length=255)
	descricao = models.CharField(max_length=255)
	curso = models.ForeignKey(Curso, on_delete=models.CASCADE)

