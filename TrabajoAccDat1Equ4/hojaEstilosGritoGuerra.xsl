<?xml version="1.0" encoding='ISO-8859-1'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:template match='/'>
	<html><xsl:apply-templates /></html>
</xsl:template>
<xsl:template match='Grupo_de_enemigos'>
	 <head><title>GRUPO DE ENEMIGOS</title></head>
	 <body> 
    	<h1> <xsl:value-of select="concat('GRUPO ', position())" /></h1>
	<table border='1'>
		<tr><th>ID</th><th>Nombre</th><th>Elemento</th><th>Grito de guerra</th></tr>
			<xsl:apply-templates select='Enemigo' />
	</table>
	</body>
</xsl:template>
 <xsl:template match='Enemigo'>
   <tr><xsl:apply-templates /></tr>
 </xsl:template>
 <xsl:template match='id|nombre|elemento|gritoguerra'>
   <td><xsl:apply-templates /></td>
 </xsl:template>
</xsl:stylesheet>