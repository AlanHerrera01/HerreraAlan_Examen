package edu.espe.springlab.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * INTERCEPTOR DE LOGGING
 * 
 * Registra información de cada petición HTTP:
 * - Método HTTP (GET, POST, etc.)
 * - URI solicitada
 * - Código de respuesta (200, 404, etc.)
 * - Tiempo de procesamiento en milisegundos
 * 
 * CICLO DE VIDA:
 * 1. preHandle() - Antes de procesar la petición
 * 2. [Controller procesa la petición]
 * 3. afterCompletion() - Después de completar la petición
 * 
 * CONFIGURACIÓN:
 * Se registra en WebConfig para interceptar todas las rutas /api/**
 * 
 * SALIDA EN CONSOLA:
 * preHandle -> GET /api/students
 * afterCompletion -> status = 200 tiempo = 45 ms
 */
@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("t0", System.currentTimeMillis());
        System.out.println("preHandle -> " + request.getMethod() + " " + request.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long t0 = (Long) request.getAttribute("t0");
        long elapsed = (t0 == null ? 0 : System.currentTimeMillis() - t0);
        System.out.println("afterCompletion -> status = " + response.getStatus() + " tiempo = " + elapsed + " ms");
    }
}
