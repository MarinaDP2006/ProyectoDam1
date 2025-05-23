package dao;

import dto.Pelicula;
import dto.Genero;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Clase DAO para gestionar las operaciones CRUD de películas.
 * Tiene métodos para filtrar películas por género y otras consultas.
 */
public class PeliculaDAO {
    private static List<Pelicula> peliculas = new ArrayList<>();

    /**
     * Registra una nueva película en el sistema.
     * @param pelicula Película a agregar
     * @throws IllegalArgumentException Si la película es nula o el género no es válido.
     */
    public void add(Pelicula pelicula) {
        if (pelicula == null || pelicula.getGenero() == null) {
            throw new IllegalArgumentException("Película o género no válidos");
        }
        peliculas.add(pelicula);
    }

    /**
     * Filtra películas por género.
     * @param genero Género para filtrar (ej: Genero.ACCION).
     * @return Lista de películas que coinciden con el género (lista vacía si no).
     */
    public List<Pelicula> filtrarPorGenero(Genero genero) {
        List<Pelicula> resultado = new ArrayList<>();
        for (Pelicula p : peliculas) {
            if (p.getGenero() == genero) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    /**
     * Busca una película por su título.
     * @param titulo Título exacto de la película (no sensible a mayúsculas).
     * @return La película encontrada o null si no existe.
     */
    public Pelicula buscarPorTitulo(String titulo) {
        return peliculas.stream()
            .filter(p -> p.getTitulo().equalsIgnoreCase(titulo))
            .findFirst()
            .orElse(null);
    }

    /**
     * Lista de todas las películas registradas.
     * @return Lista completa de películas.
     */
    public List<Pelicula> listarPeliculas() {
        return new ArrayList<>(peliculas);
    }

    /**
     * Devuelve un stream de películas.
     * @return Stream de películas
     */
    public static Stream<Pelicula> stream() {
        return peliculas.stream();
    }
}
