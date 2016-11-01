package br.com.caelum.livraria.interceptors;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Log
@Interceptor
public class TempoDeExecucaoInterceptor implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@AroundInvoke
	public Object LogTempoExecucao(InvocationContext contexto)throws Exception{
		long antes = System.currentTimeMillis();
		
		Object resultado = contexto.proceed();
		
		long depois = System.currentTimeMillis();
		System.out.println("+++++++++++++++++++++++++++++++ Tempo Estimado Transação, Antes e Depois: "+ (depois - antes));
		
		return resultado;
	}
}
