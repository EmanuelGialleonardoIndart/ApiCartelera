package ttps.cartelera.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ttps.cartelera.model.DAOimpl.DaoFactory;
import ttps.cartelera.model.objetos.Usuario;
@CrossOrigin(origins = "*", exposedHeaders = "token")
@RestController
public class UsuarioController {

	@Autowired
	private DaoFactory daoFactory;
	/* no utilizo servicio porque no es necesario */

	@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getUser(@RequestHeader("token") String token,@PathVariable("id") int id) {
		if (token == "" || token.length()<7) {
			return new ResponseEntity<Usuario>(HttpStatus.UNAUTHORIZED);
		}
		String tokenR = token.substring((token.length() - 6), token.length());
		if (tokenR.equals("123456")) {
			Usuario user = daoFactory.getUsuarioDAO().obtener_con_notificaciones(id);
			if (user == null) {
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Usuario>(user, HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<Usuario>(HttpStatus.UNAUTHORIZED);
		}

	}
	@RequestMapping(value = "/autenticacion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(@RequestBody Usuario user) {
		if (user.getUsername() == null || user.getPassword() == null) {
			return new ResponseEntity<String>("[\"completar todos los campos\"]",HttpStatus.FORBIDDEN);
		}
		Usuario u = daoFactory.getUsuarioDAO().obtener_por_username(user.getUsername());
		if (u == null) {
			return new ResponseEntity<String>("[\"informacion incorrecta\"]",HttpStatus.FORBIDDEN);
		} else {
			if (!user.getPassword().equals(u.getPassword())) {
				return new ResponseEntity<String>("[\"informacion incorrecta\"]",HttpStatus.FORBIDDEN);
			} else {
				HttpHeaders headers = new HttpHeaders();
				headers.add("token", u.getId() + "123456");
				return new ResponseEntity<String>("[\"exito\"]",headers, HttpStatus.OK);
			}

		}
	}

	

}
