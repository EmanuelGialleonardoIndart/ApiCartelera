package ttps.cartelera.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import ttps.cartelera.controller.servicios.ServiciosCartelera;
import ttps.cartelera.model.objetos.Cartelera;
import ttps.cartelera.model.objetos.Tag;
import ttps.cartelera.model.objetos.Usuario;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CarteleraController {

	@Autowired
	private ServiciosCartelera serv;

	@RequestMapping(value = "/carteleras", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cartelera>> listaCarteleras(@RequestHeader("token") String token) {
		if (token!=""&&token.length()>=7) {
			String id = token.substring(0, (token.length() - 6));
			String tokenR = token.substring((token.length() - 6), token.length());
			if (tokenR.equals("123456")) {
				Usuario u = serv.getDaoFactory().getUsuarioDAO().recuperar(Integer.parseInt(id));
				if (u != null) {
					List<Cartelera> l = serv.listaPerfil(u.getPerfil().getNombre(), Integer.parseInt(id));
					return new ResponseEntity<List<Cartelera>>(l, HttpStatus.OK);
				} else {
					return new ResponseEntity<List<Cartelera>>(serv.listaDeCarteleras(),HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<List<Cartelera>>(serv.listaDeCarteleras(),HttpStatus.OK);
	}


	@RequestMapping(value = "/carteleranueva", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> nuevaCartelera(@RequestBody Cartelera cartelera,
			@RequestHeader("token") String token) {
		if (token != "" && cartelera != null && token.length()>=7) {
			String id = token.substring(0, (token.length() - 6));
			String tokenR = token.substring((token.length() - 6), token.length());
			if (tokenR.equals("123456")) {
				Usuario u = serv.getDaoFactory().getUsuarioDAO().recuperar(Integer.parseInt(id));
				if (u != null && u.getPerfil().getNombre().equals("Administrador")) {
					String validacion_cartelera = serv.validadCartelera(cartelera);
					if (validacion_cartelera == null) {
						serv.getDaoFactory().getCarteleraDAO().persistir(cartelera);
						return new ResponseEntity<>("[\"exito\"]",HttpStatus.OK);
					} else {
						return new ResponseEntity<String>(validacion_cartelera, HttpStatus.BAD_REQUEST);
					}

				} else {
					return new ResponseEntity<String>("[\"no tiene permiso\"]", HttpStatus.UNAUTHORIZED);
				}
			}
		}
		return new ResponseEntity<String>("[\"error\"]", HttpStatus.UNAUTHORIZED);

	}
	
	@RequestMapping(value = "/tags", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Tag>> obtener_tags(){
		return new ResponseEntity<List<Tag>>(serv.getDaoFactory().getTagDAO().recuperarTodos("id"), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/usuario/cartelera/{id}" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> conoser_relacion(@RequestHeader("token") String token,@PathVariable("id") int id){
		if (token != "" && token.length()>=7) {
			String idu = token.substring(0, (token.length() - 6));
			String tokenR = token.substring((token.length() - 6), token.length());
			if (tokenR.equals("123456")) {
				Usuario u = serv.getDaoFactory().getUsuarioDAO().recuperar(Integer.parseInt(idu));
				if(u!=null) {
					return new ResponseEntity<Boolean>(serv.pertenece_o_no(u, id),HttpStatus.OK);
				}
			}
	}
	return new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED);	
	

}   
	
	private boolean isInteger(String numero){
	    try{
	        Integer.parseInt(numero);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
	
	@RequestMapping(value = "/cartelera/{id}" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cartelera> obtener_cartelera(@PathVariable("id") String id){
		if(this.isInteger(id)){
			
			if(serv.getDaoFactory().getCarteleraDAO().existe(Integer.parseInt(id))){
				return new ResponseEntity<Cartelera>(serv.getDaoFactory().getCarteleraDAO().recuperar(Integer.parseInt(id)),HttpStatus.OK);
			}else{
				return new ResponseEntity<Cartelera>(HttpStatus.BAD_REQUEST);
			}
		}else{
			return new ResponseEntity<Cartelera>(HttpStatus.BAD_REQUEST);
		}
		
		
	}
}
