def troco = Troco.&troco

/***
 * Copiado de: http://www.dojopuzzles.com/problemas/exibe/troco/
 * Seu desafio é fazer um programa que leia o valor total a ser pago e o valor efetivamente pago, 
 * informando o menor número de cédulas e moedas que devem ser fornecidas como troco.
 * Deve-se considerar que há:
 * 
 *    cédulas de R$100,00, R$50,00, R$10,00, R$5,00 e R$1,00;
 *    moedas de R$0,50, R$0,10, R$0,05 e R$0,01.
 *    
 * @param valor Valor a ser pago.
 * @param pago Valor efetivamente pago.
 * @return {@link java.util.Map} com a {@code key} sendo a cédula ou moeda e o {@code value} o número de cédula ou moeda a ser fornecido. 
 * Exemplo: troco(105, 150) => saída: [10:40, 5:1]  
 */
static Map troco(valor, pago) 
{
	// G means BigDecimal (http://groovy.codehaus.org/Groovy+Math)
	def cedulas_moedas = [100G, 50G, 10G, 5G, 1G, 0.50G, 0.10G, 0.05G,0.01G]
	
	if (pago < valor)
	{
		return [:];
	}
	
	def troco =  pago - valor
	def valores = new HashMap<>();
	
	cedulas_moedas.each { v ->

		if (troco > 0 && v <= troco)
		{
			def n =  (int) troco.divide(v)
			troco = troco.subtract(n * v);

			valores.put(v, n);
		}
	}
	
	return valores;
}
assert [:] == troco(100G,100G)
assert [:] == troco(1.0G,0.98G)

assert [5G:1, 10G:4] == troco(105G,150G)
assert [10G:4] == troco(110G,150G)
assert [0.01G:2] == troco(0.98G,1.0G)
assert [0.01:2] == troco(0.18G,0.20G)
assert [0.10:3, 0.01:4] == troco(1.66G,2G)
assert [100G:10, 0.10:3, 0.01:4] == troco(1.66G,1002G)